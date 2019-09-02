package littlepay.tripfarecalculator.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
	public final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	public static Date convertStringToDate(String stringDate) throws ParseException {
		return sdf.parse(stringDate);
	}

	
}
