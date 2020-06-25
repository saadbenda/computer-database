package persistence;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import mapper.Mapper;

import model.Computer;

@Repository
//@Scope("prototype")
public class ComputerDao extends Dao {
	private static final String ADDCOMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (:name, :introduced, :discontinued, :companyId);";
	private static final String UPDATECOMPUTER = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
	private static final String DELETECOMPUTER = "DELETE FROM computer WHERE id=?";
	// private static final String LISTCOMPUTERS = "SELECT (computer.id,
	// computer.name, introduced, discontinued, company.id, company.name) FROM
	// computer JOIN company ON company_id=company.id LIMIT 10";
	private static final String LISTCOMPUTERS = "SELECT computer.id, computer.name, introduced, discontinued, company.id, company.name FROM computer LEFT JOIN company on company.id=company_id ORDER BY computer.id LIMIT ? OFFSET ?";
	private static final String COUNT = "SELECT COUNT(*) AS count FROM computer";
	private static final String GETCOMPUTER = "SELECT computer.id, computer.name, introduced, discontinued, company.id, company.name FROM computer LEFT JOIN company on company.id=company_id WHERE computer.id=?";
	private static final String SEARCH = "SELECT computer.id, computer.name, introduced, discontinued, company.id, company.name FROM computer LEFT JOIN company on company.id=company_id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ?";
	

	/*
	 * private static final String
	 * FINDCOMPUTERBYID="SELECT id,name, introduced,discontinued,company_id FROM computer where id=?"
	 * ; private static final String
	 * FINDCOMPUTERBYNAME="SELECT id,name, introduced,discontinued,company_id FROM computer where name=?"
	 * ; private static final String
	 * FINDCOMPUTERIDBYNAME="SELECT id FROM computer where name=?"; private static
	 * final String UPDATECOMPUTERNAME="UPDATE computer SET name=? WHERE id=?";
	 * 
	 * private static final String
	 * UPDATECOMPUTERINTRODUCED="UPDATE computer SET introduced=? WHERE id=?";
	 * private static final String
	 * UPDATECOMPUTERDISCONTINUED="UPDATE computer SET discontinued=? WHERE id=?";
	 * private static final String
	 * UPDATECOMPUTERCOMPANY="UPDATE computer SET company_id=? WHERE id=?";
	 * 
	 * 
	 * private static final String LASTIDCOMPUTER =
	 * "SELECT MAX(ID) AS LastId FROM computer;";
	 */

	// Service service = new Service();
	// Mapper mapper = new Mapper();

	/***
	 * @return A positive number, if a positive number of rows are affected by the
	 *         operation, and the operation is not a mass delete on a segmented
	 *         table space. 0, if no rows are affected by the operation. -1, if the
	 *         operation is a mass delete on a segmented table space.
	 * @throws SQLException
	 */

	
	
	@Autowired
	JdbcTemplate jdbcT;
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int addComputer(Computer cp) throws SQLException {
		
		/*
		 * Parameters:
		 * sql - the SQL query to execute
			requiredType - the type that the result object is expected to match
			Returns:
			the result object of the required type, or null in case of SQL NULL
		 */
			
		
		
		// int valueType = Types.NULL;
		/*
		conn = HikariConnect.getConnection().getConnect();
		statement = conn.prepareStatement(ADDCOMPUTER);
		statement.setObject(1, cp.getName(), Types.VARCHAR);
		statement.setObject(2, cp.getIntroduced(), Types.DATE);
		statement.setObject(3, cp.getDiscontinued(), Types.DATE);
		*/
		
		//jdbcT.update(ADDCOMPUTER,cp.getName());//dangereux
		
		SqlParameterSource namedParameters = new MapSqlParameterSource()
				.addValue("id", cp.getName(),Types.BIGINT)
				.addValue(paramName, value)
				.addValue();
		
		if (cp.getCompany() != null) {
			namedParameters.addValue("companyId", cp.getCompany().getId(), Types.BIGINT);
		} else {
			statement.setNull("companyId", java.sql.Types.NULL);
		}
		rsI = namedParameterJdbcTemplate.queryForObject(ADDCOMPUTER, namedParameters, Integer.class);
		return rsI;
		
		/*Computer computre = new Computer();
		computer.setFirstName("James");
		SqlParameterSource namedParameters2 = new BeanPropertySqlParameterSource(cp);
		return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, namedParameters2, Integer.class);*/
		
		/*
		 * if (!cp.getCompany().equals(null)) {
		 * System.out.println("company is not null in computerDao");
		 * valueType=Types.BIGINT; } statement.setObject(4, cp.getCompany().getId(),
		 * valueType);
		 */

		/*
		if (cp.getCompany() != null) {
			statement.setObject(4, cp.getCompany().getId(), Types.BIGINT);
		} else {
			statement.setNull(4, java.sql.Types.NULL);
		}
		rsI = statement.executeUpdate();
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		
		*/
		
		
		
	}

