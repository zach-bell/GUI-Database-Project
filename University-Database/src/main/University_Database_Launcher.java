package main;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

import javax.swing.JFrame;

import main.window.DatabaseWindow;

public class University_Database_Launcher {

	// creates a window object to draw the screen
	private static DatabaseWindow win;
	
	public static void main(String[] args) {
		win = new DatabaseWindow();								// inits window
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// sets the close operation to exit
		win.setSize(1200, 600);									// Sets size to Width 1,200 by Height 600
		win.setVisible(true);									// Sets the window visible
	}
}
