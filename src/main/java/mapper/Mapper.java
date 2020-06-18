package mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import service.Service;
import model.Company;
import model.Computer;
import mapper.MapperDates;

public class Mapper {
	//Service service=new Service();
	//MapperDates mapperDates = new MapperDates();
	
	public Computer fromResultSetToComputer(ResultSet rs) throws Exception {
		MapperDates mapperDates = new MapperDates();
		Service service=new Service();
		//long companyId = rs.getLong("company_id");
		long companyId= rs.getLong("company.id");
		String companyName = rs.getString("company.name");
		//Company company = service.createCompany(companyId, companyName);
		//String companyName = rs.getString("companyName");
		long computerId = rs.getLong("computer.id");
		String computerName = rs.getString("computer.name");
		Date intro = rs.getDate("introduced");
		LocalDate introduced = mapperDates.fromDateToLocalDate(intro);
		Date disco = rs.getDate("discontinued");
		LocalDate discontinued = mapperDates.fromDateToLocalDate(disco);
		
		
		//LocalDate intro = mapperDates.fromDateToLocalDate(introduced);
		//LocalDate disco = mapperDates.fromDateToLocalDate(discontinued);
		
		Computer computer = service.createComputer(computerName, introduced, discontinued, companyId,companyName, computerId);

		return computer;
	}

	public Company fromResultSetToCompany(ResultSet rs) throws SQLException {
		Service service=new Service();
		long id = rs.getLong("id");
		String name = rs.getString("name");
		Company company = service.createCompany(id, name);
		return company;
		
	}

}
