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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import dto.UserDto;
import validation.Validation;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	Validation validation;
	
	@GetMapping
	public String showLoginForm(WebRequest request, Model model) {
		
		
		return "login";
	}

	@PostMapping
	public String loginUserAccount(@RequestParam(required=true, name="email") String firstName, @RequestParam(required=true, name="password") String password, HttpServletRequest request, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		
		validation.emailExists(user);
		/*try {
	        User registered = userService.registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistException e) {
	        mav.addObject("message", "An account for that username/email already exists.");
	        return mav;
	    }*/
		
		
		
	 System.out.println("-------------- "+password);
		return null;
	    //return new ModelAndView("successRegister", "user", userDto);
		
	}

}



