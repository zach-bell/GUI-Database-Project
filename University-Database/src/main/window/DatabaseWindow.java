package main.window;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

import javax.swing.JFrame;

import main.SQL.ConnectorDB;

@SuppressWarnings("serial")
public class DatabaseWindow extends JFrame {
	
	private ConnectorDB connection;
	@SuppressWarnings("unused")
	private Frames frames;
	
	public DatabaseWindow() {
		super("Database Manager");	// Sets the title of the window
		
		// Initializers
		connection = new ConnectorDB();
		frames = new Frames(this, connection);
	}
	
	
}
