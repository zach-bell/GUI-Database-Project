package main.SQL;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectorDB {
	
	public boolean connected = false;
	
	public String errorMsg = "No errors";
	
	private Connection con;
	
	private DatabaseMetaData dbMetaData;
	
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
			dbMetaData = con.getMetaData();
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
		String sender = "select * from `" + table + "`" + " where " + conditions;
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
			String err = "\nAn error has occured in general\n" + e.getMessage();
			result += err;
			System.out.println(err);
		}
		return result;
	}
	
	public ArrayList<String> listTables() {
		ArrayList<String> returnList = new ArrayList<String>();
		try {
			String[] types = {"TABLE"};
			set = dbMetaData.getTables(null, null, "%", types);
			while (set.next()) {
				returnList.add(set.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			System.out.println("\nAn error has occured in SQL\n" + e.getMessage());
			errorMsg = e.getSQLState();
		} catch (Exception e) {
			System.out.println("\nAn error has occured in general\n" + e.getMessage());
			errorMsg = e.getMessage();
		}
		return returnList;
	}
	
	public ArrayList<String> listDataTypes() {
		ArrayList<String> returnList = new ArrayList<String>();
	    try {
			set = dbMetaData.getTypeInfo();
		    while (set.next()) {
		    	returnList.add(set.getString("TYPE_NAME"));
		    }
	    } catch (SQLException e) {
			System.out.println("\nAn error has occured in SQL\n" + e.getMessage());
			errorMsg = e.getSQLState();
		} catch (Exception e) {
			System.out.println("\nAn error has occured in general\n" + e.getMessage());
			errorMsg = e.getMessage();
		}
	    return returnList;
	}
	
	public boolean createTable(String tableName, TableType[] tableInfo) {
		try {
			String sql = "create table if not exists `" + tableName +"` (";
			for (int i = 0; i < tableInfo.length; i++) {
				sql += "`" + tableInfo[i].nameTypes + "` ";
				sql += tableInfo[i].types + "(";
				if (tableInfo[i].lengths != null)
					sql += tableInfo[i].lengths;
				sql += ")";
				if (i + 1 != tableInfo.length) {
					sql += ", ";
				}
			}
			if (tableInfo[0].primaryKey) {
				sql += ", primary key (`" + tableInfo[0].primary + "`)";
			}
			sql += ");";
			System.out.println("\n-------------------\n" + "Sending to database:\n" + sql + "\n\n");
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("\nAn error has occured in SQL\n" + e.getMessage());
			errorMsg = e.getSQLState();
			return false;
		} catch (Exception e) {
			System.out.println("\nAn error has occured in general\n" + e.getMessage());
			errorMsg = e.getMessage();
			return false;
		}
		return true;
	}
	
	public String databaseRaw(String command) {
		String returnString = "";
		try {
			set = statement.executeQuery(command);
			while (set.next()) {
				returnString += set.getMetaData().toString();
				returnString += "\n";
			}
		} catch(SQLException e) {
			String err = "\nAn error has occured with SQL\n" + e.getSQLState();
			errorMsg = e.getSQLState();
			returnString += err;
		} catch(Exception e) {
			String err = "\nAn error has occured in general\n" + e.getMessage();
			errorMsg = e.getMessage();
			returnString += err;
		}
		returnString += "\n";
		return returnString;
	}
	
	/**	<strong>checkUser(username, password)</strong>
	 * <p>This will check the username and password against the database</p>
	 * @return This returns their permission level. -1 for user not existing.
	 */
	public User checkUser(String username, String password) {
		String sender = "select * from `db-users`";
		User temp = new User(username, password, 0);
		try {
			set = statement.executeQuery(sender);
			while (set.next()) {
				if(temp.username.equals(set.getString("username"))) {
					if(temp.password.equals(set.getString("password"))) {
						temp.permLvl = set.getInt("permLvl");
						temp.nickname = set.getString("nickname");
						break;
					} else {
						temp.permLvl = -1;
					}
				} else {
					temp.permLvl = -1;
				}
			}
		} catch(SQLException e) {
			errorMsg = "\nAn error has occured with SQL\n" + e.getSQLState();
			System.out.println(errorMsg);
			errorMsg = e.getSQLState();
		} catch(Exception e) {
			errorMsg = "\nAn error has occured in general\n" + e.getMessage();
			System.out.println(errorMsg);
			errorMsg = e.getMessage();
		}
		return temp;
	}
}
