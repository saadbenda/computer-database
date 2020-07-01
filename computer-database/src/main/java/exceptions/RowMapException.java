package exceptions;

import java.sql.SQLException;

public class RowMapException extends SQLException{

	private static final long serialVersionUID = -1525968949170634051L;

	private static final String RowMapException = "error when mapping";
	
	public RowMapException() {
		
	}

	public RowMapException(SQLException e) {
		super(RowMapException, e);
		}
	@Override
	public String getMessage() {
		return RowMapException;
	}
	
	
}
