package core.elements;

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

@SuppressWarnings("serial")
public class WindowStuff extends JFrame {

	private boolean loggedIn = false;
	private LoginVerification loginVar;
	private UserClass currentUser;
	private ButtonContentPop content2Pop;
	private Font font;
	
	// Labels
	private JLabel titleThing;
	private JLabel loginLabel;
	private JLabel errorText;
	private JLabel passwordLabel;
	private JLabel contentLabel3;
	
	// Panels
	private JPanel buttonPanel;
	private JPanel titlePanel;
	private JPanel contentPanel;
	private JPanel contentPanel2;
	private JPanel loginPanel;
	
	// Buttons
	private JButton loginButton;
	private JButton logoutButton;
	private JButton createUser;
	private JButton aboutProject;
	private JButton academicInterest;
	private JButton hobbies;
	
	private GridBagConstraints grid;
	
	public WindowStuff() {
		super("GUI Project Database Manager");
		
		loginVar = new LoginVerification();
		grid = new GridBagConstraints();
		grid.insets = new Insets(10, 10, 10, 10);
		content2Pop = new ButtonContentPop();
		
		labelText();
		buttons();
		panel();
		initContent();
		
		add(titlePanel, BorderLayout.NORTH);
		grid.gridx = 0;
		grid.gridy = 1;
		titlePanel.add(buttonPanel, grid);
		add(contentPanel, BorderLayout.CENTER);
		grid.gridy = 0;
		contentPanel.add(loginPanel, grid);
		grid.gridy = 1;
		contentPanel.add(contentPanel2, grid);
		grid.gridy = 2;
		contentPanel.add(errorText, grid);
	}
	
	private void labelText() {
		titleThing = new JLabel("Database GUI project");
		font = titleThing.getFont();
		
		titleThing.setFont(font.deriveFont(36f));
		titleThing.setToolTipText("by: Zachary Vanscoit");
		titleThing.setForeground(Color.WHITE);
		
		loginLabel = new JLabel();
		loginLabel.setForeground(Color.DARK_GRAY);
		loginLabel.setFont(font.deriveFont(14f));
		
		passwordLabel = new JLabel();
		passwordLabel.setForeground(Color.DARK_GRAY);
		passwordLabel.setFont(font.deriveFont(14f));
		
		errorText = new JLabel("Generic Error");
		errorText.setForeground(Color.RED);
		errorText.setFont(font.deriveFont(14f));
		errorText.setVisible(false);
		
		contentLabel3 = new JLabel("Default Text");
		contentLabel3.setBackground(Color.DARK_GRAY);
	}
	
