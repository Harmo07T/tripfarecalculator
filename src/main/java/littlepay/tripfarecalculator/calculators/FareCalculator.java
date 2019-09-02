package littlepay.tripfarecalculator.calculators;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import littlepay.tripfarecalculator.configuration.BusTripConfiguration;
import littlepay.tripfarecalculator.model.dto.Status;
import littlepay.tripfarecalculator.model.dto.Tap;

public class FareCalculator {

	private final Map<Set<String>, Double> mapOfFares;
	private final Map<String, Double> mapOfMaximumFares;

	public FareCalculator(BusTripConfiguration busTripConfiguration) {
		mapOfFares = createMapofFares(busTripConfiguration.getFares());
		mapOfMaximumFares = createMapOfMaximumFares(busTripConfiguration.getFares());
	}

	/**
	 * create map of fares from application.yml
	 * 
	 * @param collectinOfFares
	 * @return
	 */
	private Map<Set<String>, Double> createMapofFares(Collection<BusTripConfiguration.Fares> collectinOfFares) {

		Map<Set<String>, Double> map = new HashMap<>();
		collectinOfFares.forEach(fare -> {
			Set<String> stops = new HashSet<>();
			stops.addAll(fare.getStopid());
			map.put(stops, fare.getPrice());
		});
		return map;
	}

	/**
	 * create map of maximum fares from application.yml
	 * 
	 * @param collectinOfFares
	 * @return
	 */
	private Map<String, Double> createMapOfMaximumFares(Collection<BusTripConfiguration.Fares> collectinOfFares) {

		Map<String, Double> map = new HashMap<>();
		collectinOfFares.forEach(fare -> {
			fare.getStopid().forEach(stop -> {
				if (map.containsKey(stop)) {
                    map.put(stop, Math.max(map.get(stop),fare.getPrice()));
                } else {
                    map.put(stop, fare.getPrice());
                }
			});
		});
		return map;
	}
	
	
	/**
	 * calculate fare knowing the two stopsid
	 * 
	 * @param tapon
	 * @param tapoff
	 * @return
	 */
	public double calculateFare(Tap tapon, Tap tapoff, Status status) {
		switch (status) {
		case CANCELLED:
			return 0.;

		case COMPLETED:
			Set<String> actualfare = new HashSet<>();
			actualfare.add(tapon.getStopId());
			actualfare.add(tapoff.getStopId());
			return mapOfFares.get(actualfare);
		case INCOMPLETE:
			return mapOfMaximumFares.get(tapon.getStopId());
			
		default:
			return 0.;

		}
	}

}