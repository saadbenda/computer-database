package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import mapper.MapperDates;
import model.Company;
import model.Computer;
import service.Service;

public class Validation {


	public void createComputer(Computer computer) throws Exception {
		String computerName = computer.getName();
		this.computerName(computerName);
		LocalDate intro = computer.getIntroduced();
		LocalDate disco = computer.getDiscontinued();
		this.dateIntroDisco(intro, disco);
	}
	//check not empty
	public void computerName(String name) throws Exception {
		if (name.equals("null")) {
			throw new Exception("name cannot be null");
		}
	}
	//check not before 1970
	public LocalDate dateFormat(String date) throws ParseException {
		MapperDates mapperDates = new MapperDates();
		LocalDate discontinued;
		if (date.equals("null")) {
			discontinued = null;
		} else {
			discontinued = mapperDates.fromStringToLocalDate(date);
		}
		return discontinued;
	}

	public void dateIntroDisco(LocalDate intro, LocalDate disco) throws Exception {
		if (disco != null && intro != null) {
			if (intro.compareTo(disco) > 0) {
				throw new Exception("introdudction date must be lower than the discontinued date");
			}
		}
	}
	
	/*public List<Computer> existingComputerName(String computerName) throws Exception {
		Service service = new Service();
		List<Computer> computers = service.getComputers().stream().filter(computer->computer.getName().equals(computerName)).collect(Collectors.toList());
		if (!computers.isEmpty()) {
			throw new Exception("this name already exist");
		}
		return computers;

	}*/
	
	/***************************************************************************************/

	/*
	 * public LocalDate validationIntroduced(String intro) throws ParseException {
	 * LocalDate introduced; if (intro.equals("null")) { introduced = null; } else {
	 * introduced =service.fromStringToLocalDate(intro); } return introduced; }
	 */

	/*public long createCompany(String company) throws Exception {
		System.out.println(company.length());
		if (company.equals("null")) {
			throw new Exception("le nom de l'entreprise ne peut être null");
		}
		Optional<Long> company_id = service.findCompanyIdByName(company);
		System.out.println("coucou1");
		// System.out.println("company_id"+company_id.get());
		if (!company_id.isPresent()) {
			System.out.println("coucou2");
			ArrayList<Company> companyList = service.getListCompanies();

			// throw new Exception("cette entreprise n'existe pas, voici la liste des
			// entreprises disponibles : "+
			// companyList.stream().map(c->c.getName()).reduce("\t", String::concat));
		}

		return company_id.get();
	}*/

	/*public void validationChoice(int answer) throws Exception {
		if (!(answer >= 0 && answer < 13)) {
			throw new Exception("votre choix doit etre compris entre 0 et 13");
		}
	}*/

	

}
