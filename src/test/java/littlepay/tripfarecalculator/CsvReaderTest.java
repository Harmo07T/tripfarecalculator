package littlepay.tripfarecalculator;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import littlepay.tripfarecalculator.io.CsvFileReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvReaderTest {
	
	@Test
	public void readCsv() throws Exception{
		List<String[]> expected = new ArrayList<>();
		expected.add(new String[]{"1", "22-01-2018 13:00:00", "ON", "Stop1", "Company1", "Bus37", "5500005555555559"});
		List<String[]> actual =new ArrayList<>();
		CsvFileReader csvProcessor = new CsvFileReader();
		try {
			actual = csvProcessor.readCSV("src/test/resources/taps.csv");
		} catch (IOException e) {
			
		}
		 assertThat(actual,IsIterableContainingInOrder.contains(expected.toArray()));
	
	}

}