	private void buttons() {
		// Initial button creation
		loginButton = new JButton("Log In");
		loginButton.setFont(font.deriveFont(14f));
		loginButton.setToolTipText("Lets you login or Logout");
		
		logoutButton = new JButton("Log out");
		logoutButton.setFont(font.deriveFont(14f));
		
		createUser = new JButton("Create new user");
		createUser.setFont(font.deriveFont(14f));
		
		aboutProject = new JButton("About my Project");
		aboutProject.setToolTipText("Learn more about the project");
		aboutProject.setFont(font.deriveFont(14f));
		
		academicInterest = new JButton("My Academic Interests");
		academicInterest.setToolTipText("More about what I like");
		academicInterest.setFont(font.deriveFont(14f));
		
		hobbies = new JButton("My Hobbies");
		hobbies.setToolTipText("More about me outside");
		hobbies.setFont(font.deriveFont(14f));
		
		// Setting actions for buttons
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLoggin button clicked");
				toggleLogging();
			}
		});
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLogout button clicked");
				toggleLogging();
			}
		});
		aboutProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tAboutProject button clicked");
				content2Pop.aboutProject(contentLabel3);
				contentPanel2.setVisible(false);
				contentPanel2.setVisible(true);
			}
		});
		academicInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tAcademicInterest button clicked");
				content2Pop.academicInterests(contentLabel3);
				contentPanel2.setVisible(false);
				contentPanel2.setVisible(true);
			}
		});
		hobbies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tHobbies button clicked");
				content2Pop.hobbies(contentLabel3);
				contentPanel2.setVisible(false);
				contentPanel2.setVisible(true);
			}
		});
		createUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				UserClass tempUser = new UserClass(loginVar.getUserInput().getText(),
						loginVar.getPasswordInput().getText());
				if (loginVar.checkUser(tempUser)) {
					
				}
			}
		});
	}
	
	private void panel() {
		buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setVisible(true);
		
		titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setVisible(true);
		
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setVisible(true);
		
		contentPanel2 = new JPanel(new GridBagLayout());
		contentPanel2.setBackground(Color.WHITE);
		contentPanel2.setVisible(false);
		
		loginPanel = new JPanel(new GridBagLayout());
		loginPanel.setBackground(Color.LIGHT_GRAY);
		
		titlePanel.add(titleThing);
		contentPanel2.add(contentLabel3);
	}
	
	private void quickRemove() {
		buttonPanel.removeAll();
		buttonPanel.setVisible(false);
		buttonPanel.setVisible(true);
		loginPanel.setVisible(true);
		contentPanel2.setVisible(false);
	}
	
	private void initContent() {
		loginLabel.setText("Login:");
		grid.gridx = 0;
		grid.gridy = 0;
		loginPanel.add(loginLabel, grid);
		grid.gridx = 1;
		loginPanel.add(loginVar.getUserInput(), grid);
		
		passwordLabel.setText("Password:");
		grid.gridx = 0;
		grid.gridy = 1;
		loginPanel.add(passwordLabel, grid);
		grid.gridx = 1;
		loginPanel.add(loginVar.getPasswordInput(), grid);
		
		grid.gridx = 0;
		grid.gridy = 2;
		loginPanel.add(loginVar.getDropDown(), grid);
		grid.gridx = 1;
		loginPanel.add(loginButton, grid);
		
		grid.gridy = 3;
		loginPanel.add(createUser, grid);
		
		loginPanel.setVisible(true);
	}
	
	private void populateContent(UserClass user) {
		System.out.println("Logging in user: "+user.getUsername()+
				"\nWith permission level: "+user.getPermissionLevel());
		loginVar.getUserInput().setText("");
		loginVar.getPasswordInput().setText("");
		loginVar.getDropDown().setSelectedIndex(0);
		loginPanel.setVisible(false);
		if (user.getPermissionLevel() >= 1) {
			grid.gridy = 0;
			grid.gridx = 1;
			buttonPanel.add(aboutProject, grid);
		}
		if (user.getPermissionLevel() >= 2) {
			grid.gridx = 2;
			buttonPanel.add(academicInterest, grid);
		}
		if (user.getPermissionLevel() >= 3) {
			grid.gridx = 3;
			buttonPanel.add(hobbies, grid);
		}
		grid.gridx = 4;
		buttonPanel.add(logoutButton);
		content2Pop.aboutProject(contentLabel3);
		contentPanel2.setVisible(true);
		buttonPanel.setVisible(false);
		buttonPanel.setVisible(true);
	}
	
	@SuppressWarnings("deprecation")
	public void toggleLogging() {
		if (loggedIn) {
			quickRemove();
			//initContent();
			currentUser = null;
			loggedIn = false;
		} else {
			currentUser = new UserClass(loginVar.getUserInput().getText(),
					loginVar.getPasswordInput().getText(),
					loginVar.getDropDown().getItemAt(loginVar.getDropDown().getSelectedIndex()));
			System.out.println("Testing username: "+loginVar.getUserInput().getText()+
					"\nTesting Password: "+loginVar.getPasswordInput().getText()+
					"\nWith usertype: "+loginVar.getDropDown().getItemAt(loginVar.getDropDown().getSelectedIndex()));
			if (loginVar.checkUser(currentUser)) {
				errorText.setVisible(false);
				populateContent(currentUser);
				loggedIn = true;
			} else {
				System.out.println("User does not exist or password incorrect.");
				errorText.setText("User does not exist or password incorrect.");
				errorText.setVisible(true);
				currentUser = null;
			}
		}
	}
}
