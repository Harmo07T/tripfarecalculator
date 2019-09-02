package littlepay.tripfarecalculator.calculators;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DurationCalculator {
	/*
	 * calculate duration between two dates in seconds
	 */
	public static long calculateDurationBetweenTwoDates(Date dateOn, Date dateOff) {
		return TimeUnit.MILLISECONDS.toSeconds(dateOff.getTime() - dateOn.getTime());
	}
}
