package exceptions;

import java.text.ParseException;



public class DateParseException extends ParseException {

	private static final long serialVersionUID = -1525968949170634051L;
	private static final String DateParseException = "the date format is wrong, format must be like 2010-12-06";

	public DateParseException() {
		super(DateParseException, 0);

	}
	
	public DateParseException(ParseException e) {
		super(DateParseException, e.getErrorOffset());

	}

}
