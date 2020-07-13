package exceptions;

import org.openqa.selenium.NoSuchElementException;

public class EmailExistsException extends NoSuchElementException{
	
	private static final long serialVersionUID = 1307769555374335763L;
	final static String REASON = "user not found";

	public EmailExistsException() {
		super(REASON);
	}
	
	
}
