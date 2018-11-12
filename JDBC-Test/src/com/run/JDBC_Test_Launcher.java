package com.run;

import java.sql.*;

public class JDBC_Test_Launcher {

	public static void main(String[] args) {
		System.out.println("Starting the JDBC test.\n");
		try {
			// 1. Get a connection
			Connection testCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/university-db","admin","admin");
			
			// 2. Create a statement
			Statement statement = testCon.createStatement();
			
			// 3. Execute SQL query
			ResultSet result = statement.executeQuery("select * from `db-users`");
			
			// 4. Process result set
			while (result.next())
				System.out.println(result.getString("username") + ", " + result.getString("nickname"));
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("\n\n--------------------------\nWhoops. Something happened...\n");
		}
	}

}