	public int updateComputer(String name, LocalDate introduced, LocalDate discontinued, long companyId, long computerId) throws SQLException {
		conn = new Mysql2Connect().getConnection();
		statement = conn.prepareStatement(UPDATECOMPUTER);
		statement.setObject(1, name, Types.VARCHAR);
		// Timestamp timestamp = Timestamp.valueOf(introduced.atStartOfDay());
		// System.out.println("timestamp "+timestamp);
		statement.setObject(2, introduced, Types.DATE);
		statement.setObject(3, discontinued, Types.DATE);
		statement.setObject(4, companyId, java.sql.Types.INTEGER);// can be null
		statement.setObject(5, computerId, Types.BIGINT);
		rsI = statement.executeUpdate();
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		return rsI;
	}

	public int deleteComputer(long id) throws SQLException {
		conn = new Mysql2Connect().getConnection();
		statement = conn.prepareStatement(DELETECOMPUTER);
		statement.setObject(1, id, Types.BIGINT);
		rsI = statement.executeUpdate();
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		return rsI;
	}

	public ArrayList<Computer> getAllComputers() throws Exception {
		int offset = 0;
		long limit = 99999999999999L;
		
		ArrayList<Computer> computers = new ArrayList<Computer>();
		conn = new Mysql2Connect().getConnection();
		statement = conn.prepareStatement(LISTCOMPUTERS);
		statement.setObject(1, limit, Types.BIGINT);
		statement.setObject(2, offset, Types.BIGINT);
		rs = statement.executeQuery();
		while (rs.next()) {
			Computer computer = mapper.fromResultSetToComputer(rs);
			computers.add(computer);
		}
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		this.closeResultSet(rs);
		return computers;
	}

	public ArrayList<Computer> getAllComputers(long limit, long offset) throws Exception {
		
		ArrayList<Computer> computers = new ArrayList<Computer>();
		conn = new Mysql2Connect().getConnection();
		statement = conn.prepareStatement(LISTCOMPUTERS);
		statement.setObject(1, limit, Types.BIGINT);
		statement.setObject(2, offset, Types.BIGINT);
		rs = statement.executeQuery();
		while (rs.next()) {
			Computer computer = mapper.fromResultSetToComputer(rs);
			computers.add(computer);
		}
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		this.closeResultSet(rs);
		return computers;
	}

	public long countComputers() throws SQLException {
		conn = new Mysql2Connect().getConnection();
		long count = 0;
		statement = conn.prepareStatement(COUNT);
		rs = statement.executeQuery();
		if (rs.next()) {
			count = rs.getLong("count");
		}
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		this.closeResultSet(rs);
		return count;
	}

	public Computer getComputer(long id) throws Exception {
		
		Computer computer = null;
		conn = new Mysql2Connect().getConnection();
		statement = conn.prepareStatement(GETCOMPUTER);
		statement.setObject(1, id, Types.BIGINT);
		rs = statement.executeQuery();
		// System.out.println("rs "+rs.getString("computer.name"));
		if (rs.next()) {
			computer = mapper.fromResultSetToComputer(rs);
		}
		this.closeConnection(conn);
		this.closePreparedStatement(statement);
		this.closeResultSet(rs);
		return computer;

	}

	public ArrayList<Computer> searchComputer(String search) throws Exception {
		
		ArrayList<Computer> computers = new ArrayList<Computer>();
		conn = new Mysql2Connect().getConnection();
		statement = conn.prepareStatement(SEARCH);
		search.replace("%", "");
		statement.setString(1, search);

		// statement.setObject(2, "", Types.DATE);
		// statement.setObject(3, "", Types.DATE);
		statement.setString(2, search);
		rs = statement.executeQuery();
		while (rs.next()) {
			Computer computer = mapper.fromResultSetToComputer(rs);
			computers.add(computer);
		}
		return computers;

	}

