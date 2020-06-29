package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class MapperDates {
	public LocalDate fromDateToLocalDate(Date date) throws NullPointerException{
			date = new Date(date.getTime());
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return localDate;		
	}

	public LocalDate fromStringToLocalDate(String date) throws ParseException {
		if (date.equals("")) {
			return null;
		}
		Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		LocalDate localDate = new java.sql.Date(date1.getTime()).toLocalDate();
		return localDate;

	}

}
