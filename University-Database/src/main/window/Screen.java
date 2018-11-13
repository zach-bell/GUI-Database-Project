package main.window;

import javax.swing.JPanel;

public class Screen {
	
	public static final int USER = 1;
	public static final int STAFF = 2;
	public static final int ADMIN = 3;
	
	public int permLvl = 0;
	
	public JPanel activePanel;
	
	public Screen() {
		permLvl = 1;
		activePanel = new JPanel();
	}
	
	public Screen(int permLvl) {
		this.permLvl = permLvl;
		activePanel = new JPanel();
	}
}
