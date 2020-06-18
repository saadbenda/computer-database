package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.CompanyDto;
import dto.CompanyDto.CompanyDtoBuilder;
import dto.ComputerDto.ComputerDtoBuilder;
import dto.ComputerDto;
import model.Company;
import model.Computer;
import service.Service;

@WebServlet("/EditComputerServlet")
public class EditComputerServlet extends HttpServlet {
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = -3680162934694826608L;
	public static final String EDITCOMPUTER = "WEB-INF/views/editComputer.jsp";

	// private Map<String, String> errors = new HashMap<String, String>();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Computer computer = null;
		Service service = new Service();
		ArrayList<Company> companies = null;
		long id = Long.parseLong(request.getParameter("id"));
		System.out.println("id selected : "+id);
		try {
			computer = service.getComputer(id);
			System.out.println("computer fetched");
		} catch (Exception e) {
			System.out.println("computer not fetched");
			e.printStackTrace();
		}
		request.setAttribute("computer", computer);
		try {
			companies = service.getCompanies();
			System.out.println("companies fetched");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("companies not fetched");
		}
		request.setAttribute("companies", companies);
		request.getRequestDispatcher(EDITCOMPUTER).forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Service service = new Service();
		String computerId = request.getParameter("computerId");
		String computerName = request.getParameter("computerName");
		int erreur;
		//String[] company = request.getParameterValues("companyId");
		String companyId = request.getParameter("companyId");
		String introduced = request.getParameter("introduced");
		System.out.println("intro "+introduced);
		String discontinued = request.getParameter("discontinued");
		
		//CompanyDto companyDto = new CompanyDtoBuilder().withId(companyId).build();
		
		//ComputerDto computerDto = new ComputerDtoBuilder().withId(id).withbuild();
		
		try {
			service.updateComputer(computerName, introduced, discontinued, companyId, computerId);
			System.out.println("computer updated");
		} catch (SQLException e) {
			System.out.println("computer not updated");
			e.printStackTrace();
		}
		
		
	}
	
	
}
