package exceptions;

public class DiscoMustIfIntroException extends Exception{

	private static final long serialVersionUID = -1525968949170634051L;
	
	private static final String discoMustIfIntroException= "if there is a disconinued date, there must be an introduced date";

	public DiscoMustIfIntroException() {
        super(discoMustIfIntroException);
    }

}
