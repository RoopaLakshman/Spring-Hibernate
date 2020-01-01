package com.hibernate.database.test.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&serverTimezone=UTC";
		String user = "hbstudent";
		String password = "hbstudent";
		Connection c = getConnection(jdbcUrl, user, password);
	}

	private static Connection getConnection(String jdbcUrl, String user, String password) {
		Connection mycon = null;
		try {
			System.out.println("In try block" + jdbcUrl);
			 mycon = DriverManager.getConnection(jdbcUrl, user,
					password);
			 
			System.out.println("Connection received - " + mycon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mycon;
	}
}
