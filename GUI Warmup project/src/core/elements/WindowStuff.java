package core.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
	
	private JLabel titleThing;
	private JPanel buttonPanel;
	private JPanel titlePanel;
	private JButton loginButton;
	private JButton aboutProject;
	private JButton academicInterest;
	private JButton hobbies;
	
	private GridBagConstraints grid;
	
	public WindowStuff() {
		super("Taicun is upset");
		
		grid = new GridBagConstraints();
		
		labelText();
		buttons();
		panel();
		
		add(titlePanel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.NORTH);
	}
	
	private void labelText() {
		titleThing = new JLabel("Database GUI project");
		titleThing.setToolTipText("by: Zachary Vanscoit");
		titleThing.setForeground(Color.WHITE);
	}
	
	private void buttons() {
		loginButton = new JButton("Log In");
		aboutProject = new JButton("About my Project");
		academicInterest = new JButton("My Academic Interests");
		hobbies = new JButton("My Hobbies");
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleLogging();
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
		
		grid.insets = new Insets(10, 10, 10, 10);
		grid.gridx = 2;
		grid.gridy = 1;
		titlePanel.add(titleThing, grid);
		grid.gridx = 0;
		grid.gridy = 2;
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
		grid.gridx = 1;
		buttonPanel.add(aboutProject, grid);
		grid.gridx = 2;
		buttonPanel.add(academicInterest, grid);
		grid.gridx = 3;
		buttonPanel.add(hobbies, grid);
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
