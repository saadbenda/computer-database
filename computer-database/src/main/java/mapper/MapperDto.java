package mapper;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dto.CompanyDto;
import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import dto.ComputerDto;
import model.Computer.ComputerBuilder;

@Component
public class MapperDto {
	@Autowired
	MapperDates mapperDates;

	public Company fromCompanyDtoToCompany(CompanyDto companyDto) throws NumberFormatException {
		long companyId;
		if (companyDto == null) {
			return null;
		}
		companyId = Long.parseLong(companyDto.getId());
		String companyName = companyDto.getName();
		Company company = new CompanyBuilder().withName(companyName).withId(companyId).build();
		return company;
	}

	public Computer fromComputerDtoToComputer(ComputerDto computerDto) throws Exception {
		String name = computerDto.getName();
		if (name == "") {
			throw new Exception("computer name cannot be empty");
		}
		String intro = computerDto.getIntroduced();
		LocalDate introduced = mapperDates.fromStringToLocalDate(intro);
		String disco = computerDto.getDiscontinued();
		LocalDate discontinued = mapperDates.fromStringToLocalDate(disco);
		CompanyDto companyDto = computerDto.getCompanyDto();
		Company company = this.fromCompanyDtoToCompany(companyDto);
		Computer computer = new ComputerBuilder().withName(name).IntroducedIn(introduced).DiscontinuedIn(discontinued)
				.withCompany(company).build();
		return computer;
	}

}
