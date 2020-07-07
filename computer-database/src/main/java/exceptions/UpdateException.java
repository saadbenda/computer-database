package exceptions;


import org.springframework.dao.DataAccessException;

public class UpdateException extends DataAccessException{

	private static final long serialVersionUID = 4863375458744782042L;
	private static final String UpdateException= "there is a problem issuing the add computer";
	public UpdateException() {
		super(UpdateException);
	}
	public UpdateException(DataAccessException e) {
		super(UpdateException, e);
	}
}
