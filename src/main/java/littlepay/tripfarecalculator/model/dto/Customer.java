package littlepay.tripfarecalculator.model.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2290018573321907481L;
	private @Id Long id;
	private Long PAN;

	public Customer(Long PAN) {
		this.PAN = PAN;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPAN() {
		return PAN;
	}

	public void setPAN(Long pAN) {
		PAN = pAN;
	}

}
