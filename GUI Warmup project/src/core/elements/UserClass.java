package core.elements;

public class UserClass {
	
	private String username;
	private String usertype;
	private String nickname;
	private String password;
	
	private int permissionLevel = 1;
	
	/** <strong>Creates a user object to interact with.</strong>
	 * 
	 * @param username is required
	 * @param password
	 */
	public UserClass(String username, String password) {
		this.username = username;
		this.password = password;
		usertype = LoginVerification.USER_CUS;
	}
	
	public UserClass(String username, String usertype, String nickname) {
		this.username = username;
		this.usertype = usertype;
		this.nickname = nickname;
	}
	
	public UserClass(String username, String usertype, int permissionLevel) {
		this.username = username;
		this.usertype = usertype;
		this.permissionLevel = permissionLevel;
	}
	
	public UserClass(String username, String usertype, int permissionLevel, String nickname) {
		this.username = username;
		this.usertype = usertype;
		this.nickname = nickname;
		this.permissionLevel = permissionLevel;
	}

	public String getUsername() {
		return username;
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
