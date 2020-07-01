package exceptions;

import java.text.ParseException;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class DateNullException extends NullPointerException {

	private static final long serialVersionUID = -1525968949170634051L;
	private static final String DateParseException = "if there is a disconinued date, there must be an introduced date";

	public DateNullException() {
		super(DateParseException);

	}
	
	public DateNullException(NullPointerException e) {
		super(DateParseException, e);

	}

}