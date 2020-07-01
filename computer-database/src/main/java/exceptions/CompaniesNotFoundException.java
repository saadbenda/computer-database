package exceptions;



import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;


public class CompaniesNotFoundException extends DataAccessException{
	
	private static final long serialVersionUID = 7551308244971850693L;
	private static final String CompaniesNotFoundException= "all companies not found";

	public CompaniesNotFoundException() {
        super(CompaniesNotFoundException);
    }
	
	public CompaniesNotFoundException(DataAccessException e) {
        super(CompaniesNotFoundException, e);
    }
	
}