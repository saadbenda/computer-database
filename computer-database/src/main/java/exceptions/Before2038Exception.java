package exceptions;

public class Before2038Exception extends Exception{

	private static final long serialVersionUID = -5797719163649423358L;
	
	private static final String before2038Exception= "the date must be strictly lesser than 2038-01-19";

	public  Before2038Exception(Throwable e) {
		super(before2038Exception,e);
	}
}