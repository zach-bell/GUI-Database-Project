package main.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.SQL.ConnectorDB;
import main.SQL.User;

public class Frames {
	
	// General vars
	private ConnectorDB connector;
	private JFrame mainFrame;
	private boolean loggedIn = false;
	public User currentUser;
	
	// Fields
	private JTextField userInput;
	private JPasswordField passInput;
	private JTextField terminalField;
	private JTextArea terminalArea;
	
	// Labels
	private JLabel titleLabel;
	private JLabel loginLabel;
	private JLabel passwordLabel;
	private JLabel errorText;
	
	// Panels
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JPanel contentPanel;
	private JPanel subPanel;
	private JScrollPane scrollTerminalPanel;
	
	// Buttons
	private JButton loginButton;
	private JButton logoutButton;
	private JButton homeButton;
	private JButton createButton;
	private JButton exploreButton;
	private JButton terminalButton;
	
	// JFrame vars
	private GridBagConstraints grid;
	private Font font;
	
	public Frames(JFrame mainFrame, ConnectorDB connector) {
		this.mainFrame = mainFrame;
		this.connector = connector;
		
		// Inits
		generalInits();
		labelInit();
		panelInit();
		buttonInit();
		mainInit();
		
		// Populate
		populateLoginFrame();
	}
	
