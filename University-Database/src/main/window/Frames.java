package main.window;

/* --------------------------
 * CS 430 - Database Project
 * By: Zachary Vanscoit
 * -------------------------- */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.SQL.ConnectorDB;
import main.SQL.TableData;
import main.SQL.TableType;
import main.SQL.User;

public class Frames {
	
	// General vars
	private ConnectorDB connector;
	private JFrame mainFrame;
	private boolean loggedIn = false;
	public User currentUser;
	private Calendar calendar;
	private Color buttonColor;
	private String[] listOfTypes;
	private int createTableEntriesCount = 1;
	private int createSubPanelPosition = 0;	// 0 for Create Table, 1 for Create Entry
	
	// Fields
	private JTextField userInput;
	private JPasswordField passInput;
	private JTextField terminalField;
	private JTextArea terminalArea;
	private JTextArea exploreArea;
	private JTextField tableNameField;
	private ArrayList<TableColumnJHolder> tableColumnJHolderList;
	private JComboBox<String> dropDownTables;
	private ArrayList<JTextField> entryFieldList;
	
	private ActionListener dropDownTablesAction;
	
	// Labels
	private JLabel titleLabel;
	private JLabel loginLabel;
	private JLabel passwordLabel;
	public JLabel errorText;
	private JLabel timeLabel;
	private JLabel createTabSubPanelTitle;
	private ArrayList<JLabel> tableLabelsList;
	
	// Panels
	private JPanel titlePanel;
	private JPanel buttonPanel;
	private JPanel contentPanel;
	private JPanel subPanel;
	private JPanel columnsTypesPanel;
	private JScrollPane scrollTerminalPanel;
	private JScrollPane subPanelScroll;
	
	// Buttons
	private JButton loginButton;
	private JButton logoutButton;
	private JButton homeButton;
	private JButton createButton;
	private JButton createTableButton;
	private JButton createEntryButton;
	private JButton deleteTableButton;
	private JButton sendUpdateButton;
	private JButton exploreButton;
	private JButton terminalButton;
	private ArrayList<JButton> exploreButtonTableList;
	
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
	
	/*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*	*
	 * This begins a massive class that should be expressed object oriented
	 * more than what it is now. There are barely any comments and its code
	 * that's all over the place. Sorry for this mess but I promise it works.
	 */
	
	private void populateHomeFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Home");
		
		JLabel infoText = new JLabel("Welcome " + currentUser.nickname + "!");
		infoText.setFont(font.deriveFont(14f));
		infoText.setForeground(Color.DARK_GRAY);
		JLabel infoText2 = new JLabel("The current time is:");
		infoText2.setFont(font.deriveFont(14f));
		infoText2.setForeground(Color.DARK_GRAY);
		
