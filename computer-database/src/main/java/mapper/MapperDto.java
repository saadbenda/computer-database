package mapper;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import dto.CompanyDto;
import model.Company;
import model.Company.CompanyBuilder;
import model.Computer;
import dto.CompanyDto.CompanyDtoBuilder;
import dto.ComputerDto;
import dto.ComputerDto.ComputerDtoBuilder;
import model.Computer.ComputerBuilder;

import mapper.MapperDates;


@Component
public class MapperDto {
	
	
	
	
	public CompanyDto fromCompanyToCompanyDto(Company company) {
		String id = Long.toString(company.getId());
		String name =company.getName();
		
		CompanyDto compDto = new CompanyDtoBuilder().withId(id).withName(name).build();
		return compDto;
	}
	/*public ComputerDto fromComputerToComputerDto(Computer computer) {
		String id = Long.toString(company.getId());
		String name =company.getName();
		
		CompanyDto compDto = new CompanyDtoBuilder().withId(id).withName(name).build();
		return compDto;
	}*/
	
	
	public Company fromCompanyDtoToCompany(CompanyDto companyDto) {
		Company company = null;
		if (companyDto!=null) {
		long companyId = Long.parseLong(companyDto.getId());
		String companyName = companyDto.getName();
		company = new CompanyBuilder().withName(companyName).withId(companyId).build();
		}
		return company;
	}
	
	
	public Computer fromComputerDtoToComputer(ComputerDto computerDto) {
		MapperDates mapperDates = new MapperDates();
		//long companyId = Long.parseLong(computerDto.getCompany());
		//Company company = service.findCompanyById(companyId);
		String name = computerDto.getName();
		String intro = computerDto.getIntroduced();
		LocalDate introduced = mapperDates.fromStringToLocalDate(intro);
		String disco = computerDto.getDiscontinued();
		LocalDate discontinued = mapperDates.fromStringToLocalDate(disco);
		CompanyDto companyDto = computerDto.getCompanyDto();
		Company company = this.fromCompanyDtoToCompany(companyDto);
		Computer computer = new ComputerBuilder().withName(name).IntroducedIn(introduced).DiscontinuedIn(discontinued).withCompany(company).build();
		return computer;
	}
	
	public ComputerDto fromComputerToComputerDto(Computer computer) {
		MapperDto mapperDto = new MapperDto();
		ComputerDtoBuilder computerDtoBuilder = new ComputerDtoBuilder();
		String id = String.valueOf(computer.getId());
		String name = computer.getName();
		if (computer.getIntroduced()!=null) {
			String introduced = computer.getIntroduced().toString();
			computerDtoBuilder.IntroducedIn(introduced);
		}
		if (computer.getDiscontinued()!=null) {
			String discontinued = computer.getDiscontinued().toString();
			computerDtoBuilder.DiscontinuedIn(discontinued);
		}		
		CompanyDto companyDto = mapperDto.fromCompanyToCompanyDto(computer.getCompany());
		computerDtoBuilder.withId(id).withName(name).withCompanyDto(companyDto);
		ComputerDto computerDto = computerDtoBuilder.build();
		return computerDto;
	}
}
