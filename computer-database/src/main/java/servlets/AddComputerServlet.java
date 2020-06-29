package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import dto.CompanyDto;
import dto.CompanyDto.CompanyDtoBuilder;
import dto.ComputerDto;
import dto.ComputerDto.ComputerDtoBuilder;
import mapper.MapperDto;
import model.Company;
import model.Computer;
import service.Service;
import validation.Validation;

//@WebServlet("/addComputer")
public class AddComputerServlet extends HttpServlet {
	@Autowired
	MapperDto mapperDto;

	@Autowired
	Validation validation;

	@Autowired
	Service service;

	private static final long serialVersionUID = -6584495854846266599L;
	public static final String ADDCOMPUTER = "WEB-INF/views/addComputer.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		try {
			ArrayList<Company> companies = service.getCompanies();
			request.setAttribute("companies", companies);
			request.getRequestDispatcher(ADDCOMPUTER).forward(request, response);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get Param
		String computerName = request.getParameter("computerName");
		String intro = request.getParameter("introduced");
		String disco = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		// DTO
		CompanyDto companyDto = new CompanyDtoBuilder().withId(companyId).build();
		ComputerDto computerDto = new ComputerDtoBuilder().withName(computerName).IntroducedIn(intro)
				.DiscontinuedIn(disco).withCompanyDto(companyDto).build();
		try {
			// Mapping
			Computer computer = mapperDto.fromComputerDtoToComputer(computerDto);
			// Validation
			validation.createComputer(computer);
			// Service
			service.addComputer(computer);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
}