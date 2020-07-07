package mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import dto.CompanyDto;
import dto.CompanyDto.CompanyDtoBuilder;
import exceptions.CompanyIdNumberFormatException;
import exceptions.CompanyNullException;
import model.Company;
import model.Company.CompanyBuilder;


public class MapperDtoTester {
	
	@Autowired
	MapperDto mapperDto;
	
	@Test
	public void testFromCompanyDtoToCompany() throws CompanyIdNumberFormatException, CompanyNullException  {
		CompanyDto companyDto = new CompanyDtoBuilder().withId("1").withName("test").build();
		Company company = new CompanyBuilder().withId(1).withName("test").build();
		assertEquals(company, mapperDto.fromCompanyDtoToCompany(companyDto));
	}
	
	
	@Test
	public void testFromCompanyDtoToCompanyNull() throws CompanyIdNumberFormatException, CompanyNullException{
		assertEquals(null,mapperDto.fromCompanyDtoToCompany(null));
	}
	@Test(expected = CompanyIdNumberFormatException.class)
	public void testFromCompanyDtoToCompanyIdNull() throws CompanyIdNumberFormatException, CompanyNullException{
		
		CompanyDto companyDto = new CompanyDtoBuilder().withId(null).build();
		
		 mapperDto.fromCompanyDtoToCompany(companyDto);
	}
	
	
	
	
	
	


	
	
	
}
