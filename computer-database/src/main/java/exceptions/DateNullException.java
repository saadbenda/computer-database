package exceptions;

import java.text.ParseException;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class DateNullException extends NullPointerException {

	private static final long serialVersionUID = -1525968949170634051L;
	private static final String DateParseException = "the date cannot be empty";

	public DateNullException() {
		super(DateParseException);

	}
	
	

}