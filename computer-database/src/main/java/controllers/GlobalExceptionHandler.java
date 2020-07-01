package controllers;

import java.net.http.HttpHeaders;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@EnableWebMvc

public class GlobalExceptionHandler{

	private static final String ERROR403 = "403";
	private  static final String ERROR404 = "404";
	private static final String ERROR500 = "500";

	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoHandlerFound(Exception e, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("errorMessage", e.getMessage());
	    modelAndView.addObject("stackTrace", ExceptionUtils.getStackTrace(e));    
	    modelAndView.setViewName(ERROR404);
	    return modelAndView;
    }
	
	
	
	
	
	/*
	@ExceptionHandler
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse requestHandlingNoHandlerFound(final NoHandlerFoundException ex) {
        System.out.println("hey");
    }
	
	
	@ExceptionHandler({ Exception.class, Exception.class })
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        
        		
        		
        		
	}
	
	
	 @ExceptionHandler(value 
		      = { IllegalArgumentException.class, IllegalStateException.class })
		    protected ResponseEntity<Object> handleConflict(
		      RuntimeException ex, WebRequest request) {
		        String bodyOfResponse = "This should be application specific";
		        return handleExceptionInternal(ex, bodyOfResponse, 
		          new HttpHeaders(), HttpStatus.CONFLICT, request);
		    }
		   */

}