		panelGridAdd(contentPanel, infoText, 0, 0);
		panelGridAdd(contentPanel, infoText2, 0, 1);
		panelGridAdd(contentPanel, timeLabel, 0, 2);
	}
	
	//	-------------------------------------------------------------------------------------------------
	//		Home Frame
	//	-------------------------------------------------------------------------------------------------
	
	public void populateTableLists() {
		tableLabelsList.clear();
		String[] tableListArray = connector.listTables();
		for (int i = 0; i < tableListArray.length; i++) {
			JLabel tableEntry = new JLabel(tableListArray[i]);
			tableEntry.setFont(font.deriveFont(14f));
			tableLabelsList.add(tableEntry);
		}
	}
	
	//	-------------------------------------------------------------------------------------------------
	//		Terminal Frame
	//	-------------------------------------------------------------------------------------------------
	
	private void populateTerminalFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Terminal");
		
		panelGridAdd(contentPanel, scrollTerminalPanel, 0, 0);
		panelGridAdd(contentPanel, terminalField, 0, 1);
	}
	
	//	-------------------------------------------------------------------------------------------------
	//		Login Frame
	//	-------------------------------------------------------------------------------------------------
	
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
	
	//	-------------------------------------------------------------------------------------------------
	//		Create Frame
	//	-------------------------------------------------------------------------------------------------
	
	private void populateCreateFrame() {
		refreshPanel(contentPanel);
		titleLabel.setText("Create");
		subPanelScroll = new JScrollPane(subPanel);
		
		JLabel tableDisplayLabel = new JLabel("Currently Available Tables");
		tableDisplayLabel.setFont(font);
		tableDisplayLabel.setForeground(Color.DARK_GRAY);
		
		JPanel createButtonPanel = new JPanel(new GridBagLayout());
		createButtonPanel.setBackground(Color.GRAY);
		createButtonPanel.setVisible(true);
		
		JPanel adjustDisplayPanel = new JPanel(new GridBagLayout());
		adjustDisplayPanel.setBackground(Color.LIGHT_GRAY);
		adjustDisplayPanel.setVisible(true);
		
		JPanel tableDisplay = new JPanel(new GridBagLayout());
		tableDisplay.setBackground(Color.WHITE);
		tableDisplay.setVisible(true);
		
		JScrollPane tableList = new JScrollPane(tableDisplay);
		
		populateTableLists();
		
		int i = 0;
		for (JLabel table : tableLabelsList) {
			grid.gridy = i;
			tableDisplay.add(table, grid);
			i++;
		}
		
		populateCreateTableSubFrame();
		
		panelGridAdd(createButtonPanel, createTableButton, 0, 0);
		panelGridAdd(createButtonPanel, createEntryButton, 1, 0);
		
		panelGridAdd(contentPanel, tableDisplayLabel, 0, 0);
		panelGridAdd(contentPanel, createButtonPanel, 1, 0);
		panelGridAdd(contentPanel, tableList, 0, 1);
		panelGridAdd(contentPanel, adjustDisplayPanel, 1, 1);
		panelGridAdd(adjustDisplayPanel, createTabSubPanelTitle, 0, 0);
		panelGridAdd(adjustDisplayPanel, subPanelScroll, 0, 1);
		panelGridAdd(adjustDisplayPanel, sendUpdateButton, 0, 2);
	}
	
	private void populateCreateTableSubFrame() {
		subPanel.removeAll();
		createTabSubPanelTitle.setText("Create Table");
		createSubPanelPosition = 0;
		
		createTableEntriesCount = 1;
		JLabel tableNameLabel = new JLabel("Name of Table");
		tableNameLabel.setFont(font.deriveFont(12f));
		tableNameLabel.setForeground(Color.DARK_GRAY);
		JLabel typeNameLabel = new JLabel("Name of Column");
		typeNameLabel.setFont(font.deriveFont(12f));
		typeNameLabel.setForeground(Color.DARK_GRAY);
		JLabel typeLabel = new JLabel("Data Type");
		typeLabel.setFont(font.deriveFont(12f));
		typeLabel.setForeground(Color.DARK_GRAY);
		JLabel lengthLabel = new JLabel("Length");
		typeLabel.setFont(font.deriveFont(12f));
		typeLabel.setForeground(Color.DARK_GRAY);
		
		JButton clearTableButton = new JButton("Clear Table");
		clearTableButton.setToolTipText("Clears this panel and reinitializes it.");
		clearTableButton.setFont(font.deriveFont(12f));
		clearTableButton.setFocusPainted(false);
		clearTableButton.setBackground(buttonColor);
		clearTableButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tClear Table button clicked");
				populateCreateTableSubFrame();
			}
		});
		
		JButton addNewColumnButton = new JButton("Add Column");
		addNewColumnButton.setToolTipText("adds a new column to the table.");
		addNewColumnButton.setFont(font.deriveFont(12f));
		addNewColumnButton.setFocusPainted(false);
		addNewColumnButton.setBackground(buttonColor);
		addNewColumnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tAdd New Column button clicked");
				subPanelAddNewColumnType();
			}
		});
		
		subPanelAddNewColumnType();
		panelGridAdd(subPanel, tableNameLabel, 0, 0);
		panelGridAdd(subPanel, tableNameField, 1, 0);
		panelGridAdd(subPanel, clearTableButton, 2, 0);
		panelGridAdd(subPanel, addNewColumnButton, 3, 0);
		panelGridAdd(subPanel, typeNameLabel, 0, 1);
		panelGridAdd(subPanel, typeLabel, 1, 1);
		panelGridAdd(subPanel, lengthLabel, 2, 1);
		subPanel.setVisible(false);
		subPanel.setVisible(true);
	}
	
	private void sendCreatedTable() {
		ArrayList<TableType> tableTypeSender = new ArrayList<TableType>();
		try {
			for (TableColumnJHolder t : tableColumnJHolderList) {
				if (!t.typeName.getText().equals("")) {
					if (t.length.getText().equals("")) {
						tableTypeSender.add(new TableType(t.typeName.getText(), t.dropDownTypes.getSelectedItem().toString()));
					} else if (Integer.parseInt(t.length.getText()) > 0) {
						tableTypeSender.add(new TableType(t.typeName.getText(), t.dropDownTypes.getSelectedItem().toString(), t.length.getText()));
					}
				}
			}
			tableTypeSender.get(0).primaryKey = true;
		} catch (NumberFormatException e) {
			errorText.setText("Why don't you actually put a number in the length column in this time.");
			errorText.setVisible(true);
		}
		if (connector.createTable(tableNameField.getText(), tableTypeSender.toArray(new TableType[tableTypeSender.size()]))) {
			errorText.setText("Table Created Succesfully!");
			errorText.setVisible(true);
			populateCreateTableSubFrame();
		} else {
			errorText.setText("Alright listen... So about that table...");
			errorText.setVisible(true);
		}
	}
	
	private void subPanelAddNewColumnType() {
		createTableEntriesCount ++;
		JTextField typeName = new JTextField(10);
		JComboBox<String> dropDownTypes = new JComboBox<String>(listOfTypes);
		JTextField length = new JTextField(4);
		panelGridAdd(subPanel, typeName, 0, createTableEntriesCount);
		panelGridAdd(subPanel, dropDownTypes, 1, createTableEntriesCount);
		panelGridAdd(subPanel, length, 2, createTableEntriesCount);
		tableColumnJHolderList.add(new TableColumnJHolder(typeName, dropDownTypes, length));
		subPanel.setVisible(false);
		subPanel.setVisible(true);
		if (createTableEntriesCount <= 4) {
			errorText.setVisible(true);
			errorText.setVisible(false);
		}
	}
	
	private void populateCreateEntrySubFrame() {
		refreshPanel(subPanel);
		createTabSubPanelTitle.setText("Create Entry");
		createSubPanelPosition = 1;
		
		JLabel tableListLabel = new JLabel("Table List");
		JLabel tableColumnsLabel = new JLabel("Columns and types");
		
		dropDownTables = new JComboBox<String>(connector.listTables());
		dropDownTables.addActionListener(dropDownTablesAction);
		dropDownTables.setSelectedIndex(0);
		
		populateCreateEntrySubFrameColumns();
		
		panelGridAdd(subPanel, tableListLabel, 0, 0);
		panelGridAdd(subPanel, tableColumnsLabel, 1, 0);
		panelGridAdd(subPanel, dropDownTables, 0, 1);
		panelGridAdd(subPanel, columnsTypesPanel, 1, 1);
	}
	
	private void populateCreateEntrySubFrameColumns() {
		entryFieldList.clear();
		refreshPanel(columnsTypesPanel);
		System.out.println("");
		int columnsTypesPanelX = 0;
		for (TableType tableColumn : connector.listColumns(connector.listTables()[dropDownTables.getSelectedIndex()])) {
			JLabel columnName = new JLabel(tableColumn.nameTypes);
			columnName.setFont(font.deriveFont(12f));
			JTextField entryValue = new JTextField(8);
			entryFieldList.add(entryValue);
			panelGridAdd(columnsTypesPanel, columnName, columnsTypesPanelX, 0);
			panelGridAdd(columnsTypesPanel, entryValue, columnsTypesPanelX, 1);
			columnsTypesPanelX ++;
			JLabel columnType = new JLabel(tableColumn.types+"("+tableColumn.lengths+")");
			columnType.setFont(font.deriveFont(12f));
			panelGridAdd(columnsTypesPanel, columnType, columnsTypesPanelX, 1);
			columnsTypesPanelX ++;
		}
	}
	
	private void sendCreatedEntry() {
		String[] values = new String[entryFieldList.size()];
		for (int i = 0; i < values.length; i ++) {
			values[i] = entryFieldList.get(i).getText();
		}
		if (connector.createData(connector.listTables()[dropDownTables.getSelectedIndex()], values)) {
			populateCreateEntrySubFrameColumns();
			errorText.setText("Entry Created Succesfully!");
			errorText.setVisible(true);
		} else {
			errorText.setText(connector.errorMsg);
			errorText.setVisible(true);
		}
	}
	
	private void populateDeleteTableFrame() {
		refreshPanel(subPanel);
		
		panelGridAdd(contentPanel, subPanelScroll, 1, 0);
	}
	
	//	-------------------------------------------------------------------------------------------------
	//		Explore Frame
	//	-------------------------------------------------------------------------------------------------
	
	private void populateExploreFrame() {
		refreshPanel(contentPanel);
		exploreButtonTableList.clear();
		titleLabel.setText("Explore");
		
		subPanelScroll = new JScrollPane(subPanel);
		subPanelScroll.setPreferredSize(new Dimension(500, 300));
		
		JPanel tableListPane = new JPanel(new GridBagLayout());
		tableListPane.setBackground(Color.WHITE);
		tableListPane.setVisible(true);
		JScrollPane tableList = new JScrollPane(tableListPane);
		String[] tableListArray = connector.listTables();
		for (int i = 0; i < tableListArray.length; i++) {
			String tableName = tableListArray[i];
			JButton tableEntry = new JButton(tableName);
			tableEntry.addActionListener(new ActionListener() {
				String table = tableName;
				public void actionPerformed(ActionEvent e) {
					System.out.println("\t" + table +" button clicked");
					populateExploreFramePanel(table);
				}
			});
			exploreButtonTableList.add(tableEntry);
			tableEntry.setFont(font.deriveFont(14f));
			panelGridAdd(tableListPane, tableEntry, 0, i);
			if (i == 0)
				populateExploreFramePanel(tableName);
		}
		tableList.setVisible(true);
		
		panelGridAdd(contentPanel, subPanelScroll, 1, 0);
		panelGridAdd(contentPanel, tableList, 0, 0);
	}
	
	private void populateExploreFramePanel(String tableName) {
		refreshPanel(subPanel);
		
		JPanel innerPanel = new JPanel(new GridBagLayout());
		innerPanel.setBackground(Color.LIGHT_GRAY);
		innerPanel.setVisible(true);
		
		JLabel subPanelTitle = new JLabel(tableName);
		subPanelTitle.setForeground(Color.DARK_GRAY);
		subPanelTitle.setFont(font.deriveFont(14f));
		
		JButton dropTableButton = new JButton("X");
		dropTableButton.addActionListener(new ActionListener() {
			
			String table = tableName;
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tDrop table entry for " + table + " button clicked");
				int choice = JOptionPane.showConfirmDialog(subPanel,
						"Are you sure you want to drop " + table + "?", "Make a choice man", JOptionPane.YES_NO_OPTION);
				if (choice == 0) {
					System.out.println("\nDropping table Entry...");
					connector.dropTable(table);
					JOptionPane.showConfirmDialog(subPanel, "ok :c", ":(", JOptionPane.PLAIN_MESSAGE);
					populateExploreFrame();
				}
			}
		});
		
		int i = 0;
		for (TableType t : connector.listColumns(tableName)) {
			JLabel columnTitle = new JLabel(t.nameTypes);
			if (t.primaryKey) {
				columnTitle.setFont(font.deriveFont(13f));
				columnTitle.setForeground(Color.BLACK);
			} else {
				columnTitle.setFont(font.deriveFont(12f));
				columnTitle.setForeground(Color.DARK_GRAY);
			}
			panelGridAdd(innerPanel, columnTitle, i, 0);
			i ++;
		}
		
		int y = 1;
		for (TableData t : connector.selectTable(tableName)) {
			int x = 0;
			JButton dropEntryButton = new JButton("X");
			dropEntryButton.addActionListener(new ActionListener() {
				
				TableData data = t;
				public void actionPerformed(ActionEvent e) {
					System.out.println("\tDrop table entry for " + data.tableName + " button clicked");
					int choice = JOptionPane.showConfirmDialog(subPanel,
							"Are you sure you want to drop this entry?", "Make a choice man", JOptionPane.YES_NO_OPTION);
					if (choice == 0) {
						System.out.println("\nDropping table Entry...");
						connector.deleteTableEntry(tableName, data);
						populateExploreFrame();
					}
				}
			});
			for (String s : t.data) {
				JLabel data = new JLabel(s);
				data.setFont(font.deriveFont(12f));
				data.setForeground(Color.DARK_GRAY);
				panelGridAdd(innerPanel, data, x, y);
				x ++;
			}
			if (currentUser.permLvl >= 2)
				panelGridAdd(innerPanel, dropEntryButton, x, y);
			y ++;
		}
		panelGridAdd(subPanel, subPanelTitle, 0, 0);
		if (currentUser.permLvl == 3)
			panelGridAdd(subPanel, dropTableButton, 1, 0);
		panelGridAdd(subPanel, innerPanel, 0, 1);
	}
	
	//	-------------------------------------------------------------------------------------------------
	//		User Frames
	//	-------------------------------------------------------------------------------------------------
	
	private void populateAdminFrame() {
		refreshPanel(contentPanel);
		System.out.println("Admin Panel Initialized");
		
		panelGridAdd(buttonPanel, homeButton, 0, 0);
		panelGridAdd(buttonPanel, createButton, 1, 0);
		panelGridAdd(buttonPanel, exploreButton, 2, 0);
		panelGridAdd(buttonPanel, terminalButton, 3, 0);
		panelGridAdd(buttonPanel, logoutButton, 4, 0);
	}
	
	private void populateStaffFrame() {
		refreshPanel(contentPanel);
		System.out.println("Staff Panel Initialized");
		
		panelGridAdd(buttonPanel, homeButton, 0, 0);
		panelGridAdd(buttonPanel, createButton, 1, 0);
		panelGridAdd(buttonPanel, exploreButton, 2, 0);
		panelGridAdd(buttonPanel, logoutButton, 4, 0);
	}
	
	private void populateStudentFrame() {
		refreshPanel(contentPanel);
		System.out.println("Student Panel Initialized");
		
		panelGridAdd(buttonPanel, homeButton, 0, 0);
		panelGridAdd(buttonPanel, exploreButton, 2, 0);
		panelGridAdd(buttonPanel, logoutButton, 4, 0);
	}
	
	//	-------------------------------------------------------------------------------------------------
	//		Initializer Methods
	//	-------------------------------------------------------------------------------------------------
	
	private void generalInits() {
		currentUser = new User("","",0);
		tableLabelsList = new ArrayList<JLabel>();
		entryFieldList = new ArrayList<JTextField>();
		exploreButtonTableList = new ArrayList<JButton>();
		tableColumnJHolderList = new ArrayList<TableColumnJHolder>();
		
		dropDownTablesAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				populateCreateEntrySubFrameColumns();
			}
		};
		
		dropDownTables = new JComboBox<String>(connector.listTables());
		dropDownTables.addActionListener(dropDownTablesAction);
		
		grid = new GridBagConstraints();
		grid.insets = new Insets(10, 10, 10, 10);
		
		buttonColor = new Color(240,240,240);
		font = new Font("Geneva", Font.BOLD, 12);
		
		listOfTypes = connector.listDataTypes();
		
		userInput = new JTextField(15);
		passInput = new JPasswordField(15);
		tableNameField = new JTextField(15);
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
		
		exploreArea = new JTextArea(16, 58);
		exploreArea.setBackground(Color.WHITE);
		exploreArea.setForeground(Color.DARK_GRAY);
		exploreArea.setEditable(false);
		
		scrollTerminalPanel = new JScrollPane(terminalArea);
	} // GeneralInit()
	
	private void mainInit() {
		mainFrame.add(titlePanel, BorderLayout.NORTH);
		mainFrame.add(contentPanel, BorderLayout.CENTER);
		mainFrame.add(errorText, BorderLayout.SOUTH);
	}
	
	private void labelInit() {
		titleLabel = new JLabel("LOGIN");
		terminalArea.setFont(font.deriveFont(12f));
		
		titleLabel.setFont(font.deriveFont(24f));
		titleLabel.setToolTipText("by: Zachary Vanscoit");
		titleLabel.setForeground(Color.WHITE);
		
		timeLabel = new JLabel();
		timeLabel.setForeground(Color.DARK_GRAY);
		timeLabel.setFont(font.deriveFont(14f));
		
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
		
		createTabSubPanelTitle = new JLabel("");
		createTabSubPanelTitle.setForeground(Color.DARK_GRAY);
		createTabSubPanelTitle.setFont(font.deriveFont(14f));
	}
	
	private void panelInit() {
		titlePanel = new JPanel(new GridBagLayout());
		titlePanel.setBackground(Color.GRAY);
		titlePanel.setVisible(true);
		
		contentPanel = new JPanel(new GridBagLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setVisible(true);
		
		subPanel = new JPanel(new GridBagLayout());
		subPanel.setBackground(Color.WHITE);
		subPanel.setVisible(true);
		
		columnsTypesPanel = new JPanel(new GridBagLayout());
		columnsTypesPanel.setBackground(Color.WHITE);
		columnsTypesPanel.setVisible(true);
		
		subPanelScroll = new JScrollPane(subPanel);
		subPanelScroll.setPreferredSize(new Dimension(500, 300));
		
		buttonPanel = new JPanel(new GridBagLayout());
		buttonPanel.setBackground(Color.GRAY);
		buttonPanel.setVisible(true);
		
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setVisible(true);
		
		panelGridAdd(titlePanel, titleLabel, 0, 0);
		panelGridAdd(titlePanel, buttonPanel, 0, 1);
	}
	
	public void updateTimeLabel() {
		calendar = Calendar.getInstance();
		timeLabel.setText(calendar.getTime().toString());
		contentPanel.repaint();
	}
	
	private void buttonInit() {
		loginButton = new JButton();
		standardButtonInits(loginButton, "Log In", "Lets you login", 14f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLoggin button clicked");
				toggleLogging();
			}
		});
		logoutButton = new JButton();
		standardButtonInits(logoutButton, "Log out", "Lets you logout", 14f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tLogout button clicked");
				toggleLogging();
			}
		});
		homeButton = new JButton();
		standardButtonInits(homeButton, "Home", "Displays the Home panel", 14f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tHome button clicked");
				populateHomeFrame();
			}
		});
		createButton = new JButton();
		standardButtonInits(createButton, "Create", "Displays the Creation panel", 14f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tCreate button clicked");
				populateCreateFrame();
			}
		});
		createTableButton = new JButton();
		standardButtonInits(createTableButton, "Create Table", "Displays the Creation Table panel", 12f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tCreate Table button clicked");
				populateCreateTableSubFrame();
			}
		});
		createEntryButton = new JButton();
		standardButtonInits(createEntryButton, "Create Entry", "Displays the Creation Entry panel", 12f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tCreate Entry button clicked");
				populateCreateEntrySubFrame();
			}
		});
		deleteTableButton = new JButton();
		standardButtonInits(deleteTableButton, "Delete Table", "Displays the Delete Table panel", 12f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tDelete Table button clicked");
				populateDeleteTableFrame();
			}
		});
		sendUpdateButton = new JButton();
		standardButtonInits(sendUpdateButton, "Send Update", "Sends an update to the database", 12f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tSend Update button clicked");
				if (createSubPanelPosition == 0)
					sendCreatedTable();
				if (createSubPanelPosition == 1)
					sendCreatedEntry();
			}
		});
		exploreButton = new JButton();
		standardButtonInits(exploreButton, "Explore", "Displays the Explore panel", 14f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tExplore button clicked");
				populateExploreFrame();
			}
		});
		terminalButton = new JButton();
		standardButtonInits(terminalButton, "Terminal", "Displays the Terminal panel", 14f, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("\tExplore button clicked");
				populateTerminalFrame();
			}
		});
	} // ButtonInit()
	
	//	-------------------------------------------------------------------------------------------------
	//		Other Methods
	//	-------------------------------------------------------------------------------------------------
	
	private void refreshPanel(JPanel panel) {
		panel.removeAll();
		panel.setVisible(false);
		panel.setVisible(true);
	}
	
	private void standardButtonInits(JButton button, String buttonText, String toolTip, float fontSize, ActionListener action) {
		button.setText(buttonText);
		button.setToolTipText(toolTip);
		button.setFont(font.deriveFont(fontSize));
		button.setFocusPainted(false);
		button.setBackground(buttonColor);
		button.addActionListener(action);
	}
	
	private void panelGridAdd(JPanel panel, Component comp, int gx, int gy) {
		grid.gridx = gx;
		grid.gridy = gy;
		panel.add(comp, grid);
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
			populateStaffFrame();
		} else
		if (currentUser.permLvl == 1) {
			refreshPanel(buttonPanel);
			refreshPanel(contentPanel);
			populateStudentFrame();
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
