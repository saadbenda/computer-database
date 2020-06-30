package persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import model.Company;
import mapper.CompanyMapper;


@Repository
//@Scope("prototype")
public class CompanyDao {
	private static final String FINDCOMPANY = "SELECT name FROM company WHERE id=:id";
	private static final String LISTCOMPANIES = "SELECT id,name FROM company";
	private static final String ADDCOMPANY = "INSERT INTO company (name) VALUES(?)";
	private static final String DELETECOMPANY = "INSERT INTO company (name) VALUES(?)";

	
	@Autowired
	NamedParameterJdbcTemplate name;

	@Autowired
	CompanyMapper companyMapper;

	
	
	public ArrayList<Company> getAllCompanies() throws SQLException {
		ArrayList<Company> companyList = new ArrayList<Company>();
		companyList = (ArrayList<Company>) name.query(LISTCOMPANIES, companyMapper);
		return companyList;
	}

	/************************************************************************************************/

	/*
	 * public int addCompany(Company company) throws SQLException { conn = new
	 * Mysql2Connect().getConnection(); statement =
	 * conn.prepareStatement(ADDCOMPANY); statement.setObject(1, company.getName(),
	 * Types.VARCHAR); rsI = statement.executeUpdate(); this.closeConnection(conn);
	 * this.closePreparedStatement(statement); return rsI;
	 * 
	 * }
	 */

	/*
	 * public Optional<Long> findCompanyIdByName(String company) { long result = 0;
	 * try { statement =
	 * MysqlConnect.getInstance().prepareStatement(FINDCOMPANYIDBYNAME);
	 * statement.setObject(1, company, Types.VARCHAR); rs =
	 * statement.executeQuery(); // Iterator<ResultSet> if (rs.next() == false) {
	 * System.out.println("false rs "); return Optional.empty(); } else {
	 * System.out.println("else rs "); do { result = rs.getLong("id");
	 * System.out.println("result " + result); } while (rs.next()); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return Optional.ofNullable(result); }
	 */

	/*
	 * public Company findCompanyNameById(long company_id) { String result = null;
	 * try { statement =
	 * MysqlConnect.getInstance().prepareStatement(FINDCOMPANYNAMEBYID);
	 * statement.setObject(1, company_id, Types.BIGINT); rs =
	 * statement.executeQuery(); while (rs.next()) { result = rs.getString("name");
	 * } } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return null; // return Optional.ofNullable(result); }
	 */

	/*
	 * public int updateCompanyId(long company_id, String companyName) { try {
	 * statement = MysqlConnect.getInstance().prepareStatement(UPDATECOMPANYID);
	 * statement.setObject(1, company_id, Types.BIGINT); statement.setObject(2,
	 * companyName, Types.VARCHAR); rsI = statement.executeUpdate();// return number
	 * of lines updated or 0 if nothing
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return rsI; }
	 */

	/*
	 * public int updateCompanyName(long company_id, String companyName) { try {
	 * statement = MysqlConnect.getInstance().prepareStatement(UPDATECOMPANYNAME);
	 * statement.setObject(2, company_id, Types.BIGINT); statement.setObject(1,
	 * companyName, Types.VARCHAR); rsI = statement.executeUpdate();// return number
	 * of lines updated or 0 if nothing
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); } finally {
	 * closeEverything(); } return rsI; }
	 */

	/*
	 * public int deleteCompany(long company_id) { try { statement =
	 * MysqlConnect.getInstance().prepareStatement(DELETECOMPANY);
	 * statement.setObject(1, company_id, Types.BIGINT); rsI =
	 * statement.executeUpdate(); } catch (SQLException e) { e.printStackTrace(); }
	 * finally { closeEverything(); } return rsI; }
	 */

}
