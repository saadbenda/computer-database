package persistence;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class MysqlConnect {
    private static Connection conn;
    //private Statement statement;
    //public static MysqlConnect db;
    private MysqlConnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "computer-database-db";
        String driver = "com.mysql.jdbc.Driver";
        //String debug="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "hello";
        try {
            this.conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("connexion crée");
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
        
    }
    
    public static synchronized Connection getInstance() throws ClassNotFoundException {
    	Connection conn = null;
    	Class.forName("com.mysql.jdbc.Driver"); 
    	String url= "jdbc:mysql://localhost:3306/";
        String dbName = "computer-database-db";
        String driver = "com.mysql.jdbc.Driver";
        //String debug="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "hello";
    	try {
			if (conn == null || conn.isClosed())
				conn = DriverManager.getConnection(url+dbName,userName,password);
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.out.println("error connection !");
		}
		return conn;
    }
    
    /*public static synchronized boolean close() {
		try {
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
    	
    	
    	
    	
    }*/
    
    
    /*public static synchronized MysqlConnect getDbCon() {
        if ( db == null ) {
            db = new MysqlConnect();
            System.out.println("nouvelle instance de connexion crée.");
        }
        return db;
 
    }
    public Connection getConnection() {
        return this.conn;
    }*/
}

