package sendingnote.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sendingnote.common.MyConstants;

public class MyConnection {
	
	private static Connection conn = null;

	private MyConnection() {
	}

	public static Connection getConnection() {
		try {
			if (conn == null) {

				Class.forName("oracle.jdbc.driver.OracleDriver");

				conn = DriverManager.getConnection(MyConstants.DB_URL, MyConstants.DB_USER, MyConstants.DB_PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
