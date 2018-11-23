package main.SQL;

public class TableData {
	
	public String tableName;
	public String[] data;
	public int index;
	
	public TableData(String tableName, String[] data, int index) {
		this.tableName = tableName;
		this.data = data;
		this.index = index;
	}
}
