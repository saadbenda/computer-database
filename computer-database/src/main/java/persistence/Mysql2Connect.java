package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class Mysql2Connect{
	
	private Connection conn;
	
	//private String url = "jdbc:mysql://localhost:3306/computer-database-db";
	//String driver = "com.mysql.jdbc.Driver";
	String driver = "com.mysql.cj.jdbc.Driver";
	String userName = "root";
    String password = "hello";
    String debug="?useUnicode=true&serverTimezone=UTC";
	
	String url= "jdbc:mysql://localhost:3306/";
    String dbName = "computer-database-db";
	

	public Connection getConnection() {
		
		try {
			Class.forName(driver).newInstance();
			this.conn = DriverManager.getConnection(url+dbName+debug,userName,password);
			System.out.println("connection created");
			return this.conn;
			
		}
		catch(Exception sqle){
			System.out.println("cannot create");
			sqle.printStackTrace();
		}
		
		return this.conn;
		
	}

	public void close() throws SQLException {
		if(conn != null) {
			conn.close();
			conn = null;
		}
		
	}
	
	
}