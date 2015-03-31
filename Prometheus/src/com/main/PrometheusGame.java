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

public class PrometheusGame extends JFrame {

	private final int WIDTH = 700, HEIGHT = 700;
	private final String TITLE = "Prometheus";
	private final JPanel mainPanel;
	private JTextArea ta_conversation = new JTextArea();
	private JScrollPane sp_console = new JScrollPane();
	private JTextField textField = new JTextField();
	private Color textColor = Color.white;
	private Color backgroundColor = Color.DARK_GRAY;
	private EKVoice voice = new EKVoice();
	private DesktopManager desktopManager = new DesktopManager();
	private final String BROWSE_WEB = "/browse_web"; 
	private final String OPEN_FILE = "/open_file"; 
	private final String PRINT_FILE = "/print_file"; 
	private final String EDIT_FILE = "/edit_file"; 
	private final String WRITE_EMAIL = "/email"; 
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
		sp_console.setBounds(10, 150, this.getWidth() - 200,
				this.getHeight() - 170);

		ta_conversation.setBackground(backgroundColor);
		ta_conversation.setForeground(textColor);
		sp_console.setBackground(Color.white);

		mainPanel.add(sp_console);
		
		ta_conversation.setEditable(false);
		
		textField.setBounds(10, 100, this.getWidth() - 200, 35);
		textField.setBackground(backgroundColor);
		textField.setForeground(textColor);
		mainPanel.add(textField);
				
		textField.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						decode(textField.getText());
						textField.setText("");
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
		if(parts[0].equals(BROWSE_WEB)){
			desktopManager.browse(parts[1]);
			this.addMessageToConsole("Now Going to Website: " + parts[1]);
		}
	}
	
}
