package littlepay.tripfarecalculator.model.dto;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class Tap implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -9096333229067803443L;
	private @Id Long id;
	private Date date;
	private TapType tapType;
	private String stopId;
	private String company;
	private String busId;
	private Customer customer;
	 
	 public Tap(){
		 
	 }
	 public Tap(Long id, Date date, TapType tapType, String stopId, String company, String busId, Customer customer){
		 this.id = id;
		 this.date = date;
		 this.tapType = tapType;
		 this.stopId = stopId;
		 this.company = company;
		 this.busId = busId;
		 this.customer = customer;
	 }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TapType getTapType() {
		return tapType;
	}
	public void setTapType(TapType tapType) {
		this.tapType = tapType;
	}
	public String getStopId() {
		return stopId;
	}
	public void setStopId(String stopId) {
		this.stopId = stopId;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	 

}
