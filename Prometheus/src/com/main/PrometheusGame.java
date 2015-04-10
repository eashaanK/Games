package com.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import com.AI.DesktopManager;
import com.AI.EKVoice;
import com.enums.Command;

public class PrometheusGame extends JFrame {

	private final int WIDTH = 1000, HEIGHT = 1000;
	private final String TITLE = "Prometheus";
	private final JPanel mainPanel;
	private JTextArea ta_conversation = new JTextArea();
	private JScrollPane sp_console = new JScrollPane();
	private JTextField browseWebTextField = new JTextField();
	private JTextField openFileTextField = new JTextField();
	private Color textColor = Color.white;
	private Color backgroundColor = Color.DARK_GRAY;
	private EKVoice voice = new EKVoice(225, 100);
	private DesktopManager desktopManager = new DesktopManager();
	private final int LEFT_MARGIN = 10, RIGHT_MARGIN = 10 * 2;
	
	/*private final String BROWSE_WEB = "/browse_web"; 
	private final String OPEN_FILE = "/open_file"; 
	private final String PRINT_FILE = "/print_file"; 
	private final String EDIT_FILE = "/edit_file"; 
	private final String WRITE_EMAIL = "/email"; */
	
	private final String DELIMETER = "#"; 

	
	public static void main(String args[]) {
		new PrometheusGame().start();
	}
	
	public PrometheusGame(){
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setLayout(new BorderLayout());
		this.setName(TITLE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font myFont = new Font("Times",  Font.PLAIN, 40);
        this.setFont(myFont);
		
		mainPanel = new JPanel();
		mainPanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPanel.setLayout(null);
		this.add(mainPanel);
		mainPanel.setBackground(backgroundColor);
		
		JLabel title = new JLabel("Prometheus");
		title.setBounds(WIDTH/2 - 200/2, 10, 200, 80);
		title.setForeground(textColor);
		title.setFont(this.getFont());
		mainPanel.add(title);
		
		configure();
		
	}
	
	private void configure(){
		ta_conversation.setMargin(new Insets(5, 5, 5, 5));

		sp_console
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp_console
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_console.setViewportView(ta_conversation);
		sp_console.setBounds(LEFT_MARGIN, this.getHeight()/2 + 100, this.getWidth() - RIGHT_MARGIN,
				this.getHeight() - 170);

		ta_conversation.setBackground(backgroundColor);
		ta_conversation.setForeground(textColor);
		sp_console.setBackground(Color.white);

		mainPanel.add(sp_console);
		
		ta_conversation.setEditable(false);
		
		JLabel browseWebLabel = new JLabel("Browse web address:");
		browseWebLabel.setBounds(LEFT_MARGIN, 100, this.getWidth()-RIGHT_MARGIN, 35);
		browseWebLabel.setForeground(textColor);
		mainPanel.add(browseWebLabel);
		browseWebTextField.setBounds(LEFT_MARGIN, 125, this.getWidth() - RIGHT_MARGIN, 35);
		browseWebTextField.setBackground(backgroundColor);
		browseWebTextField.setForeground(textColor);
		mainPanel.add(browseWebTextField);
		
		JLabel openFileLabel = new JLabel("Open Application: ");
		openFileLabel.setBounds(LEFT_MARGIN, 160, this.getWidth()-RIGHT_MARGIN, 35);
		openFileLabel.setForeground(textColor);
		mainPanel.add(openFileLabel);
		openFileTextField.setBounds(LEFT_MARGIN, 185, this.getWidth() - RIGHT_MARGIN, 35);
		openFileTextField.setBackground(backgroundColor);
		openFileTextField.setForeground(textColor);
		mainPanel.add(openFileTextField);
				
		browseWebTextField.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						decode(Command.BROWSE_WEB.getID() + DELIMETER + browseWebTextField.getText());
						browseWebTextField.setText("");
					}
				});
		
		openFileTextField.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						decode(Command.OPEN_FILE.getID() + DELIMETER + openFileTextField.getText());
						openFileTextField.setText("");
					}
				});
	}
	
	public void start(){
		this.setVisible(true);

		addMessageToConsole("Hello!");

	}
	
	private void addMessageToConsole(String message){
		ta_conversation.append(getTimeStamp() + ": " + message + "\n");
		voice.speak(message);

	}
	
	private String getTimeStamp(){
		Date date = new Date();
		return new Timestamp(date.getTime()).toString();
	}

	private void decode(String message){
		String[] parts = message.split(this.DELIMETER);
		
		Command  command = Command.lookupCommand(parts[0]);
		
				
		switch(command){
		case BROWSE_WEB:
			handleBrowseWeb(parts[1]);
			break;
			
		case OPEN_FILE:
			handleOpenFile(parts[1]);
			break;
		}
	
	}
	
	private void handleOpenFile(String file) {
		byte worked = desktopManager.openApplication(file);
		if(worked == 0)
			this.addMessageToConsole("Now Opening Application " + file);
		else{
			this.addMessageToConsole("Incorrect Application name " + file + ". Check your spelling and make sure that the App actually exists.");
		}
	}

	private void handleBrowseWeb(String website){
		if(website.length() <= 8 || !website.substring(0, 8).equals("https://") ){
			website = "https://" + website;

		}
	
		byte worked = desktopManager.browse(website);
		if(worked == 0)
			this.addMessageToConsole("Now Going to Website: " + website);
		else{
			this.addMessageToConsole("Incorrect URL syntax. Did you forget (http://), (www.), (.com), etc.?: " + website);
		}
	}
	
}
