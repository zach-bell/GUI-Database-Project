package main.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.JLabel;

import main.SQL.ConnectorDB;

public class Frames {
	
	private ConnectorDB connector;
	private Screen currentScreen;
	private Screen loginScreen;
	
	// Labels
	private JLabel titleLabel;
	
	// JFrame vars
	private GridBagConstraints grid;
	private Font font;
	
	public Frames(ConnectorDB connector, Screen currentScreen) {
		this.connector = connector;
		this.currentScreen = currentScreen;
		
		loginScreen = new Screen();
	}
	
	public void populateLoginFrame() {
		titleLabel = new JLabel("LOGIN");
		font = titleLabel.getFont();
		
		titleLabel.setFont(font.deriveFont(36f));
		titleLabel.setToolTipText("by: Zachary Vanscoit");
		titleLabel.setForeground(Color.WHITE);
		
		
	}
	
	public Screen getCurrentScreen() {
		return currentScreen;
	}
}
