package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.zaxxer.hikari.HikariDataSource;

import dto.CompanyDto;
import dto.CompanyDto.CompanyDtoBuilder;
import dto.ComputerDto;
import dto.ComputerDto.ComputerDtoBuilder;
import mapper.MapperDates;
import mapper.MapperDto;
import model.Company;
import model.Computer;
import persistence.CompanyDao;
import service.Service;
import spring.SpringConfiguration;

import validation.Validation;


//@Controller
//@RequestMapping("/AddComputerServlet")


@WebServlet("/AddComputerServlet")
@ComponentScan(basePackages = "com.excilys.cdb")

public class AddComputerServlet extends HttpServlet {
	/**
	 * generated serialVersionUID
	 */

	/*
	 * public void init(ServletConfig config) throws ServletException {
	 * super.init(config);
	 * SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this); }
	 */

	// private static AnnotationConfigApplicationContext appContext =
	// SpringConfiguration.getContext();

	// private Service service = appContext.getBean(Service.class);
	// private Service service =
	// SpringConfiguration.getContext().getBean(Service.class);

	private static final long serialVersionUID = -6584495854846266599L;
	public static final String ADDCOMPUTER = "WEB-INF/views/addComputer.jsp";
	// private Map<String, String> errors = new HashMap<String, String>();

	//ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	//Service service = appContext.getBean(Service.class);
	
	//ApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
	//Service service = appContext.getBean(Service.class);
	
	//Service service = SpringConfiguration.getContext().getBean(Service.class);
    //CompanyService companyService = CDBConfig.getContext().getBean(CompanyService.class);

	@Autowired
	Service service;
 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
    	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/* Affichage de la page d'inscription */
		/*
		 * String test = "1"; String test2 = "2"; request.setAttribute("computerName",
		 * test2); request.setAttribute("test", test);
		 */

		// List<Company> companies = service.getListCompanies();

		/*
		 * List<CompanyDto> companiesDto = new ArrayList(); companiesDto.add(new
		 * CompanyDtoBuilder().withId("0").withName("apple").build());
		 * 
		 * 
		 * request.setAttribute("companies", companiesDto);
		 */

		// this.getServletContext().getRequestDispatcher( VIEW ).forward( request,
		// response );

		// validation.addComputer();
		ArrayList<Company> companies = null;
		/*
		 * try { companies = service.getCompanies(); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		try {
			companies = service.getCompanies();
			System.out.println("companies fetched");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("companies not fetched");
		}
		request.setAttribute("companies", companies);
		request.getRequestDispatcher(ADDCOMPUTER).forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Validation validation = new Validation();
		MapperDto mapperDto = new MapperDto();
		MapperDates mapperDates = new MapperDates();
		CompanyDao companyDao = new CompanyDao();

		String computerName = request.getParameter("computerName");

		// String companyName = request.getParameter("companyName");
		String intro = request.getParameter("introduced");
		String disco = request.getParameter("discontinued");
		String companyId = request.getParameter("companyId");

		System.out.println("computer name : " + computerName);
		System.out.println("intro : " + intro);
		System.out.println("disco : " + disco);
		System.out.println("company id : " + companyId);

		/*
		 * try { validation.computerName(computerName); } catch (Exception e3) {
		 * e3.printStackTrace(); }
		 */

		/*
		 * try { validation.dateFormat(intro); validation.dateFormat(disco); } catch
		 * (ParseException e2) { e2.printStackTrace();
		 * System.out.println("invalid date format"); }
		 */
		String companyName = null;
		CompanyDto companyDto = null;
		// Optional<String> companyIdO = Optional.of(companyId);

		if (companyId.equals("null")) {
			System.out.println("companyId is null");
		} else {
			try {
				companyName = companyDao.findCompanyName(Long.parseLong(companyId));
				System.out.println("company created");
				System.out.println("Company name : " + companyName);
				companyDto = new CompanyDtoBuilder().withId(companyId).withName(companyName).build();
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		ComputerDto computerDto = new ComputerDtoBuilder().withName(computerName).IntroducedIn(intro)
				.DiscontinuedIn(disco).withCompanyDto(companyDto).build();
		Computer computer = mapperDto.fromComputerDtoToComputer(computerDto);
		try {
			validation.createComputer(computer);
			System.out.println("computer valid");
		} catch (Exception e) {
			String error = e.getMessage();
			// request.setAttribute("errors", error);
			System.out.println("invalid computer");
		}
		try {
			service.addComputer(computer);
			System.out.println("computer added");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("computer not added");
		}
		// this.getServletContext().getRequestDispatcher(ADDCOMPUTER).forward(request,response);

		// this.getServletContext().getRequestDispatcher( VIEW ).forward( request,
		// response );

		// service.addComputer(service.createComputerDto(computerName,companyId,intro,
		// disco));

		/*
		 * if (computerName=="null") { errors.put("computerName","wrong computer name");
		 * }
		 */
		// this.getServletContext().getRequestDispatcher( VIEW ).forward( request,
		// response );

	}
}