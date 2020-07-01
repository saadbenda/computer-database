package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import exceptions.RowMapException;
import model.Company;
import service.Service;


@Component
public class CompanyMapper implements RowMapper<Company>{
	
	@Autowired
	Service service;

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws RowMapException {
		Company company=null;
		try {
		long id = rs.getLong("id");
		String name = rs.getString("name");
		company = service.createCompany(id, name);
		} catch(SQLException e) {
			throw new RowMapException(e);
		}
		return company;
		
	}
	

}
