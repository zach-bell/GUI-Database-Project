package core.elements;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginVerification {
	
	public static final String USER_ADMIN = "admin";
	public static final String USER_STAFF = "staff";
	public static final String USER_CUS = "customer";	// Default user
	private String[] usertypes = {USER_ADMIN,USER_STAFF,USER_CUS};

	private JTextField userInput;
	private JPasswordField passInput;
	private JComboBox<String> userTypesCB = new JComboBox<String>(usertypes);
	
	private ArrayList<UserClass> userList;
	
	public LoginVerification() {
		userList = new ArrayList<UserClass>();
		userInput = new JTextField(15);
		passInput = new JPasswordField(15);
	}
	
	public void testInit() {
		userList.add(new UserClass("admin1",USER_ADMIN));
	}
	
	public JTextField getUserInput() {
		return userInput;
	}
	
	public JPasswordField getPasswordInput() {
		return passInput;
	}
	
	public JComboBox<String> getDropDown() {
		return userTypesCB;
	}
}
