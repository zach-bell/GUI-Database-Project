package core.elements;

import javax.swing.JLabel;

public class ButtonContentPop {
	
	public void aboutProject(JLabel label) {
		label.setText("<html><p><font size=\"6\"> This is my database project GUI. What you might see in this project<br/>"
				+ "are some nice visuals using a library system know as processing<br/>"
				+ "displaying data from a test hotel database. Staff can check the hotel<br/>"
				+ "and see who's in what rooms and over time how many customers stayed.</font></p></html>");
	}
	
	public void academicInterests(JLabel label) {
		label.setText("<html><p><font size=\"6\"> I have more artistic approaches to coding and designing and really<br/>"
				+ "love the idea of visual representations of data. I also have interest<br/>"
				+ "in interactive media and the complexity of procedural algorithms in<br/>"
				+ "multiple dimensions of the sort.</font></p></html>");
	}
	
	public void hobbies(JLabel label) {
		label.setText("<html><p><font size=\"6\"> My hobbies include running my club under SIU eSports being all<br/>"
				+ "rhythm based arcade games such as Dance Dance Revolution, Guitar Hero,<br/>"
				+ "and many others of the sort. I also am the treasurer for the Aikido<br/>"
				+ "martial arts club at SIU. I am also the Secretary for the SIU eSports.</font></p></html>");
	}
}
