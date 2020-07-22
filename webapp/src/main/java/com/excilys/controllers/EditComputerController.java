package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import dto.CompanyDto;
import dto.ComputerDto;
import exceptions.DateParseException;
import exceptions.NameEmptyException;
import mapper.MapperDto;
import model.Company;
import model.Computer;
import persistence.CompanyRepository;
import persistence.ComputerRepository;
import service.Service;
import validation.Validation;

@Controller
@RequestMapping("editComputer")

@SessionAttributes(value = {"lang", "id"})


public class EditComputerController {
	@Autowired
	Service service;

	@Autowired
	MapperDto mapperDto;

	@Autowired
	Validation validation;

	@Autowired
	MessageSource messageSource;
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	ComputerRepository computerRepo;
	
	public static final String EDITCOMPUTER = "editComputer";
	public static final String DASHBOARD = "dashboard";
	
	@GetMapping
	public String doGet(@RequestParam(required=false, name="id")  String id, ModelMap modelMap, HttpSession session) {
		
		if ((boolean) session.getAttribute("id")) {
			id=(String) session.getAttribute("id");
		}
		Computer computer = computerRepo.findById(id);
		modelMap.addAttribute("computer", computer);
		List<Company> companies = companyRepo.findAll();
		modelMap.addAttribute("companies", companies);
		return EDITCOMPUTER;
		
	}
	
	@PostMapping
	public String doPost(@ModelAttribute(name="Computer") ComputerDto computerDto, CompanyDto companyDto, ModelMap modelMap) throws DateParseException, NameEmptyException {
		
		/*computerDto.setCompany(companyDto);
		Computer computer = mapperDto.fromComputerDtoToComputer(computerDto);
		//computerRepo.update(computer);
		*/
		System.out.println("------------------------" +computerDto);
		System.out.println("------------------------" +companyDto);
		
		//computerDto.setCompany(companyDto);
		System.out.println(computerDto.toString());
		
		return DASHBOARD;
		
	}
	
}
