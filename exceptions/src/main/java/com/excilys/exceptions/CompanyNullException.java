package exceptions;

public class CompanyNullException extends NullPointerException {

	private static final long serialVersionUID = -1525968949170634051L;
	private static final String CompanyNullException = "the Company cannot be null";

	public CompanyNullException() {
		super(CompanyNullException);

	}
	
	

}