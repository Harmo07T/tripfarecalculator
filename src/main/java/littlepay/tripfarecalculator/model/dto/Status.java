package littlepay.tripfarecalculator.model.dto;


public enum Status {
	 COMPLETED("COMPLETED"),
	 INCOMPLETE("INCOMPLETE"),
	 CANCELLED("CANCELLED");

	private String status;
	
	
	Status(String status){
		this.status = status;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
