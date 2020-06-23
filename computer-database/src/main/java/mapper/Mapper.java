package mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import service.Service;
import model.Company;
import model.Computer;
import mapper.MapperDates;

@Component
public class Mapper {
	//Service service=new Service();
	//MapperDates mapperDates = new MapperDates();
	
	@Autowired
	MapperDates mapperDates;
	@Autowired
	Service service;
	
	public Computer fromResultSetToComputer(ResultSet rs) throws Exception {
		
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
		
		long id = rs.getLong("id");
		String name = rs.getString("name");
		Company company = service.createCompany(id, name);
		return company;
		
	}

}
