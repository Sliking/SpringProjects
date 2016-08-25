package pt.link.tutorial.cm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.core.convert.converter.Converter;

/*
 * Not Used
 */
public class StringToCustomDateConverter implements Converter<String , CustomDate>{

	public static final String DATA_PATTERN = "yyyy-MM-dd";
	
	public CustomDate convert(String source) {
		DateFormat df = new SimpleDateFormat(DATA_PATTERN, Locale.UK);
		
		try {
			return new CustomDate(df.parse(source));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

}
