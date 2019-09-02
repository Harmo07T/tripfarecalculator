package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import littlepay.tripfarecalculator.calculators.StatusCalculator;
import littlepay.tripfarecalculator.configuration.BusTripConfiguration;
import littlepay.tripfarecalculator.io.CsvFileReader;
import littlepay.tripfarecalculator.io.CsvFileWriter;
import littlepay.tripfarecalculator.model.dto.Customer;
import littlepay.tripfarecalculator.model.dto.Status;
import littlepay.tripfarecalculator.model.dto.Tap;
import littlepay.tripfarecalculator.model.dto.TapType;
import littlepay.tripfarecalculator.services.BusTripCalculationService;

@MockitoSettings
public class BusTripCalculationServiceTest {

	    @Mock
	    private CsvFileReader csvFileReader;

	    @Mock
	    private CsvFileWriter csvFileWriter;

	    @Mock
	    private BusTripConfiguration configuration;
	    
	    @Mock
	    private BusTripCalculationService busTripCalculationService;
	    
	    @Mock
	    private Customer customer;
	    
	    @Mock
	    private StatusCalculator statusCalculator;
	    
	    @BeforeEach
	    void setup() {
	        when(configuration.getInputFile()).thenReturn(RandomStringUtils.randomAlphabetic(10));
	        when(configuration.getOutputFile()).thenReturn(RandomStringUtils.randomAlphabetic(10));

	        busTripCalculationService = new BusTripCalculationService(configuration);
	    }

	@Test
	public void generateTripDetails_CompleteTrip() {
		Date startDate = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(startDate);
	    cal.add(Calendar.SECOND, 300);
	    Date endDate =  cal.getTime();
	    String stopOn = "stop1";
	    String stopOff = "stop2";
	    String company = RandomStringUtils.randomAlphabetic(10);
	    String busId = RandomStringUtils.randomAlphabetic(10);	    
	    
		Tap tapOn = new Tap(new Random().nextLong(), startDate, TapType.ON, stopOn, company, busId, customer);
        Tap tapOff = new Tap(new Random().nextLong(), endDate, TapType.OFF, stopOff, company, busId, customer);
        
        Status status = statusCalculator.calculateStatus(tapOn, tapOff);
        assertEquals(Status.COMPLETED, status);
		
	}
}
