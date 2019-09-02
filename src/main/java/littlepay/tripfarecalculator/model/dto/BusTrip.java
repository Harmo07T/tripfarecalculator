package littlepay.tripfarecalculator.model.dto;

import java.io.Serializable;

public class BusTrip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1842368075167371342L;
	private Tap tapOn, tapOff;
	private Long duration;
	private double fares;
	private Status status;

	public BusTrip() {
	}
	
	public BusTrip(Tap tapOn, Tap tapOff, Long duration, double fare, Status status) {
		this.tapOn = tapOn;
		this.tapOff = tapOff;
		this.duration = duration;
		this.fares = fare;
		this.status = status;
	}

	public Tap getTapOn() {
		return tapOn;
	}

	public void setTapOn(Tap tapOn) {
		this.tapOn = tapOn;
	}

	public Tap getTapOff() {
		return tapOff;
	}

	public void setTapOff(Tap tapOff) {
		this.tapOff = tapOff;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public double getFares() {
		return fares;
	}

	public void setFares(double fares) {
		this.fares = fares;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
