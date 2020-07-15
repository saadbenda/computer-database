package com.excilys.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomeController {
	public static final String ADDCOMPUTER = "addComputer";
	
	@GetMapping(value="/")
	public String doGet() {
		return ADDCOMPUTER;
	}

}
