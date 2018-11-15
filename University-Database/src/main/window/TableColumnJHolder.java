package main.window;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TableColumnJHolder {
	
	public JTextField typeName;
	public JComboBox<String> dropDownTypes;
	public JTextField length;
	
	public TableColumnJHolder(JTextField typeName, JComboBox<String> dropDownTypes, JTextField length) {
		this.typeName = typeName;
		this.dropDownTypes = dropDownTypes;
		this.length = length;
	}
}
