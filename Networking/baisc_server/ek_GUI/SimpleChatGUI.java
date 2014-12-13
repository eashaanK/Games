package ek_GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class SimpleChatGUI{
	
private static String userName = "Anonymous";
	
	//Gui
	private static JFrame mainWindow = new JFrame();
	
	//Chat Window
	private static JButton b_connect = new JButton("Connect");
	private static JButton b_disconnect = new JButton("Disconnect");
	private static JButton b_whisper = new JButton("Whisper");

	
	private static JLabel l_message = new JLabel("Message: ");
	private static JTextField tf_message = new JTextField(25);
	private static JTextField tf_whisper = new JTextField(25);
	
	private static JLabel l_conversation = new JLabel();
	private static JTextArea ta_conversation = new JTextArea();
	private static JScrollPane sp_conversation = new JScrollPane();
	
	private static JLabel l_online = new JLabel();
	private static JList jl_online = new JList();
	private static JScrollPane sp_online = new JScrollPane();
	
	private static JLabel l_loggedInAs = new JLabel();
	private static JLabel l_loggedInAsBox = new JLabel();

	//Log in Window
	private static JFrame logInWindow;
	private static JTextField tf_userNameBox;
	private static JLabel l_enterUserName;
	
	private static JTextField tf_port;
	private static JTextField tf_host;
	private static JLabel l_enterHost;
	private static JLabel l_enterPort;
	
	private static JPanel p_login = new JPanel();
	
	public static void main(String[] args){
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//logInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainWindow();
		init();
		
		String[] list = new String[1000];
		for(int i = 0; i < 1000; i ++){
			SimpleChatGUI.addToConversation("LOL");
			list[i] = "lol";
		}
		
		SimpleChatGUI.updateOnlineList(list);
		

	}
	
	public static void connect() throws UnknownHostException, IOException{

		/*final int PORT = Integer.parseInt(tf_port.getText());
		final String HOST = tf_host.getText();
		Socket SOCK = new Socket(HOST, PORT);
		
		chatClient = new Client(SOCK, userName);
		
		Thread t = new Thread(chatClient);
		t.start();	
		
			userName = tf_userNameBox.getText().trim();
				l_loggedInAsBox.setText(userName);
				mainWindow.setTitle(userName + "'s conversation");
				logInWindow.setVisible(true);
				b_disconnect.setEnabled(true);
				b_connect.setEnabled(false);
				tf_message.setEnabled(true);
				tf_whisper.setEnabled(true);
				logInWindow.setVisible(false);
				logInWindow.setEnabled(false);
				logInWindow.dispose();
		*/
		
	}
	
	private static void mainWindow(){
		mainWindow.setSize(900, 520);
		mainWindow.setResizable(false);
		configureMainWindow();
		mainWindowAction();
		mainWindow.setVisible(true);
		
	}
	
	private static void buildLogInWindow(){
		l_enterUserName = new JLabel("Username: ");
		l_enterPort = new JLabel("Port: ");
		l_enterHost = new JLabel("Host: ");
		tf_userNameBox = new JTextField();
		tf_port = new JTextField();
		tf_host = new JTextField();
		logInWindow = new JFrame();
		
		l_enterUserName.setBounds(10, 10, 100, 25);
		l_enterPort.setBounds(10, 30, 100, 25);
		l_enterHost.setBounds(10, 50, 100, 25);
		
		tf_userNameBox.setBounds(100, 10, 200, 20);
		tf_port.setBounds(100, 30, 200, 20);
		tf_host.setBounds(100, 50, 200, 20);



		
		logInWindow.setTitle("What's your name?");
		logInWindow.setSize(400, 100);
		logInWindow.setLocation(25, 200);
		logInWindow.setResizable(false);
		tf_userNameBox.setPreferredSize(new Dimension(150, 20));
		
		p_login = new JPanel();
		p_login.setLayout(null);
		p_login.add(l_enterUserName);
		p_login.add(tf_userNameBox);
		p_login.add(l_enterPort);
		p_login.add(l_enterHost);
		p_login.add(tf_port);
		p_login.add(tf_host);

		logInWindow.add(p_login);
		
		login_Action();
		logInWindow.setVisible(true);
	}
	
	private static void configureMainWindow(){
		mainWindow.setLayout(null);

		// left side
		ta_conversation.setColumns(20);
		ta_conversation.setFont(new Font("Tahoma", 0, 12));
		ta_conversation.setForeground(new Color(0, 0, 255));
		ta_conversation.setLineWrap(true);
		ta_conversation.setRows(5);
		ta_conversation.setEditable(false);

		sp_conversation
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_conversation
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_conversation.setViewportView(ta_conversation);
		mainWindow.add(sp_conversation);
		sp_conversation.setBounds(20, 150, 570, 250);

		l_conversation.setHorizontalAlignment(SwingConstants.CENTER);
		l_conversation.setText("Conversation");
		mainWindow.add(l_conversation);
		l_conversation.setBounds(223, 134, 140, 16);

		mainWindow.add(l_message);
		l_message.setBounds(20, 410, 62, 20);

		tf_message.setForeground(new Color(0, 0, 255));
		tf_message.requestFocus();
		mainWindow.add(tf_message);
		tf_message.setBounds(82, 410, 512, 30);
		
		b_whisper.setForeground(new Color(0, 0, 0));
		mainWindow.add(b_whisper);
		b_whisper.setBounds(232, 450, 150, 30);
		
		tf_whisper.setForeground(new Color(0, 0, 0));
		mainWindow.add(tf_whisper);
		tf_whisper.setBounds(82, 450, 150, 30);

		JLabel l_user = new JLabel("Username");
		mainWindow.add(l_user);
		l_user.setBounds(20, 450, 62, 20);
		
		// right
		l_online.setHorizontalAlignment(SwingConstants.CENTER);
		l_online.setText("Currently Online");
		l_online.setToolTipText("");
		mainWindow.add(l_online);
		l_online.setBounds(694, 134, 103, 16);

		jl_online.setForeground(new Color(0, 0, 255));
		sp_online
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_online
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_online.setViewportView(jl_online);
		mainWindow.add(sp_online);
		sp_online.setBounds(610, 150 , 270 , 250);
		
		// top
		l_loggedInAs.setFont(new Font("Tahoma", 0, 15));
		l_loggedInAs.setText("Currently Logged In As");
		mainWindow.add(l_loggedInAs);
		l_loggedInAs.setBounds(375, 0, 150, 20);

		l_loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
		l_loggedInAsBox.setFont(new Font("Tahoma", 0, 25));
		l_loggedInAsBox.setForeground(new Color(0, 0, 0));
		l_loggedInAsBox.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0)));
		l_loggedInAsBox.setText(userName);
		l_loggedInAsBox.setPreferredSize(new Dimension(100, 100));
		mainWindow.add(l_loggedInAsBox);
		l_loggedInAsBox.setBounds(350, 40, 200, 40);

		
		b_disconnect.setForeground(new Color(0, 0, 0));
		mainWindow.add(b_disconnect); 
		b_disconnect.setBounds(475, 100, 150, 30);
		
		//b_connect.setBackground(new Color(0, 0, 255));
		b_connect.setForeground(new Color(0, 0, 0));
		b_connect.setToolTipText("");
		mainWindow.add(b_connect);
		b_connect.setBounds(275, 100, 150, 30);
		 
		tf_message.requestFocus();


	
	
	}
	
	private static void init(){
		b_disconnect.setEnabled(false);
		b_connect.setEnabled(true);
		tf_message.setEnabled(false);
		tf_whisper.setEnabled(false);
	}
	
	private static void login_Action(){
		tf_userNameBox.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						if(check())
							connectRequestedOnEnterKey();
					}
				});
		tf_port.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						if(check())
							connectRequestedOnEnterKey();
					}
				});
		
		tf_host.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						if(check())
							connectRequestedOnEnterKey();
					}
				});
	}
	
	private static void connectRequestedOnEnterKey(){
		if(!tf_userNameBox.getText().equals(""))
		{
			
			try {
				connect();

			} catch (UnknownHostException e) {
				JOptionPane.showMessageDialog(null, "Server not found\n" + e.toString());
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Server not responding\n" + e.toString());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Please enter a name!");
	}
	
	private static void mainWindowAction(){
		tf_message.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						action_TF_Send();
					}
				});
		b_disconnect.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						action_B_Disconnect();
					}
				});
		b_connect.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						buildLogInWindow();
					}
				});
	
		b_whisper.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					action_B_Whisper();
				}
			});
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////FIX THIS////////////////////////////////////////////////////////////////////////////////////////
	private static void action_B_Whisper(){
		if(!tf_message.getText().equals(""))
		{
			/*chatClient.whisper(tf_whisper.getText(), tf_message.getText());
			tf_message.setText("");
			tf_whisper.setText("");
			tf_message.requestFocus();*/
		}
	}
	
	private static void action_TF_Send(){
		if(!tf_message.getText().equals(""))
		{
			/*chatClient.send(tf_message.getText());
			tf_message.setText("");
			tf_message.requestFocus();*/
		}
	}
	
	private static void action_B_Disconnect(){
		try{
		/*	ta_conversation.setText("");
			
			updateOnlineList(new String[0]);

			b_disconnect.setEnabled(false);
			b_connect.setEnabled(true);
			tf_message.setEnabled(false);
			tf_userNameBox.setEnabled(true);
			System.out.println("GUI is attempting to disconect");
			if(chatClient!= null)
				chatClient.disconnect();*/

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void updateOnlineList(String[] list){
		jl_online.setListData(list);
		sp_online.setViewportView(jl_online);

	}
	
	public static void addToConversation(String message){
		SimpleChatGUI.ta_conversation.append(message + "\n");
		ta_conversation.setCaretPosition(ta_conversation.getDocument().getLength());
	}
	
	public static String getUserName(){
		return userName;
	}
	
	public  static boolean check(){
		String name = tf_userNameBox.getText();
		String port = tf_port.getText();
		String host = tf_host.getText();
		String securityName = name.trim();
		String securityPort = port.trim();
		int actualPort = -1;
		
		if(securityName.length() >= 1)
		{
			l_enterUserName.setForeground(Color.black);
		}
		if(securityPort.length() >= 1)
		{
			l_enterPort.setForeground(Color.black);
		}
		if(host.length() >= 1)
		{
			l_enterHost.setForeground(Color.black);
		}
		
		if(securityName == null || securityName.equals("")){
			l_enterUserName.setForeground(Color.red);
			return false;
		}
		if(port == null || port.equals("")){
			l_enterPort.setForeground(Color.red);
			return false;
		}
		if(host == null || host.equals("")){
			l_enterHost.setForeground(Color.red);
			return false;
		}
		
		
		try{
			actualPort = Integer.parseInt(securityPort);
			
		}
		catch(Exception e){
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please enter a valid number");
			return false;
		}	
		
		return true;
	}

}
