package main.SQL;

public class User {
	
	public String username = "";
	public String password = "";
	public String nickname = "";
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, String nickname) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}
}
