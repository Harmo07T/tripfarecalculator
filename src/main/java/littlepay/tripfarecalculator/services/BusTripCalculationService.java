package littlepay.tripfarecalculator.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import littlepay.tripfarecalculator.calculators.DurationCalculator;
import littlepay.tripfarecalculator.calculators.FareCalculator;
import littlepay.tripfarecalculator.calculators.StatusCalculator;
import littlepay.tripfarecalculator.configuration.BusTripConfiguration;
import littlepay.tripfarecalculator.io.CsvFileReader;
import littlepay.tripfarecalculator.io.CsvFileWriter;
import littlepay.tripfarecalculator.model.dto.BusTrip;
import littlepay.tripfarecalculator.model.dto.Status;
import littlepay.tripfarecalculator.model.dto.Tap;
import littlepay.tripfarecalculator.model.dto.TapType;

@Service
public class BusTripCalculationService {
	private static final Logger logger = LoggerFactory.getLogger(BusTripCalculationService.class);
	private final String inputFile;
	private final String outputFile;
	private BusTripConfiguration configuration;
	
	public BusTripCalculationService(BusTripConfiguration configuration) {
		inputFile = configuration.getInputFile();
		outputFile = configuration.getOutputFile();
		this.configuration = configuration;
	}

	
	public void calculateFares() {
		CsvFileReader csvFileReader = new CsvFileReader();
		List<Tap> taps = new ArrayList<>();

		// Read and parse cvs File
		try {
			taps = csvFileReader.parseData(csvFileReader.readCSV(inputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// create list of trips
		List<BusTrip> listOfBusTrips = createListOfTrips(taps);

		// write list of trips to csv
		CsvFileWriter csvFileWriter = new CsvFileWriter();
		try {
			csvFileWriter.csvWriterOneByOne(listOfBusTrips, outputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Create the list of Trips from the list of Taps
	 * @param taps
	 * @return list of trips
	 */
	private List<BusTrip> createListOfTrips(List<Tap> taps) {
		Map<Long, Tap> tapONMap = new HashMap<>();
		List<BusTrip> busTrips = new ArrayList<>();

		taps.forEach((tap) -> {
			// if tapType is ON -> add to tapON
			if (tap.getTapType() == TapType.ON) {
				tapONMap.put(tap.getCustomer().getPAN(), tap);
			}
			// if tapType is OFF -> get tapON from list, generate Trip details, add to list
			// of trips, remove from tapON
			else {
				Long pan = tap.getCustomer().getPAN();
				if (tapONMap.containsKey(pan)) {
					Tap tapon = tapONMap.get(pan);
					busTrips.add(generateTripDetails(tapon, tap));
					tapONMap.remove(pan, tapon);
				}

			}

		});

		// All remaining taps are IncompleteTrips
		tapONMap.forEach((id, tap) -> {
			busTrips.add(generateTripDetails(tap, null));

		});
		return busTrips;
	}

	/**
	 * calculate details for trips
	 * 
	 * @param tapon
	 * @param tapoff
	 * @return trip 
	 */
	private BusTrip generateTripDetails(Tap tapon, Tap tapoff) {
		BusTrip trip = new BusTrip();
		
		trip.setTapOn(tapon);

		if (tapoff != null) {
			trip.setTapOff(tapoff);
			// calculate duration of trip
			long duration = DurationCalculator.calculateDurationBetweenTwoDates(tapon.getDate(), tapoff.getDate());
			trip.setDuration(duration);
		}
		// Determine status
		Status status = StatusCalculator.calculateStatus(tapon, tapoff);
		trip.setStatus(status);
		// calculate fare due
		FareCalculator fareCalculator = new FareCalculator(configuration);
		double fare = fareCalculator.calculateFare(tapon, tapoff, status);
		trip.setFares(fare);

		return trip;
	}

}
