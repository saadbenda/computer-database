package mapper;

import java.text.ParseException;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dto.CompanyDto;
import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import dto.ComputerDto;
import exceptions.DateNumberFormatException;
import exceptions.DateParseException;
import exceptions.NameEmptyException;
import model.Computer.ComputerBuilder;

@Component
public class MapperDto {
	@Autowired
	MapperDates mapperDates;

	public Company fromCompanyDtoToCompany(CompanyDto companyDto) throws DateNumberFormatException {
		long companyId;
		Company company = null;
		if (companyDto == null) {
			return null;
		}
		try{
			companyId = Long.parseLong(companyDto.getId());
			String companyName = companyDto.getName();
			company = new CompanyBuilder().withName(companyName).withId(companyId).build();
		}catch (NumberFormatException e) {
			throw new DateNumberFormatException(e);
		}
		return company;
	}

	public Computer fromComputerDtoToComputer(ComputerDto computerDto) throws NameEmptyException, DateParseException {
		String name = computerDto.getName();
		Computer computer=null;
		if (name == "") {
			throw new NameEmptyException();
		}
		try {
			String intro = computerDto.getIntroduced();	
			String disco = computerDto.getDiscontinued();			
			LocalDate introduced = mapperDates.fromStringToLocalDate(intro);
			LocalDate discontinued = mapperDates.fromStringToLocalDate(disco);			
			CompanyDto companyDto = computerDto.getCompanyDto();
			Company company = this.fromCompanyDtoToCompany(companyDto);
			computer = new ComputerBuilder().withName(name).IntroducedIn(introduced).DiscontinuedIn(discontinued)
					.withCompany(company).build();
		} catch (ParseException e) {
			throw new DateParseException(e);
		}
		
		return computer;
	}

}
