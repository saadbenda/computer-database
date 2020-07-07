package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
	public static final String ADDCOMPUTER = "addComputer";

	public HomeController() {

	}

	public String doGet() {
		return ADDCOMPUTER;
	}

}
