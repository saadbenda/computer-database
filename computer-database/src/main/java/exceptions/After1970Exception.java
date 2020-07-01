package exceptions;

public class After1970Exception extends Exception {

	private static final long serialVersionUID = -5797719163649423358L;
	private static final String after1970Exception = "the date must be strictly greater than 1970-01-01";

	public After1970Exception(Throwable e) {
		super(after1970Exception, e);
	}

}
