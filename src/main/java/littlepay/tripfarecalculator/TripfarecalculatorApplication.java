package littlepay.tripfarecalculator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import littlepay.tripfarecalculator.services.BusTripCalculationService;

@SpringBootApplication
public class TripfarecalculatorApplication   {
	private static final Logger logger = LoggerFactory.getLogger(TripfarecalculatorApplication.class);
	public static void main(String[] args) {
		logger.info("Load application context");
		ApplicationContext applicationContext = SpringApplication.run(TripfarecalculatorApplication.class, args);
		BusTripCalculationService busTripCalculationService = applicationContext.getBean(BusTripCalculationService.class);
		logger.info("Calculate Trip fares");
		busTripCalculationService.calculateFares();
		logger.info("Exit system");
		System.exit(0);
	}
	
	

}
