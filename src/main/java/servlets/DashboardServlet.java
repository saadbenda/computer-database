package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		ArrayList<Computer> computers = new ArrayList<>();
		// Map<String,String> options = new Map<String,String>();
		ArrayList<String> options = new ArrayList<String>();
		Service service = new Service();
		String option = "asc";

		HttpSession session = request.getSession();
		// session.invalidate();
		
		session.setAttribute("computerAZ", true);

		ComputerDao computerDao = new ComputerDao();
		long limit;
		long count = 0L;
		long offset = 0L;
		int divisor = 2;

		
		try {
			count = service.countComputers();
			System.out.println(count + " computers in the database");
			computers=service.getComputers();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		//limit = count / divisor;
		// offset=limit;
		// limit*=divisor;

		/*
		 * try { computers = service.getComputers(); System.out.println(computers.size()
		 * + " computers fetched"); } catch (Exception e) {
		 * System.out.println("computers not fetched"); e.printStackTrace();
		 * 
		 * }
		 */

		

		/*
		 * try { computers = service.searchComputer(search);
		 * System.out.println("search ok !"); } catch (Exception e) {
		 * System.out.println("search not ok !"); e.printStackTrace(); }
		 */

		/*
		 * try { computers = service.getComputers(limit); computersFounded =
		 * computers.size(); limit = computersFounded / 2;
		 * System.out.println(computersFounded + " computers fetched");
		 * request.setAttribute("computersFounded", computersFounded); } catch
		 * (Exception e) { e.printStackTrace();
		 * System.out.println("computers not fetched"); }
		 */
		/*
		 * ArrayList<ComputerDto> computersDto=new ArrayList<ComputerDto>();
		 * computers.stream().forEach(computer->computersDto.add(mapperDto.
		 * fromComputerToComputerDto(computer)));
		 */
		request.setAttribute("count", count);
		request.setAttribute("computers", computers);
		// System.out.println("computers size ----- " + computers.size());
		request.getRequestDispatcher(DASHBOARD).forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Computer> computers = null;
		Service service = new Service();
		HttpSession session = request.getSession();
		ArrayList<String> options = new ArrayList<String>();
		String computer = request.getParameter("computer");
		String intro = request.getParameter("introduction");
		String disco = request.getParameter("discontinued");
		String company = request.getParameter("company");
		String limit = request.getParameter("limit");
		String page = request.getParameter("page");
		
		if (limit == null) {limit="10";}
		if (page == null) {page="1";}
		
		session.setAttribute("limit", limit);
		session.setAttribute("page", page);
		if (Integer.parseInt(page)>=2) {
			offset=
		}
		if (computer != null) {
			if (computer.equals("A-Z")) {

				session.setAttribute("computerAZ", true);
				session.setAttribute("computerZA", false);

				options.add("computer.name asc");
			}

			else if (computer.equals("Z-A")) {

				session.setAttribute("computerZA", true);
				session.setAttribute("computerAZ", false);

				options.add("computer.name desc");
			}

			else if (computer.equals("none")) {

				session.setAttribute("computerAZ", false);
				session.setAttribute("computerZA", false);

			}
		}
		if (intro != null) {
			if (intro.equals("latest")) {
				session.setAttribute("introLatest", true);
				session.setAttribute("introOldest", false);
				options.add("introduced asc");

			} else if (intro.equals("oldest")) {
				session.setAttribute("introLatest", true);
				session.setAttribute("introOldest", false);
				options.add("introduced desc");

			} else if (intro.equals("none")) {
				session.setAttribute("introLatest", false);
				session.setAttribute("introOldest", false);

			}
		}
		if (disco != null) {
			if (disco.equals("latest")) {
				session.setAttribute("discoLatest", true);
				session.setAttribute("discoOldest", false);
				options.add("discontinued desc");

			} else if (disco.equals("oldest")) {
				session.setAttribute("discoLatest", true);
				session.setAttribute("discoOldest", false);
				options.add("discontinued desc");

			} else if (disco.equals("none")) {
				session.setAttribute("discoLatest", false);
				session.setAttribute("discoOldest", false);

			}
		}
		if (company != null) {
			if (company.equals("A-Z")) {

				session.setAttribute("companyAZ", true);
				session.setAttribute("companyZA", false);

				options.add("company.name asc");
			}

			else if (company.equals("Z-A")) {

				session.setAttribute("companyZA", true);
				session.setAttribute("companyAZ", false);

				options.add("company.name desc");
			}

			else if (company.equals("none")) {

				session.setAttribute("companyAZ", false);
				session.setAttribute("companyZA", false);

			}
		}
		
			
			String sql = String.join(",", options);
		
			try {
				computers = service.orderBy(sql);
				count=computers.size();
				System.out.println(computers.size() + " computers fetched");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		
		
		request.setAttribute("count", count);
		request.setAttribute("computers", computers);
		request.getRequestDispatcher(DASHBOARD).forward(request, response);

	}

}
