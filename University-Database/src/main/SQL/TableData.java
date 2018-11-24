package main.SQL;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

public class TableData {
	
	public String tableName;
	public String[] data;
	public String primaryKeyColumn;
	public int primaryKeyIndexInData;
	
	public TableData(String tableName, String[] data, String primaryKeyColumn, int primaryKeyIndexInData) {
		this.tableName = tableName;
		this.data = data;
		this.primaryKeyColumn = primaryKeyColumn;
		this.primaryKeyIndexInData = primaryKeyIndexInData;
	}
}
