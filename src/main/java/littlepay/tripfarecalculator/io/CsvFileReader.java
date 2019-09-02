package littlepay.tripfarecalculator.io;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import littlepay.tripfarecalculator.model.dto.Tap;
import littlepay.tripfarecalculator.model.dto.TapType;
import littlepay.tripfarecalculator.model.dto.Customer;
import littlepay.tripfarecalculator.utils.Utils;

public class CsvFileReader {
	private static final Logger logger = LoggerFactory.getLogger(CsvFileReader.class);
	/**
	 * Read CSV File
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public List<String[]> readCSV(String fileName) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(fileName));

		CSVReader csvReader = new CSVReaderBuilder(reader).withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
				.withSkipLines(1).build();
		return csvReader.readAll();
	}
	
	public List<Tap> parseData(List<String[]> allData){
		logger.info("Start parsing csv file");
		List<Tap> busTapList = new ArrayList<>();
		// Read CSV line by line and use the string array as you want
		for (String[] element : allData) {

			Tap tap = new Tap();
			tap.setId(Long.parseLong(element[0]));
			try {
				tap.setDate(Utils.convertStringToDate(element[1]));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			tap.setTapType(TapType.valueOf(element[2]));
			tap.setStopId(element[3]);
			tap.setCompany(element[4]);
			tap.setBusId(element[5]);
			tap.setCustomer(new Customer(Long.parseLong(element[6])));
			busTapList.add(tap);
		}
		return busTapList;
	}

}
