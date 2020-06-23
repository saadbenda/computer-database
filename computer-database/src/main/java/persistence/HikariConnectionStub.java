package persistence;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionStub {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	private static Connection connection=null;
	private static HikariConnectionStub instance;

	static {
		/* config = new HikariConfig("datasource.properties");

	        Properties props = new Properties();
	        props.setProperty("dataSourceClassName", "com.mysql.cj.jdbc.Driver");
	        props.setProperty("dataSource.user", "root");
	        props.setProperty("dataSource.password", "hello");
	        props.put("dataSource.logWriter", new PrintWriter(System.out));
	        config = new HikariConfig(props);*/

		config = new HikariConfig("datasource.properties");
		HikariDataSource ds = new HikariDataSource(config);

		/*Properties props = new Properties();
		props.setProperty("dataSourceClassName", "com.mysql.cj.jdbc.Driver");
		props.setProperty("dataSource.user", "");
		props.setProperty("dataSource.password", "");
		props.put("dataSource.logWriter", new PrintWriter(System.out));
		config = new HikariConfig(props);*/


	}
	 private HikariConnectionStub() {}

	    public static Connection getConnection() {     
			try {
				connection = ds.getConnection();
				return connection;
			} catch (SQLException e) {
				System.out.println("cannot connect");
				e.printStackTrace();
			}
	        return connection;
	    }
	    
	    /*public static HikariConnection getInstance() {
	    	if (connection==null) {
	    		HikariConnection.getConnection();
	    	}else {
	    		return this;
	    	}
	    }*/
	    
	    
	    
	    

}
