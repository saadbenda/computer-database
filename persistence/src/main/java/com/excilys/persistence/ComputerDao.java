package persistence;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import exceptions.CompaniesNotFoundException;
import exceptions.UpdateException;
import model.Computer;

@Repository
//@Scope("prototype")
public class ComputerDao {
	private static final String COUNT = "SELECT COUNT(*) AS count FROM computer";

	private static final String ADDCOMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name, :introduced, :discontinued, :companyId)";
	private static final String UPDATECOMPUTER = "UPDATE computer SET name=:name, introduced=:introduced, discontinued=:disontinued, company_id=:companyId WHERE id=:id";
	private static final String DELETECOMPUTER = "DELETE FROM computer WHERE id=?";
	private static final String LISTCOMPUTERS = "SELECT computer.id, computer.name, introduced, discontinued, company.id, company.name FROM computer LEFT JOIN company on company.id=company_id ORDER BY computer.id LIMIT ? OFFSET ?";

	private static final String GETCOMPUTER = "SELECT computer.id, computer.name, introduced, discontinued, company.id, company.name FROM computer LEFT JOIN company on company.id=company_id WHERE computer.id=?";
	private static final String SEARCH = "SELECT computer.id, computer.name, introduced, discontinued, company.id, company.name FROM computer LEFT JOIN company on company.id=company_id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ?";

	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public int addComputer(Computer cp)  throws CompaniesNotFoundException {
		int rsI;
		Long companyId = null;
		MapSqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("name", cp.getName(), Types.VARCHAR).addValue("introduced", cp.getIntroduced(), Types.DATE)
				.addValue("discontinued", cp.getDiscontinued(), Types.DATE);
		if (cp.getCompany() != null) {
			companyId = cp.getCompany().getId();
		}
		namedParameters.addValue("companyId", companyId);
		// rsI = namedParameterJdbcTemplate.queryForObject(ADDCOMPUTER, namedParameters,
		// Integer.class);
		try {
		rsI = namedParameterJdbcTemplate.update(ADDCOMPUTER, namedParameters);
		} catch(DataAccessException e) {
			throw new CompaniesNotFoundException(e);
		}
		return rsI;

	}

//	public int updateComputer(String name, LocalDate introduced, LocalDate discontinued, long companyId,
//			long computerId) throws SQLException {
//		conn = new Mysql2Connect().getConnection();
//		statement = conn.prepareStatement(UPDATECOMPUTER);
//		statement.setObject(1, name, Types.VARCHAR);
//		// Timestamp timestamp = Timestamp.valueOf(introduced.atStartOfDay());
//		// System.out.println("timestamp "+timestamp);
//		statement.setObject(2, introduced, Types.DATE);
//		statement.setObject(3, discontinued, Types.DATE);
//		statement.setObject(4, companyId, java.sql.Types.INTEGER);// can be null
//		statement.setObject(5, computerId, Types.BIGINT);
//		rsI = statement.executeUpdate();
//		this.closeConnection(conn);
//		this.closePreparedStatement(statement);
//		return rsI;
//	}

	public int deleteComputer(long id) throws SQLException {
		/*
		 * conn = new Mysql2Connect().getConnection(); statement =
		 * conn.prepareStatement(DELETECOMPUTER); statement.setObject(1, id,
		 * Types.BIGINT); rsI = statement.executeUpdate(); this.closeConnection(conn);
		 * this.closePreparedStatement(statement); return rsI;
		 */
		return 0;
	}

	public ArrayList<Computer> getAllComputers() throws Exception {
		/*
		 * ArrayList<Computer> computers = new ArrayList<Computer>(); conn = new
		 * Mysql2Connect().getConnection(); statement =
		 * conn.prepareStatement(LISTCOMPUTERS); statement.setObject(1, limit,
		 * Types.BIGINT); statement.setObject(2, offset, Types.BIGINT); rs =
		 * statement.executeQuery(); while (rs.next()) { Computer computer =
		 * mapper.fromResultSetToComputer(rs); computers.add(computer); }
		 * this.closeConnection(conn); this.closePreparedStatement(statement);
		 * this.closeResultSet(rs); return computers;
		 */
		return null;
	}

	public ArrayList<Computer> getAllComputers(long limit, long offset) throws Exception {
		/*
		 * ArrayList<Computer> computers = new ArrayList<Computer>(); conn = new
		 * Mysql2Connect().getConnection(); statement =
		 * conn.prepareStatement(LISTCOMPUTERS); statement.setObject(1, limit,
		 * Types.BIGINT); statement.setObject(2, offset, Types.BIGINT); rs =
		 * statement.executeQuery(); while (rs.next()) { Computer computer =
		 * mapper.fromResultSetToComputer(rs); computers.add(computer); }
		 * this.closeConnection(conn); this.closePreparedStatement(statement);
		 * this.closeResultSet(rs); return computers;
		 */
		return null;
	}
//
//	public long countComputers() throws SQLException {
//
//	}
//
//	public Computer getComputer(long id) throws Exception {
//
//		Computer computer = null;
//		conn = new Mysql2Connect().getConnection();
//		statement = conn.prepareStatement(GETCOMPUTER);
//		statement.setObject(1, id, Types.BIGINT);
//		rs = statement.executeQuery();
//		// System.out.println("rs "+rs.getString("computer.name"));
//		if (rs.next()) {
//			computer = mapper.fromResultSetToComputer(rs);
//		}
//		this.closeConnection(conn);
//		this.closePreparedStatement(statement);
//		this.closeResultSet(rs);
//		return computer;
//
//	}

	public ArrayList<Computer> searchComputer(String search) throws Exception {
		/*
		 * ArrayList<Computer> computers = new ArrayList<Computer>(); conn = new
		 * Mysql2Connect().getConnection(); statement = conn.prepareStatement(SEARCH);
		 * search.replace("%", ""); statement.setString(1, search);
		 * 
		 * // statement.setObject(2, "", Types.DATE); // statement.setObject(3, "",
		 * Types.DATE); statement.setString(2, search); rs = statement.executeQuery();
		 * while (rs.next()) { Computer computer = mapper.fromResultSetToComputer(rs);
		 * computers.add(computer); } return computers;
		 */
		return null;

	}

	public ArrayList<Computer> orderBy(String orderBy, String limit, String offset) throws Exception {
		/*
		 * ArrayList<Computer> computers = new ArrayList<Computer>(); conn = new
		 * Mysql2Connect().getConnection(); String sql;
		 * System.out.println("ordeer by ----- "+orderBy); if (orderBy.equals(""))
		 * {sql=LISTCOMPUTERS;} else {sql=LISTCOMPUTERS+" ORDER BY "+orderBy;}
		 * 
		 * 
		 * System.out.println("sql --- "+sql); statement = conn.prepareStatement(sql);
		 * //statement.setString(1, option); rs = statement.executeQuery(); while
		 * (rs.next()) { Computer computer = mapper.fromResultSetToComputer(rs);
		 * computers.add(computer); } return computers;
		 */
		return null;
	}

}
