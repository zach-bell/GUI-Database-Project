package core.elements;

public class UserClass {
	
	private String username;
	private String usertype;
	private String nickname;
	private String password;
	
	private int permissionLevel = 3;
	
	/** <strong>Creates a user object to interact with.</strong>
	 * 
	 * @param username for the user
	 * @param password for the user
	 */
	public UserClass(String username, String password) {
		this.username = username;
		this.password = password;
		usertype = LoginVerification.USER_CUS;
	}
	
	/** <strong>Creates a user object to interact with.</strong>
	 * 
	 * @param username for the user
	 * @param password for the user
	 * @param usertype can be found at LoginVerification.USER_XXX
	 * Default would be USER_CUS being permission level 1
	 */
	public UserClass(String username, String password, String usertype) {
		this.username = username;
		this.password = password;
		this.usertype = usertype;
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
		try {
			if (nickname == null)
				return username;
		} catch(NullPointerException e) {
			return username;
		}
		return nickname;
	}
	public int getPermissionLevel() {
		return permissionLevel;
	}
	public void setPermissionLevel(int perm) {
		permissionLevel = perm;
	}
}
