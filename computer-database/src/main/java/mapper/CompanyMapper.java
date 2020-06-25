package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import model.Company;
import service.Service;


@Component
public class CompanyMapper implements RowMapper<Company>{
	
	@Autowired
	Service service;

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {

		long id = rs.getLong("id");
		String name = rs.getString("name");
		System.out.println(name);
		
		Company company = service.createCompany(id, name);
		return company;
		
	}
	

}
