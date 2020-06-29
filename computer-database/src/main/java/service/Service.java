package service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dto.ComputerDto;
import dto.ComputerDto.ComputerDtoBuilder;
import mapper.MapperDto;
import ui.Cli;
import model.Company;
import model.Computer;
import model.Company.CompanyBuilder;
import model.Computer.ComputerBuilder;
import persistence.CompanyDao;
import persistence.ComputerDao;
import mapper.MapperDates;
import validation.Validation;

@org.springframework.stereotype.Service
public class Service {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private ComputerDao computerDao;

	@Autowired
	private MapperDates mapperDates;

	@Autowired
	MapperDto mapperDto;

	public void quit() {
		Cli.scan.close();
	}
	public int addComputer(Computer computer) throws SQLException {
		int error = computerDao.addComputer(computer);
		return error;
	}

	public int deleteComputer(long computerId) throws SQLException {
		int error = computerDao.deleteComputer(computerId);
		return error;
	}

	/*public int updateComputer(String name, String introduced, String discontinued, String companyId, String computerId)
			throws SQLException {

		LocalDate intro = mapperDates.fromStringToLocalDate(introduced);
		LocalDate disco = mapperDates.fromStringToLocalDate(discontinued);
		// validation.validationIntroDisco(intro, disco);
		long cpanyId = Long.parseLong(companyId);
		long cputerId = Long.parseLong(computerId);
		int error = computerDao.updateComputer(name, intro, disco, cpanyId, cputerId);
		return error;
	}*/

	public Company createCompany(long id, String name) {
		Company company = new CompanyBuilder().withId(id).withName(name).build();
		return company;
	}

	public ArrayList<Company> getCompanies() throws SQLException {
		ArrayList<Company> companyList = companyDao.getAllCompanies();
		return companyList;
	}
	
public Computer createComputer(long computerId, String name, LocalDate introduced, LocalDate discontinued, long companyId, String companyName){
		Company company = new CompanyBuilder().withId(computerId).withName(companyName).withId(companyId).build();	
		Computer computer =  new Computer.ComputerBuilder().withName(name).IntroducedIn(introduced)
				.DiscontinuedIn(discontinued).withCompany(company).build();
		return computer;
	}

	public ArrayList<Computer> getComputers() throws Exception {
		ArrayList<Computer> computerList = computerDao.getAllComputers();
		return computerList;
	}

	public ArrayList<Computer> getComputers(long limit, long offset) throws Exception {
		ArrayList<Computer> computerList = computerDao.getAllComputers(limit, offset);
		return computerList;
	}

	public Computer getComputer(long id) throws Exception {
		Computer computer = computerDao.getComputer(id);
		return computer;
	}

	public String findCompanyName(long companyId) throws SQLException {
		String cpany = companyDao.findCompanyName(companyId);
		return cpany;
	}
	/*
	 * public List<Computer> filterComputer(String computerName) throws Exception {
	 * List<Computer> computers = this.getComputers(); List<Computer> cputers =
	 * computers.stream().filter(computer->computer.getName().equals(computerName)).
	 * collect(Collectors.toList()); return cputers; }
	 */

	public long countComputers() throws SQLException {

		return computerDao.countComputers();
	}

	public ArrayList<Computer> searchComputer(String search) throws Exception {

		ArrayList<Computer> computers = computerDao.searchComputer(search);
		return computers;

	}

	public ArrayList<Computer> orderBy(String orderBy) throws Exception {

		ArrayList<Computer> computers = computerDao.orderBy(orderBy, orderBy, orderBy);
		return computers;
	}

	/*********************************************************************************************/

	/*
	 * public void checkChoice(int a, int b) { validation.checkChoice(a,b); }
	 */

	/*
	 * public int addCompany(Company company) { int error =
	 * companyDao.addCompany(company); return error; }
	 */

	/*
	 * public ComputerDto createComputerDto(String computerName, String compId,
	 * String intro, String disco) { long companyId = Long.parseLong(compId); //
	 * Company company = this.createCompany(companyId);
	 * 
	 * ComputerDto compDto = new
	 * ComputerDtoBuilder().withName(computerName).withCompany(compId).IntroducedIn(
	 * intro) .DiscontinuedIn(disco).build();
	 * 
	 * return compDto; }
	 */

	/*
	 * public void addComputer(ComputerDto computer) { /*ComputerDao computerDao =
	 * new ComputerDao(); MapperDto mapper = new MapperDto(); Computer computer =
	 * mapper.fromComputerDtoToComputer(computer) computerDao.addComputer(computer);
	 */
	// }

	/*
	 * public void addCompany(Company company) { CompanyDao companyDao = new
	 * CompanyDao(); companyDao.addCompany(company); }
	 */

	/***
	 * 
	 * find computers and companies
	 * 
	 */

	/*
	 * public Optional<Long> findCompany(String company) { CompanyDao companyDao =
	 * new CompanyDao(); Optional<Long> company1 =
	 * companyDao.findCompanyIdByName(company); return
	 * Optional.ofNullable(company1.orElse(null));
	 * 
	 * }
	 */

	/*
	 * public Optional<String> findCompany(long companyId) { CompanyDao companyDao =
	 * new CompanyDao(); Optional<String> company1 =
	 * companyDao.findCompanyNameById(companyId); if (company1.isPresent()) { return
	 * company1.get(); } return null; }
	 */

	/*
	 * public Optional<Long> findComputer(String computer) { ComputerDao computerDao
	 * = new ComputerDao(); Optional<Long> company1 =
	 * computerDao.findComputerIdByName(computer); return
	 * Optional.ofNullable(company1.orElse(null)); }
	 */

	/*
	 * public ArrayList<Computer> findComputersByName(String computer) { ComputerDao
	 * computerDao = new ComputerDao(); ArrayList<Computer> companyList =
	 * computerDao.findComputersByName(computer); return companyList; }
	 */

	/*
	 * public void display(Object x) { System.out.println(x.toString()); }
	 */

	/*
	 * public void updateComputerName(String name, Computer computer) { ComputerDao
	 * computerDao = new ComputerDao(); long computer_id = computer.getId();
	 * computerDao.updateComputerName(name, computer_id);
	 * 
	 * }
	 */

	/*
	 * public void updateComputerIntro(String date1, Computer computer) {
	 * ComputerDao computerDao = new ComputerDao(); long computer_id =
	 * computer.getId(); LocalDate date = fromStringToLocalDate(date1);
	 * computerDao.updateComputerIntroduced(date, computer_id);
	 * 
	 * }
	 */

	/*
	 * public void updateComputerDisco(String date2, Computer computer) {
	 * ComputerDao computerDao = new ComputerDao(); long computer_id =
	 * computer.getId(); LocalDate date = fromStringToLocalDate(date2);
	 * computerDao.updateComputerDiscontinued(date, computer_id);
	 * 
	 * }
	 */

	/*
	 * public void updateComputerCompany(String company, Computer computer) {
	 * ComputerDao computerDao = new ComputerDao(); long computer_id =
	 * computer.getId(); Optional<Long> company_id = findCompanyIdByName(str);
	 * computerDao.updateComputerCompany(company_id.get(), computer_id);
	 * 
	 * }
	 */

}
