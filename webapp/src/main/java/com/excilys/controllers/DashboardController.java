package controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mapper.MapperDto;
import model.Company;
import model.Computer;
import persistence.CompanyRepository;
import persistence.ComputerRepository;
import service.Service;
import validation.Validation;

@Controller
@RequestMapping("dashboard")
@SessionAttributes(value = {"id","page","pageSize","order"})

public class DashboardController {
	@Autowired
	Service service;

	@Autowired
	MapperDto mapperDto;

	@Autowired
	Validation validation;

	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	ComputerRepository computerRepo;
	


	
	public static final String DASHBOARD = "dashboard";
	
	
	@GetMapping
	public String doGet(ModelMap modelMap, HttpSession session) {		
		List<Computer> computers;
		//Pageable page = PageRequest.of(offset, limit,Direction.ASC,state);
		
		page = new PageRequest(page, pageSize, Direction.ASC);
		computers=computerRepo.findByNameContainingIgnoreCase((String) session.getAttribute("search"), page);
		
		
		//List<Computer> computers = computerRepo.searchByName(session.getAttribute(name));
		
		
		
		//List<Computer> computers=computerRepo.findAll();
		modelMap.addAttribute("computers",computers);		
		return DASHBOARD;
	}
	
	@PostMapping
	public String doPost(@ModelAttribute(name="search") String search, @ModelAttribute(name="page") String page, @ModelAttribute(name="result") String result) {
				
		System.out.println(search);
		System.out.println(page);
		System.out.println(result);			
		return DASHBOARD;
		
	}
	
	@PostMapping(value="/delete")
	public ModelAndView deleteComputer(@RequestParam(value = "selection") String selection) {

		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		List<String> computers = Arrays.asList(selection.split(","));
		for (String s : computers) {
			computerService.deleteComputer(Integer.parseInt(s));
		}

		return modelAndView;
	}
	
	@PostMapping(value="/order")
	public ModelAndView order(@RequestParam(value = "selection") String selection) {

		ModelAndView modelAndView = new ModelAndView("redirect:/dashboard");
		List<String> options = Arrays.asList(selection.split(","));
		for (String s : options) {
			computerService.deleteComputer(Integer.parseInt(s));
		}

		return modelAndView;
	}
		
	
	
	
	
}
