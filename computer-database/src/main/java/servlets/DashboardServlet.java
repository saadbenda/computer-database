package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ComputerDto;
import mapper.MapperDto;
import model.Company;
import model.Computer;
import persistence.ComputerDao;
import service.Service;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 503410316613640940L;
	public static final String DASHBOARD = "WEB-INF/views/dashboard.jsp";

	// private Map<String, String> errors = new HashMap<String, String>();
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Computer> computers = null;
		//int display = Integer.parseInt(request.getParameter("display"));	
		//LinkedList pages= new LinkedList<Page>();
		ComputerDao computerDao = new ComputerDao();
		long limit;
		long count=0L;
		long offset=0L;
		int divisor=2;
		
		Service service = new Service();
		MapperDto mapperDto = new MapperDto();
		try {
			count = service.countComputers();
			System.out.println(count + " computers in the database");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		limit=count/divisor;
		//offset=limit;
		//limit*=divisor;
		
		try {
			computers=service.getComputers();
			System.out.println(computers.size() + " computers fetched");
		} catch (Exception e) {
			System.out.println("computers not fetched");
			e.printStackTrace();
			
		}
		
		
		
		/*try {
			computers = service.getComputers(limit);
			computersFounded = computers.size();
			limit = computersFounded / 2;
			System.out.println(computersFounded + " computers fetched");
			request.setAttribute("computersFounded", computersFounded);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("computers not fetched");
		}*/
		/*
		 * ArrayList<ComputerDto> computersDto=new ArrayList<ComputerDto>();
		 * computers.stream().forEach(computer->computersDto.add(mapperDto.
		 * fromComputerToComputerDto(computer)));
		 */
		request.setAttribute("count", count);
		request.setAttribute("computers", computers);
		request.getRequestDispatcher(DASHBOARD).forward(request, response);

}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selection = request.getParameter("selection");
		System.out.println("selection "+selection);
	
	}


}
