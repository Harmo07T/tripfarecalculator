package littlepay.tripfarecalculator.io;

import java.io.FileWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;

import littlepay.tripfarecalculator.model.dto.BusTrip;
import littlepay.tripfarecalculator.model.dto.Status;
import littlepay.tripfarecalculator.utils.Utils;

/**
 * Write to a csv file
 * @author Harmonie
 *
 */
public class CsvFileWriter {
	private static final Logger logger = LoggerFactory.getLogger(CsvFileWriter.class);
	public void csvWriterOneByOne(List<BusTrip> busTrips, String outputFile) throws Exception {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
			for (BusTrip trip : busTrips) {
				String endDate = "NA";
				String duration = "NA";
				String stopOff = "NA";
				if (trip.getStatus() != Status.INCOMPLETE) {
					endDate = Utils.sdf.format(trip.getTapOff().getDate());
					duration = Long.toString(trip.getDuration());
					stopOff = trip.getTapOff().getStopId();
				}

				String[] output = { Utils.sdf.format(trip.getTapOn().getDate()), endDate, duration,
						trip.getTapOn().getStopId(), stopOff, Double.toString(trip.getFares()),
						trip.getTapOn().getCompany(), trip.getTapOn().getBusId(),
						Long.toString(trip.getTapOn().getCustomer().getPAN()), trip.getStatus().toString() };
				writer.writeNext(output);

			}

			writer.close();
		} catch (Exception e) {
			logger.error("Could not write in file");
			e.printStackTrace();
		}
	}
}
