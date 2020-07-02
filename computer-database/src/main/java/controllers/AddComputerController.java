package controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import javax.websocket.Session;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import exceptions.After1970Exception;
import exceptions.Before2038Exception;
import exceptions.CompaniesNotFoundException;
import exceptions.DateIntroDiscoException;
import exceptions.DateParseException;
import exceptions.DiscoMustIfIntroException;
import exceptions.NameEmptyException;
import exceptions.RowMapException;
import mapper.MapperDto;
import model.Company;
import model.Computer;
import service.Service;
import validation.Validation;

@Controller
@RequestMapping("addComputer")
@SessionAttributes(value="lang")
//@Validated
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
	public String doGet(@RequestParam(name = "lang", required = false) String lang, ModelMap modelMap) throws CompaniesNotFoundException, RowMapException {
		
			ArrayList<Company> companies = service.getCompanies();
			modelMap.put("companies", companies);
			modelMap.addAttribute("lang",lang);				
			return ADDCOMPUTER;
	}

	@PostMapping	
	public String doPost(@RequestParam(name = "computerName") String computerName,
			/* GET PARAM */
			@RequestParam(name = "introduced", required = false) String intro,
			@RequestParam(name = "discontinued", required = false) String disco,
			@RequestParam(name = "companyId") String companyId, @Valid ComputerDto computerDto2,
			ModelMap modelMap, BindingResult bindingResult) throws DateParseException, NameEmptyException, DiscoMustIfIntroException, After1970Exception, Before2038Exception, DateIntroDiscoException {
		
		// DTO
		CompanyDto companyDto = new CompanyDtoBuilder().withId(companyId).build();
		ComputerDto computerDto = new ComputerDtoBuilder().withName(computerName).IntroducedIn(intro)
				.DiscontinuedIn(disco).withCompanyDto(companyDto).build();
		
		if (bindingResult.hasErrors()) {
			System.out.println("----- error ----");
		}
		
		
			// Mapping
			//Computer computer = mapperDto.fromComputerDtoToComputer(computerDto);
			// Validation
			//validation.createComputer(computer);
			// Service
			//service.addComputer(computer);			
			
			return DASHBOARD;

	}

}
