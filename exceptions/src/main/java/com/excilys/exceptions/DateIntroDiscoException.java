package exceptions;

public class DateIntroDiscoException extends Exception{

	private static final long serialVersionUID = -5797719163649423358L;
	private static final String dateIntroDiscoException="introdudction date must be before or equal to the discontinued date";

	public  DateIntroDiscoException() {
		super(dateIntroDiscoException);
	}
}