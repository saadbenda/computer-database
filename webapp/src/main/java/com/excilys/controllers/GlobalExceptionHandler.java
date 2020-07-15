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

import exceptions.After1970Exception;
import exceptions.Before2038Exception;
import exceptions.CompaniesNotFoundException;
import exceptions.DateIntroDiscoException;
import exceptions.DateNullException;
import exceptions.DateParseException;
import exceptions.DiscoMustIfIntroException;
import exceptions.NameEmptyException;
import exceptions.RowMapException;
import exceptions.UpdateException;

@ControllerAdvice
public class GlobalExceptionHandler{
	private static final String ERROR400 = "400";
	private static final String ERROR403 = "403";
	private  static final String ERROR404 = "404";
	private static final String ERROR500 = "500";
	@ResponseBody
	@ExceptionHandler({RowMapException.class, CompaniesNotFoundException.class, UpdateException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleInternalServerError(Exception e, WebRequest request) {
		return display(e, ERROR500);
    }
	
	
	/*@ExceptionHandler(RowMapException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ModelAndView handForbidden(Exception e, WebRequest request) {
		return display(e, ERROR403);
    }*/
	@ResponseBody
	@ExceptionHandler({After1970Exception.class, Before2038Exception.class, DateIntroDiscoException.class, DateNullException.class, DateParseException.class, DiscoMustIfIntroException.class, NameEmptyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBadRequest(Exception e, WebRequest request) {
		return display(e, ERROR400);
	    
    }
	@ResponseBody
	@ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNotFound(Exception e, WebRequest request) {
		return display(e, ERROR404); 
    }
	
	public ModelAndView display(Exception e, String errorPage) {
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("errorMessage", e.getMessage());
	    modelAndView.addObject("stackTrace", ExceptionUtils.getStackTrace(e));    
	    modelAndView.setViewName(errorPage);
	    e.printStackTrace();
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
