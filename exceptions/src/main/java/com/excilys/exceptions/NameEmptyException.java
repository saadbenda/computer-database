package exceptions;

public class NameEmptyException extends Exception {

	private static final long serialVersionUID = -1525968949170634051L;

	private static final String NameEmptyException = "computer name cannot be empty";

	public NameEmptyException(Throwable e) {
		super(NameEmptyException, e);
	}
	
	public NameEmptyException() {
		super(NameEmptyException);
	}
}
