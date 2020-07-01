//package controllers;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import mapper.MapperDto;
//import model.Company;
//import service.Service;
//import validation.Validation;
//
//@Controller
//@RequestMapping("addComputer")
//@SessionAttributes(value="lang")
//public class EditComputerController {
//	@Autowired
//	Service service;
//
//	@Autowired
//	MapperDto mapperDto;
//
//	@Autowired
//	Validation validation;
//
//	@Autowired
//	MessageSource messageSource;
//
//
//	public static final String ADDCOMPUTER = "addComputer";
//	public static final String DASHBOARD = "dashboard";
//	
//	@GetMapping
//	public String doGet(@RequestParam(defaultValue = "en", name = "lang", required = false) String lang, ModelMap modelMap) {
//		try {		
//			ArrayList<Company> companies = service.getCompanies();
//			modelMap.put("companies", companies);
//			modelMap.addAttribute("lang",lang);
//			return ADDCOMPUTER;
//		} catch (SQLException e) {
//			modelMap.addAttribute("errorMessage", e.getMessage());
//			modelMap.addAttribute("stackTrace", ExceptionUtils.getStackTrace(e));
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//	
//}
