package littlepay.tripfarecalculator.configuration;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * Get configuration from application.yml
 * @author Harmonie
 *
 */
@ConfigurationProperties(prefix = "bustrip-configuration")
@org.springframework.context.annotation.Configuration
public class BusTripConfiguration {

	private String inputFile;
	private String outputFile;

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}	
	
	 private Collection<Fares> fares;

	    public static class Fares {
	        private List<String> stopid;
	        private Double price;
			public List<String> getStopid() {
				return stopid;
			}
			public void setStopid(List<String> stopid) {
				this.stopid = stopid;
			}
			public Double getPrice() {
				return price;
			}
			public void setPrice(Double price) {
				this.price = price;
			}
	    }

		public Collection<Fares> getFares() {
			return fares;
		}

		public void setFares(Collection<Fares> fares) {
			this.fares = fares;
		}

	
	
}