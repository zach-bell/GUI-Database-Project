package core.elements;

public class UserClass {
	
	private String username;
	private String usertype;
	private String nickname;
	private String password;
	
	private int permissionLevel = 1;
	
	/** <strong>Creates a user object to interact with.</strong>
	 * 
	 * @param username for the user
	 * @param password
	 */
	public UserClass(String username, String password) {
		this.username = username;
		this.password = password;
		usertype = LoginVerification.USER_CUS;
	}
	
	public UserClass(String username, String password, String usertype) {
		this.username = username;
		this.usertype = usertype;
		this.password = password;
	}
	
	public UserClass(String username, String password, String usertype, String nickname) {
		this.username = username;
		this.usertype = usertype;
		this.nickname = nickname;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getUserType() {
		return usertype;
	}
	public String getNickName() {
		return nickname;
	}
	public int getPermissionLevel() {
		return permissionLevel;
	}
}
