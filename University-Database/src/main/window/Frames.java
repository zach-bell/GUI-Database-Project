package main.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.SQL.ConnectorDB;

public class Frames {
	
	private ConnectorDB connector;
	private JFrame mainFrame;
	private boolean loggedIn = false;
	
	// Fields
	private JTextField userInput;
	private JPasswordField passInput;
	
	// Screens
	private Screen currentScreen;
	private Screen loginScreen;
	private Screen adminScreen;
	private Screen staffScreen;
	private Screen customerScreen;
	
	// Labels
	private JLabel titleLabel;
	private JLabel loginLabel;
	private JLabel passwordLabel;
	private JLabel errorText;
	
	// Panels
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JPanel contentPanelInard;
	
	// Button
	private JButton loginButton;
	private JButton logoutButton;
	
	// JFrame vars
	private GridBagConstraints grid;
	private Font font;
	
	public Frames(JFrame mainFrame, ConnectorDB connector, Screen currentScreen) {
		this.mainFrame = mainFrame;
		this.connector = connector;
		this.currentScreen = currentScreen;
		
		loginScreen = new Screen();
		adminScreen = new Screen();
		staffScreen = new Screen();
		customerScreen = new Screen();
		
		grid = new GridBagConstraints();
		grid.insets = new Insets(10, 10, 10, 10);
		userInput = new JTextField(15);
		passInput = new JPasswordField(15);
		
		// Inits
		labelInit();
		panelInit();
		buttonInit();
		mainInit();
		
		// Populate
		populateLoginFrame();
		
		// Sets the screen to login
		setCurrentScreen(loginScreen);
	}
	
	public void setCurrentScreen(Screen screen) {
		currentScreen = screen;
		currentScreen.panel.setVisible(false);
		currentScreen.panel.setVisible(true);
	}
	
	public void populateLoginFrame() {
		JPanel loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.LIGHT_GRAY);
		loginPanel.setVisible(true);
		
		grid.gridy = 0;
		loginScreen.panel.add(loginPanel, grid);
		grid.gridy = 1;
		loginScreen.panel.add(contentPanelInard, grid);
		grid.gridy = 2;
		loginScreen.panel.add(errorText, grid);
		
		loginLabel.setText("Login:");
		grid.gridx = 0;
		grid.gridy = 0;
		loginPanel.add(loginLabel, grid);
		grid.gridx = 1;
		loginPanel.add(userInput, grid);
		
		passwordLabel.setText("Password:");
		grid.gridx = 0;
		grid.gridy = 1;
		loginPanel.add(passwordLabel, grid);
		grid.gridx = 1;
		loginPanel.add(passInput, grid);
		grid.gridy = 2;
		grid.gridx = 1;
		loginPanel.add(loginButton, grid);
		
		if (!connector.connected) {
			errorText.setText("Database is offline or unreachable");
			errorText.setVisible(true);
		}
	}
	
	public void populateAdminFrame() {
		JPanel adminPanel = new JPanel(new GridBagLayout());
		adminPanel.setBackground(Color.LIGHT_GRAY);
		
		
	}
	
	public void mainInit() {
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		mainFrame.add(loginScreen.panel, BorderLayout.CENTER);
	}
	
	public void labelInit() {
		titleLabel = new JLabel("LOGIN");
		font = titleLabel.getFont();
		
		titleLabel.setFont(font.deriveFont(36f));
		titleLabel.setToolTipText("by: Zachary Vanscoit");
		titleLabel.setForeground(Color.WHITE);
		
		loginLabel = new JLabel();
		loginLabel.setForeground(Color.DARK_GRAY);
		loginLabel.setFont(font.deriveFont(14f));
		
		passwordLabel = new JLabel();
		passwordLabel.setForeground(Color.DARK_GRAY);
		passwordLabel.setFont(font.deriveFont(14f));
		
		errorText = new JLabel("");
		errorText.setForeground(Color.RED);
		errorText.setFont(font.deriveFont(14f));
		errorText.setVisible(false);
	}
	
	public void panelInit() {
		titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setVisible(true);
		titlePanel.add(titleLabel);
		
		loginScreen.panel = new JPanel(new GridBagLayout());
		loginScreen.panel.setBackground(Color.WHITE);
		loginScreen.panel.setVisible(true);
		
		buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setVisible(true);
		
		grid.gridx = 0;
		grid.gridy = 1;
		titlePanel.add(buttonPanel, grid);
		
		contentPanelInard = new JPanel(new GridBagLayout());
		contentPanelInard.setBackground(Color.WHITE);
		contentPanelInard.setVisible(false);
	}
	
	public void buttonInit() {
		// login button
		loginButton = new JButton("Log In");
		loginButton.setFont(font.deriveFont(14f));
		loginButton.setToolTipText("Lets you login or Logout");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLoggin button clicked");
				toggleLogging();
			}
		});
		
		// logout button
		logoutButton = new JButton("Log out");
		logoutButton.setFont(font.deriveFont(14f));
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLogout button clicked");
				toggleLogging();
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	public void toggleLogging() {
		if (loggedIn) {
			setCurrentScreen(loginScreen);
			loggedIn = false;
		} else {
			System.out.println("Testing username: "+userInput.getText()+
					"\nTesting Password: "+passInput.getText());
			if (connector.checkUser(userInput.getText(), passInput.getText()) != -1) {
				errorText.setVisible(false);
				System.out.println("\nUser logged in successfully.\n");
				loggedIn = true;
			} else {
				errorText.setVisible(false);
				System.out.println("User does not exist or password incorrect.");
				errorText.setText("User does not exist or password incorrect.");
				errorText.setVisible(true);
			}
		}
	}
	
	public Screen getCurrentScreen() {
		return currentScreen;
	}
}
