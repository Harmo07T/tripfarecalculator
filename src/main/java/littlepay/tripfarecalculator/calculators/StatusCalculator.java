package littlepay.tripfarecalculator.calculators;

import littlepay.tripfarecalculator.model.dto.Status;
import littlepay.tripfarecalculator.model.dto.Tap;

public class StatusCalculator {

	/**
	 * generate status
	 * @param tapon
	 * @param tapoff
	 * @return status
	 */
	public static Status calculateStatus(Tap tapon, Tap tapoff) {
		Status status;
		//if one tap is null
		if(tapoff == null) {
			status = Status.INCOMPLETE;
		}
		//else if stopsid are the same
		else if (tapon.getStopId().equals(tapoff.getStopId())) {
			status = Status.CANCELLED;
		}
		
		//else if stopsid non null and different
		else {
			status = Status.COMPLETED;
		}
		return status;
	}
	
}
