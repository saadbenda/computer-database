package controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.websocket.Session;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.CompanyDto;
import dto.ComputerDto;
import dto.CompanyDto.CompanyDtoBuilder;
import dto.ComputerDto.ComputerDtoBuilder;
import mapper.MapperDto;
import model.Company;
import model.Computer;
import service.Service;
import validation.Validation;

@Controller
@RequestMapping("addComputer")
@SessionAttributes(value="lang")
public class AddComputerController {

	@Autowired
	Service service;

	@Autowired
	MapperDto mapperDto;

	@Autowired
	Validation validation;

	@Autowired
	MessageSource messageSource;


	public static final String ADDCOMPUTER = "addComputer";
	public static final String DASHBOARD = "dashboard";
	public static final String ERROR400 = "400";
	public static final String ERROR500 = "500";
	
	@GetMapping
	public String doGet(@RequestParam(defaultValue = "en", name = "lang", required = false) String lang, ModelMap modelMap) {
		try {		
			ArrayList<Company> companies = service.getCompanies();
			modelMap.put("companies", companies);
			modelMap.addAttribute("lang",lang);
			return ADDCOMPUTER;
		} catch (SQLException e) {
			modelMap.addAttribute("errorMessage", e.getMessage());
			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			return ERROR500;
		}

	}

	@PostMapping	
	public String doPost(@RequestParam(name = "computerName") String computerName,
			/* GET PARAM */
			@RequestParam(name = "introduced", required = false) String intro,
			@RequestParam(name = "discontinued", required = false) String disco,
			@RequestParam(name = "companyId") String companyId, 
			@RequestParam(name = "language", required=false) String language, 
			ModelMap modelMap) {

		// DTO
		CompanyDto companyDto = new CompanyDtoBuilder().withId(companyId).build();
		ComputerDto computerDto = new ComputerDtoBuilder().withName(computerName).IntroducedIn(intro)
				.DiscontinuedIn(disco).withCompanyDto(companyDto).build();
		try {
			// Mapping
			Computer computer = mapperDto.fromComputerDtoToComputer(computerDto);
			// Validation
			validation.createComputer(computer);
			// Service
			service.addComputer(computer);

		} catch (NumberFormatException e) {
			modelMap.addAttribute("errorMessage", e.getMessage());
			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			return ERROR400;
			
		} catch (ParseException e) {
			modelMap.addAttribute("errorMessage", e.getMessage());
			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			return ERROR400;

		} catch (SQLException e) {
			modelMap.addAttribute("errorMessage", e.getMessage());
			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			return ERROR500;
			
		} catch (NullPointerException e) {
			modelMap.addAttribute("errorMessage", e.getMessage());
			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			return ERROR500;
			
		} catch (Exception e) {
			modelMap.addAttribute("errorMessage", e.getMessage());
			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
			e.printStackTrace();
			return ERROR500;
			
		}
		return DASHBOARD;

	}

}
