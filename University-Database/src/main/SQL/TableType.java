package main.SQL;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

public class TableType {
	
	public String nameTypes;
	public String types;
	public String lengths;
	public boolean primaryKey = false;
	public String primary;
	
	public TableType(String nameTypes, String types) {
		this.nameTypes = nameTypes;
		this.types = types;
	}
	public TableType(String nameTypes, String types, String lengths) {
		this.nameTypes = nameTypes;
		this.types = types;
		this.lengths = lengths;
	}
	public TableType(String nameTypes, String types, String lengths, boolean primaryKey, String primary) {
		this.nameTypes = nameTypes;
		this.types = types;
		this.lengths = lengths;
		this.primaryKey = primaryKey;
		this.primary = primary;
	}
}
