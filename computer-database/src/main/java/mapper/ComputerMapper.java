package mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import model.Computer;
import service.Service;

@Component
public class ComputerMapper implements RowMapper<Computer> {

	@Autowired
	Computer computer;
	
	@Autowired
	Service service;
	
	@Autowired
	MapperDates mapperDates;

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException  {

		long companyId = rs.getLong("company.id");
		String companyName = rs.getString("company.name");
		long computerId = rs.getLong("computer.id");
		String computerName = rs.getString("computer.name");
		Date intro = rs.getDate("introduced");
		LocalDate introduced = mapperDates.fromDateToLocalDate(intro);
		Date disco = rs.getDate("discontinued");
		LocalDate discontinued = mapperDates.fromDateToLocalDate(disco);
		
			try {
				computer = service.createComputer(computerName, introduced, discontinued, companyId, companyName,
						computerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		

		return computer;
		
	}

}
