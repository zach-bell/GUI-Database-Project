package main.SQL;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorDB {
	
	public boolean connected = false;
	
	public String errorMsg = "No errors";
	
	private Connection con;
	
	private Statement statement;
	
	private ResultSet set;
	
	public ConnectorDB() {
		try {
			// Establishes a connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/university-db","admin","admin");
			
			// Checks the connection and will wait 10 seconds
			if (con.isValid(10)) {
				connected = true;
				System.out.println("-- Connected successfully --\n");
			} else {
				connected = false;
				System.out.println("-- CONNECTION FAILED WITHIN 10 SECONDS --\n");
			}
			// creates a statement object
			statement = con.createStatement();
			
		} catch(SQLException e) {
			connected = false;
			System.out.println("-- CONECTION FAILED --\n" + e.getMessage()+"\n");
			errorMsg = e.getSQLState();
		} catch(Exception e) {
			connected = false;
			System.out.println("-- CONECTION FAILED --\n" + e.getMessage()+"\n");
			errorMsg = e.getMessage();
		} // eof try catch() 
	} // eof connectorDB()
	
	public String selectTable(String table, String[] collums, String conditions) {
		String sender = "select * from `" + table + "`";
		String result = "";
		try {
			set = statement.executeQuery(sender);
			while (set.next())
				for (String collum: collums) {
					result += set.getString(collum) + ", ";
				}
			result += "\n";
		} catch(SQLException e) {
			String err = "\nAn error has occured with SQL\n" + e.getSQLState();
			result += err;
			System.out.println(err);
		} catch(Exception e) {
			String err = "\nAn error has occured with SQL\n" + e.getMessage();
			result += err;
			System.out.println(err);
		}
		return result;
	}
	
	/**	<strong>checkUser(username, password)</strong>
	 * <p>This will check the username and password against the database</p>
	 * @return This returns their permission level. -1 for user not existing.
	 */
	public int checkUser(String username, String password) {
		String sender = "select * from `db-users`";
		int permLvl = 0;
		try {
			set = statement.executeQuery(sender);
			while (set.next()) {
				if(username.equals(set.getString("username"))) {
					if(password.equals(set.getString("password"))) {
						permLvl = set.getInt("permLvl");
						break;
					} else {
						permLvl = -1;
					}
				} else {
					permLvl = -1;
				}
			}
		} catch(SQLException e) {
			errorMsg = "\nAn error has occured with SQL\n" + e.getSQLState();
			System.out.println(errorMsg);
		} catch(Exception e) {
			errorMsg = "\nAn error has occured in general\n" + e.getMessage();
			System.out.println(errorMsg);
		}
		return permLvl;
	}
}
