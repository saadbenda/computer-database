package exceptions;

import java.text.ParseException;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class CompanyIdNumberFormatException extends NumberFormatException{
	private static final long serialVersionUID = -1525968949170634051L;
	private static final String CompanyIdNumberFormatException = "there is a problem issuing the conversion of company Id ";

	public CompanyIdNumberFormatException() {
		super(CompanyIdNumberFormatException);

	}
	
	public CompanyIdNumberFormatException(Throwable e) {
		super(CompanyIdNumberFormatException);

	}
}
