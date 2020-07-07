package exceptions;



public class DateNullException extends NullPointerException {

	private static final long serialVersionUID = -1525968949170634051L;
	private static final String DateNullException = "the date cannot be null";

	public DateNullException() {
		super(DateNullException);

	}
	
	

}