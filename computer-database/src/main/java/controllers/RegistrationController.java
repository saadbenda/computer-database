package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDto;
import validation.Validation;

@Controller
@RequestMapping("/register")
//@SessionAttributes(value = "lang")

//@Validated
public class RegistrationController {
	
	@Autowired
	Validation validation;
	
	@GetMapping
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";
	}
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserDto user, HttpServletRequest request, ModelMap model, Errors errors) {
		ModelAndView mav = new ModelAndView();
		
		if (errors.hasErrors()) {
			errors.getFieldErrors();
		}
		
		validation.emailExists(user);
		
		/*try {
	        User registered = userService.registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistException e) {
	        mav.addObject("message", "An account for that username/email already exists.");
	        return mav;
	    }*/
		
		
		
	 System.out.println("-------------- "+userDto);
		return null;
	    //return new ModelAndView("successRegister", "user", userDto);
		
	}
}
