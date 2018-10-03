package core.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
	
	// Labels
	private JLabel titleThing;
	private JLabel contentLabel;
	private JLabel contentLabel2;
	
	// Panels
	private JPanel buttonPanel;
	private JPanel titlePanel;
	private JPanel contentPanel;
	
	// Buttons
	private JButton loginButton;
	private JButton aboutProject;
	private JButton academicInterest;
	private JButton hobbies;
	
	private GridBagConstraints grid;
	
	public WindowStuff() {
		super("GUI Project Database Manager");
		
		loginVar = new LoginVerification();
		grid = new GridBagConstraints();
		
		labelText();
		buttons();
		panel();
		initContent();
		
		add(titlePanel, BorderLayout.SOUTH);
		add(buttonPanel, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
	}
	
	private void labelText() {
		// Title label
		titleThing = new JLabel("Database GUI project");
		Font font = titleThing.getFont();
		titleThing.setFont(font.deriveFont(24f));
		titleThing.setToolTipText("by: Zachary Vanscoit");
		titleThing.setForeground(Color.WHITE);
		
		contentLabel = new JLabel();
		contentLabel.setForeground(Color.DARK_GRAY);
		contentLabel2 = new JLabel();
		contentLabel2.setForeground(Color.DARK_GRAY);
		
	}
	
	private void buttons() {
		loginButton = new JButton("Log In");
		loginButton.setToolTipText("Lets you login or Logout");
		aboutProject = new JButton("About my Project");
		aboutProject.setToolTipText("Learn more about the project");
		academicInterest = new JButton("My Academic Interests");
		academicInterest.setToolTipText("More about what I like");
		hobbies = new JButton("My Hobbies");
		hobbies.setToolTipText("More about me outside");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleLogging();
				System.out.println("Loggin button clicked");
			}
		});
		aboutProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("AboutProject button clicked");
			}
		});
		academicInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("AcademicInterest button clicked");
			}
		});
		hobbies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hobbies button clicked");
			}
		});
	}
	
	private void panel() {
		buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setVisible(true);
		
		titlePanel = new JPanel(new FlowLayout());
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setVisible(true);
		
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setVisible(true);
		
		grid.insets = new Insets(10, 10, 10, 10);
		titlePanel.add(titleThing);
		grid.gridx = 0;
		grid.gridy = 0;
		buttonPanel.add(loginButton, grid);
	}
	
	private void quickRemove() {
		buttonPanel.remove(aboutProject);
		buttonPanel.remove(academicInterest);
		buttonPanel.remove(hobbies);
		buttonPanel.setVisible(false);
		buttonPanel.setVisible(true);
	}
	
	private void quickAdd() {
		grid.gridy = 0;
		grid.gridx = 1;
		buttonPanel.add(aboutProject, grid);
		grid.gridx = 2;
		buttonPanel.add(academicInterest, grid);
		grid.gridx = 3;
		buttonPanel.add(hobbies, grid);
		buttonPanel.setVisible(false);
		buttonPanel.setVisible(true);
	}
	
	private void initContent() {
		contentLabel.setText("Login:");
		grid.gridx = 0;
		grid.gridy = 0;
		contentPanel.add(contentLabel, grid);
		grid.gridx = 1;
		contentPanel.add(loginVar.getUserInput(), grid);
		
		contentLabel2.setText("Password:");
		grid.gridx = 0;
		grid.gridy = 1;
		contentPanel.add(contentLabel2, grid);
		grid.gridx = 1;
		contentPanel.add(loginVar.getPasswordInput(), grid);
		grid.gridy = 2;
		contentPanel.add(loginVar.getDropDown(), grid);
	}
	
	public void toggleLogging() {
		if (loggedIn) {
			loginButton.setText("Log In");
			quickRemove();
			loggedIn = false;
		} else {
			loginButton.setText("Log out");
			quickAdd();
			loggedIn = true;
		}
	}
}
