package main.SQL;

public class User {
	
	public String username = "";
	public String password = "";
	public String nickname = "";
	public int permLvl = 0;
	
	public User(String username, String password, int permLvl) {
		this.username = username;
		this.password = password;
		this.permLvl = permLvl;
	}
	
	public User(String username, String password, int permLvl, String nickname) {
		this.username = username;
		this.password = password;
		this.permLvl = permLvl;
		this.nickname = nickname;
	}
}
