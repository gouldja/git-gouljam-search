package com.gouldja.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DatabaseTest {
	
	private static final Logger logger = LogManager.getLogger(DatabaseTest.class);

	public static void main(String[] args) {

	
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		
			Connection con = DriverManager.getConnection("jdbc:derby:target/database;create=true","APP",null);
			Statement s = con.createStatement();
			s.execute("CREATE TABLE TEST (TEST_ID INT NOT NULL)");
			ResultSet rs = s.executeQuery("Select * from sys.systables");
			
			while (rs.next()) {
				logger.debug(rs.getString("TABLENAME"));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