	private void generalInits() {
		currentUser = new User("","",0);
		
		grid = new GridBagConstraints();
		grid.insets = new Insets(10, 10, 10, 10);
		
		userInput = new JTextField(15);
		passInput = new JPasswordField(15);
		terminalField = new JTextField(58);
		terminalField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = terminalField.getText();
				terminalField.setText("");
				terminalArea.append(command + "\n");
				terminalArea.append(connector.databaseRaw(command)+"\n");
			}
		});
		
		terminalArea = new JTextArea(16, 58);
		terminalArea.setBackground(Color.DARK_GRAY);
		terminalArea.setForeground(Color.GREEN);
		terminalArea.setEditable(false);
		
		scrollTerminalPanel = new JScrollPane(terminalArea);
	}
	
	private void populateLoginFrame() {
		refreshPanel(contentPanel);
		JPanel loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.LIGHT_GRAY);
		loginPanel.setVisible(true);
		
		titleLabel.setText("Login");
		loginLabel.setText("Login:");
		passwordLabel.setText("Password:");
		
		panelGridAdd(loginPanel, loginLabel, 0, 0);
		panelGridAdd(loginPanel, userInput, 1, 0);
		panelGridAdd(loginPanel, passwordLabel, 0, 1);
		panelGridAdd(loginPanel, passInput, 1, 1);
		panelGridAdd(loginPanel, loginButton, 1, 2);
		panelGridAdd(contentPanel, loginPanel, 0, 0);
		
		if (!connector.connected) {
			errorText.setText("Database is offline or unreachable");
			errorText.setVisible(true);
		}
	}
	
	private void populateCreateFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Create");
		
	}
	
	private void populateExploreFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Explore");
		
		JScrollPane tableList = new JScrollPane();
		String[] tableListArray = connector.listTables().toArray(new String[connector.listTables().size()]);
		for (String tableName : tableListArray) {
			JButton tableEntry = new JButton(tableName);
			tableEntry.setFont(font.deriveFont(14f));
			tableList.add(tableEntry);
		}
		tableList.setVisible(true);
		panelGridAdd(contentPanel, tableList, 0, 0);
	}
	
	private void populateTerminalFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Terminal");
		
		panelGridAdd(contentPanel, scrollTerminalPanel, 0, 0);
		panelGridAdd(contentPanel, terminalField, 0, 1);
	}
	
	private void populateHomeFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Home");
		
		
	}
	
	private void populateAdminFrame() {
		refreshPanel(contentPanel);
		System.out.println("Admin Panel Initialized");
		
		panelGridAdd(buttonPanel, homeButton, 0, 0);
		panelGridAdd(buttonPanel, createButton, 1, 0);
		panelGridAdd(buttonPanel, exploreButton, 2, 0);
		panelGridAdd(buttonPanel, terminalButton, 3, 0);
		panelGridAdd(buttonPanel, logoutButton, 4, 0);
	}
	
	private void panelGridAdd(JPanel panel, Component comp, int gx, int gy) {
		grid.gridx = gx;
		grid.gridy = gy;
		panel.add(comp, grid);
	}
	
	private void mainInit() {
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		mainFrame.add(contentPanel, BorderLayout.CENTER);
		mainFrame.add(errorText, BorderLayout.SOUTH);
	}
	
	private void labelInit() {
		titleLabel = new JLabel("LOGIN");
		font = titleLabel.getFont();
		terminalArea.setFont(font.deriveFont(12f));
		
		titleLabel.setFont(font.deriveFont(24f));
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
	
	private void panelInit() {
		titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setVisible(true);
		titlePanel.add(titleLabel);
		
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setVisible(true);
		
		subPanel = new JPanel(new GridBagLayout());
		subPanel.setBackground(Color.LIGHT_GRAY);
		subPanel.setVisible(true);
		
		buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setVisible(true);
		
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setVisible(true);
		
		grid.gridx = 0;
		grid.gridy = 1;
		titlePanel.add(buttonPanel, grid);
	}
	
	private void buttonInit() {
		// login button
		loginButton = new JButton("Log In");
		loginButton.setFont(font.deriveFont(14f));
		loginButton.setToolTipText("Lets you login");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLoggin button clicked");
				toggleLogging();
			}
		});
		
		// logout button
		logoutButton = new JButton("Log out");
		logoutButton.setFont(font.deriveFont(14f));
		logoutButton.setToolTipText("Lets you logout");
		
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLogout button clicked");
				toggleLogging();
			}
		});
		
		homeButton = new JButton("Home");
		homeButton.setToolTipText("Displays the Home panel");
		homeButton.setFont(font.deriveFont(14f));
		
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tHome button clicked");
				populateHomeFrame();
			}
		});
		
		createButton = new JButton("Create");
		createButton.setToolTipText("Displays the Creation panel");
		createButton.setFont(font.deriveFont(14f));
		
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tCreate button clicked");
				populateCreateFrame();
			}
		});
		
		exploreButton = new JButton("Explore");
		exploreButton.setToolTipText("Displays the Explore panel");
		exploreButton.setFont(font.deriveFont(14f));
		
		exploreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tExplore button clicked");
				populateExploreFrame();
			}
		});
		
		terminalButton = new JButton("Terminal");
		terminalButton.setToolTipText("Displays the Terminal panel");
		terminalButton.setFont(font.deriveFont(14f));
		
		terminalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tExplore button clicked");
				populateTerminalFrame();
			}
		});
	}
	
	private void refreshPanel(JPanel panel) {
		panel.removeAll();
		panel.setVisible(false);
		panel.setVisible(true);
	}
	
	private void setLogin() {
		if (currentUser.permLvl == 3) {
			refreshPanel(buttonPanel);
			refreshPanel(contentPanel);
			populateAdminFrame();
		} else
		if (currentUser.permLvl == 2) {
			refreshPanel(buttonPanel);
			refreshPanel(contentPanel);
		} else
		if (currentUser.permLvl == 1) {
			refreshPanel(buttonPanel);
			refreshPanel(contentPanel);
		}
		if (currentUser.permLvl > 0) {
			populateHomeFrame();
		} else {
			refreshPanel(buttonPanel);
			refreshPanel(contentPanel);
			toggleLogging();
		}
	}
	
	@SuppressWarnings("deprecation")
	private void toggleLogging() {
		if (loggedIn) {
			buttonPanel.removeAll();
			populateLoginFrame();
			loggedIn = false;
		} else {
			System.out.println("Testing username: "+userInput.getText()+
					"\nTesting Password: "+passInput.getText());
			User temp = connector.checkUser(userInput.getText(), passInput.getText());
			if (temp.permLvl > 0) {
				errorText.setVisible(false);
				System.out.println("\nUser logged in successfully.\n");
				currentUser = temp;
				setLogin();
				userInput.setText("");
				passInput.setText("");
				loggedIn = true;
			} else {
				errorText.setVisible(false);
				System.out.println("User does not exist or password incorrect.");
				errorText.setText("User does not exist or password incorrect.");
				errorText.setVisible(true);
			}
		}
	}
}