	public ArrayList<Computer> orderBy(String orderBy, String limit, String offset) throws Exception {
		
		ArrayList<Computer> computers = new ArrayList<Computer>();
		conn = new Mysql2Connect().getConnection();
		String sql;
		System.out.println("ordeer by ----- "+orderBy);
		if (orderBy.equals("")) {sql=LISTCOMPUTERS;}
		else {sql=LISTCOMPUTERS+" ORDER BY "+orderBy;}
		
		
		System.out.println("sql --- "+sql);
		statement = conn.prepareStatement(sql);
		//statement.setString(1, option);
		rs = statement.executeQuery();
		while (rs.next()) {
			Computer computer = mapper.fromResultSetToComputer(rs);
			computers.add(computer);
		}
		return computers;
	}
	
	
	
	
	

	/******************************************************/

	/*
	 * public ArrayList<Computer> findComputersByName(String computer_name) {
	 * System.out.println("computer dao"+computer_name); ComputerBuilder cb = new
	 * ComputerBuilder(); ArrayList<Computer> computersList=new
	 * ArrayList<Computer>();
	 * 
	 * try { statement =
	 * MysqlConnect.getInstance().prepareStatement(FINDCOMPUTERBYNAME);
	 * statement.setObject(1,computer_name, Types.VARCHAR);
	 * rs=statement.executeQuery(); if(rs.next()) {
	 * System.out.println("computer dao"+computer_name); do { Computer computer =
	 * mapper.mapToComputer(rs); computersList.add(computer); }while(rs.next()); }
	 * }catch (SQLException e) { e.printStackTrace(); } finally { closeEverything();
	 * }
	 * 
	 * return computersList; }
	 */

	/*
	 * public Optional<Long> findComputerIdByName(String computer_name) {
	 * Optional<Long> computer_id = Optional.empty(); try { statement =
	 * MysqlConnect.getInstance().prepareStatement(FINDCOMPUTERIDBYNAME);
	 * statement.setObject(1, computer_name, Types.VARCHAR); rs =
	 * statement.executeQuery(); if (rs.next()) do { computer_id =
	 * Optional.of(rs.getLong("id")); } while (rs.next());
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } return computer_id; }
	 */

	/*
	 * public Optional<Computer> findComputerById(String computer_id) {
	 * ComputerBuilder cb = new ComputerBuilder(); Computer computer = null; try {
	 * statement = MysqlConnect.getInstance().prepareStatement(FINDCOMPUTERBYID);
	 * statement.setObject(1,computer_id, Types.BIGINT);
	 * rs=statement.executeQuery(); while(rs.next()) { computer =
	 * mapper.mapToComputer(rs); } }catch (SQLException e) { e.printStackTrace(); }
	 * finally { closeEverything(); }
	 * 
	 * return Optional.ofNullable(computer); }
	 */
	/*
	 * public int updateComputerName(String name, long computer_id) { try {
	 * statement = MysqlConnect.getInstance().prepareStatement(UPDATECOMPUTERNAME);
	 * statement.setObject(1, name, Types.VARCHAR); statement.setObject(2,
	 * computer_id, Types.BIGINT); rsI = statement.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return rsI; }
	 */

	/*
	 * public int updateComputerIntroduced(LocalDate introduced, long computer_id) {
	 * try { statement =
	 * MysqlConnect.getInstance().prepareStatement(UPDATECOMPUTERINTRODUCED);
	 * statement.setObject(1, introduced); // statement.setObject(1,introduced,
	 * Types.TIMESTAMP); statement.setObject(2, computer_id, Types.BIGINT); rsI =
	 * statement.executeUpdate(); System.out.println("computer DAO " + introduced);
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return rsI; }
	 */

	/*
	 * public int updateComputerDiscontinued(LocalDate discontinued, long
	 * computer_id) { try { statement =
	 * MysqlConnect.getInstance().prepareStatement(UPDATECOMPUTERDISCONTINUED);
	 * statement.setObject(1, discontinued, Types.TIMESTAMP); statement.setObject(2,
	 * computer_id, Types.BIGINT); rsI = statement.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return rsI; }
	 */

	/*
	 * public int updateComputerCompany(long company_id, long computer_id) { try {
	 * statement =
	 * MysqlConnect.getInstance().prepareStatement(UPDATECOMPUTERCOMPANY);
	 * statement.setObject(1, company_id, Types.BIGINT); statement.setObject(2,
	 * computer_id, Types.BIGINT); rsI = statement.executeUpdate();
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return rsI; }
	 */

	/*
	 * public long lastIdComputer() { long id = 0L; try { statement =
	 * MysqlConnect.getInstance().prepareStatement(LASTIDCOMPUTER); rs =
	 * statement.executeQuery(); do { id = rs.getLong("LastId"); } while
	 * (rs.next());
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); }
	 * 
	 * return id; }
	 */

}
