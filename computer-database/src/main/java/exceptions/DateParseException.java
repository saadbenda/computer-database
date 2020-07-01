package exceptions;

import java.text.ParseException;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class DateParseException extends ParseException {

	private static final long serialVersionUID = -1525968949170634051L;
	private static final String DateParseException = "if there is a disconinued date, there must be an introduced date";

	public DateParseException() {
		super(DateParseException, 0);

	}
	
	public DateParseException(ParseException e) {
		super(ExceptionUtils.getStackTrace(e), e.getErrorOffset());

	}

}
