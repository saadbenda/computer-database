package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import spring.SpringConfiguration;

import com.zaxxer.hikari.HikariDataSource;

public class HikariConnect {
	
	private static HikariConnect hikariConnect;
	
	private HikariDataSource ds;
	
	private Connection connect;
	
	public HikariConnect() {
		try {
			ds = SpringConfiguration.getContext().getBean(HikariDataSource.class);
			connect = ds.getConnection();
		}
		catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}
	
	public static synchronized HikariConnect getConnection() {
		if (hikariConnect == null) {
			hikariConnect = new HikariConnect();
		}
		return hikariConnect;
	}

	
	
	
	public Connection getConnect() throws SQLException {
		return ds.getConnection();
	}
	
	
	
	/*protected void finalize() throws SQLException {
		this.close();
	}*/
}