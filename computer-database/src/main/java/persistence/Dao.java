package persistence;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import java.sql.Connection;

import mapper.Mapper;

@Repository
public class Dao {
	protected PreparedStatement statement;
	protected ResultSet rs;
	protected int rsI;
	protected Connection conn;
	public boolean closeEverything() {
		return false;

			/*//statement.close();
			//MysqlConnect.close();
			try{
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return true;
		*/
		
	}
	
	protected void closeConnection(Connection conn){
		try {
			conn.close();
			System.out.println("connection closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void closePreparedStatement(PreparedStatement statement){
		try {
			statement.close();
			System.out.println("preparedStatement closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void closeResultSet(ResultSet rs){
		try {
			rs.close();
			System.out.println("resultSet closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}

