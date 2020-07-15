package mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import exceptions.DateNullException;
import exceptions.DateParseException;

@Component
public class MapperDates {
	public LocalDate fromDateToLocalDate(Date date) throws DateNullException{
		if (date==null) {
			throw new DateNullException();
		}	
		date = new Date(date.getTime());
			LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return localDate;		
	}

	public LocalDate fromStringToLocalDate(String date) throws DateParseException {
		if (date.equals("")) {
			return null;
		}
		Date date1 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			throw new DateParseException(e);
		}
		LocalDate localDate = new java.sql.Date(date1.getTime()).toLocalDate();
		return localDate;

	}

}
