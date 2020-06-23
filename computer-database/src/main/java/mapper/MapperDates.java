package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MapperDates {

	public LocalDate fromDateToLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		date = new Date(date.getTime());
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}
	
	public LocalDate fromStringToLocalDate(String date) {
		LocalDate localDate = null;
		try {
			if(date.equals("")) {
				return null;
			}
			Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			//localDate = this.fromDateToLocalDate(date1);
			localDate = new java.sql.Date(date1.getTime()).toLocalDate();
			System.out.println("localdate "+localDate);
			
			/* Date dateToConvert=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);  
			    return new java.sql.Date(dateToConvert.getTime()).toLocalDate(); */
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return localDate;
	}
	
	
}
