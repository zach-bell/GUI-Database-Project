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
		testInit();
	}
	
	public boolean checkUniqueUser(UserClass user) {
		for (UserClass u : userList) {
			if (u.getUsername().equals(user.getUsername()))
				return false;
		}
		return true;
	}
	
	public boolean addUser(UserClass user) {
		if (checkUniqueUser(user)) {
			userList.add(user);
			return true;
		}
		System.out.println("User already exists!");
		return false;
	}
	
	public void testInit() {
		addUser(new UserClass("admin","admin", USER_ADMIN));
		addUser(new UserClass("staff1","staff1",USER_STAFF));
		addUser(new UserClass("cus1","cus1",USER_CUS));
		addUser(new UserClass("james1","james1",USER_CUS,"James"));
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
