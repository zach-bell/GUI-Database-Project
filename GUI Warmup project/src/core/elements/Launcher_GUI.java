package core.elements;

import javax.swing.JFrame;

public class Launcher_GUI {

	public static WindowStuff win = new WindowStuff();
	
	public static void main(String[] args) {
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(1200, 600);
		win.setVisible(true);
		
	}
}
