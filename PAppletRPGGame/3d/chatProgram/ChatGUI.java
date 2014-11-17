package chatProgram;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

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


public class ChatGUI {

	private static ChatClient chatClient;
	public static String userName = "Anonymous";
	
	//Gui
	public static JFrame mainWindow = new JFrame();
	
	//Chat Window
	private static JButton b_connect = new JButton("Connect");
	private static JButton b_disconnect = new JButton("Disconnect");
	
	private static JLabel l_message = new JLabel("Message: ");
	public static JTextField tf_message = new JTextField(25);
	
	private static JLabel l_conversation = new JLabel();
	public static JTextArea ta_conversation = new JTextArea();
	private static JScrollPane sp_conversation = new JScrollPane();
	
	private static JLabel l_online = new JLabel();
	public static JList jl_online = new JList();
	private static JScrollPane sp_online = new JScrollPane();
	
	private static JLabel l_loggedInAs = new JLabel();
	private static JLabel l_loggedInAsBox = new JLabel();

	//Log in Window
	public static JFrame logInWindow = new JFrame();
	public static JTextField tf_userNameBox = new JTextField();
	private static JLabel l_enterUserName = new JLabel("Enter username: ");
	private static JPanel p_login = new JPanel();
	
	public static void main(String[] args){
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//logInWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainWindow();
		init();
	}
	
	public static void connect(){
		try{
			final int PORT = 8888;
			final String HOST = "localhost";
			Socket SOCK = new Socket(HOST, PORT);
			System.out.println("You connected to: " + HOST);
			
			chatClient = new ChatClient(SOCK);
			
			//send name to add to online list of users on the right
			PrintWriter out = new PrintWriter(SOCK.getOutputStream());
			out.println(userName);
			out.flush();
			
			Thread t = new Thread(chatClient);
			t.start();
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}
	
	private static void mainWindow(){
		mainWindow.setTitle(userName + "'s conversation");
		mainWindow.setSize(450, 500); //////////WHAAAAAAAAAAAAAAAA
		mainWindow.setLocation(220, 180);
		mainWindow.setResizable(false);
		configureMainWindow();
		mainWindowAction();
		mainWindow.setVisible(true);
	}
	
	private static void buildLogInWindow(){
		logInWindow.setTitle("What's your name?");
		logInWindow.setSize(400, 100);
		logInWindow.setLocation(25, 200);
		logInWindow.setResizable(false);
		tf_userNameBox.setPreferredSize(new Dimension(150, 20));
		
		p_login = new JPanel();
		p_login.add(l_enterUserName);
		p_login.add(tf_userNameBox);
		logInWindow.add(p_login);
		
		login_Action();
		logInWindow.setVisible(true);
	}
	
	private static void configureMainWindow(){
		mainWindow.setBackground(new Color(255, 255, 255));
		mainWindow.setSize(500, 320);
		mainWindow.getContentPane().setLayout(null);
		
		b_disconnect.setBackground(new Color(0, 0, 255));
		b_disconnect.setForeground(new Color(0, 0, 0));
		mainWindow.getContentPane().add(b_disconnect);
		b_disconnect.setBounds(10, 40, 110, 25);
		
		b_connect.setBackground(new Color(0, 0, 255));
		b_connect.setForeground(new Color(0, 0, 0));
		b_connect.setToolTipText("");
		mainWindow.getContentPane().add(b_connect);
		b_connect.setBounds(130, 40, 110, 25);
		
		l_message.setForeground(new Color(0, 0, 0));
		mainWindow.getContentPane().add(l_message);
		l_message.setBounds(10, 10, 60, 20);
		
		tf_message.setForeground(new Color(0, 0, 255));
		tf_message.requestFocus();
		mainWindow.getContentPane().add(tf_message);
		tf_message.setBounds(70, 4, 260, 30);
		
		l_conversation.setHorizontalAlignment(SwingConstants.CENTER);
		l_conversation.setText("Conversation");
		mainWindow.getContentPane().add(l_conversation);
		l_conversation.setBounds(100, 70, 140, 16);
		
		ta_conversation.setColumns(20);
		ta_conversation.setFont(new Font("Tahoma", 0, 12));
		ta_conversation.setForeground(new Color(0, 0, 255));
		ta_conversation.setLineWrap(true);
		ta_conversation.setRows(5);
		ta_conversation.setEditable(false);

		sp_conversation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_conversation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_conversation.setViewportView(ta_conversation);
		mainWindow.getContentPane().add(sp_conversation);
		sp_conversation.setBounds(10, 90, 330, 180);
		
		l_online.setHorizontalAlignment(SwingConstants.CENTER);
		l_online.setText("Currently Online");
		l_online.setToolTipText("");
		mainWindow.getContentPane().add(l_online);
		l_online.setBounds(350, 70, 130, 16);
		
		//String[] testNames = {"Bob", "Sue", "Jenny", "Anna"};
		jl_online.setForeground(new Color(0, 0, 255));
		//jl_online.setListData(testNames);
		
		sp_online.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_online.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_online.setViewportView(jl_online);
		mainWindow.getContentPane().add(sp_online);
		sp_online.setBounds(350, 90, 130, 180);
		
		l_loggedInAs.setFont(new Font("Tahoma", 0, 12));
		l_loggedInAs.setText("Currently Logged In As");
		mainWindow.getContentPane().add(l_loggedInAs);
		l_loggedInAs.setBounds(348, 0, 140, 15);

		l_loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
		l_loggedInAsBox.setFont(new Font("Tahoma", 0, 12));
		l_loggedInAsBox.setForeground(new Color(255, 0, 0));
		l_loggedInAsBox.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		l_loggedInAsBox.setText("Conversation");
		mainWindow.getContentPane().add(l_loggedInAsBox);
		l_loggedInAsBox.setBounds(340, 17, 150, 20);

	
	}
	
	private static void init(){
		b_disconnect.setEnabled(false);
		b_connect.setEnabled(true);
		tf_message.setEnabled(false);
	}
	
	private static void login_Action(){
		tf_userNameBox.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						tf_UserNameBox();
					}
				});
	}
	
	private static void tf_UserNameBox(){
		if(!tf_userNameBox.getText().equals(""))
		{
			userName = tf_userNameBox.getText().trim();
			l_loggedInAsBox.setText(userName);
			ChatServer.users.add(userName);
			mainWindow.setTitle(userName + "'s conversation");
			logInWindow.setVisible(true);
			b_disconnect.setEnabled(true);
			b_connect.setEnabled(false);
			tf_message.setEnabled(true);
			logInWindow.setVisible(false);
			logInWindow.setEnabled(false);
			logInWindow.dispose();
			connect();
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
	}
	
	private static void action_TF_Send(){
		if(!tf_message.getText().equals(""))
		{
			chatClient.SEND(tf_message.getText());
			tf_message.requestFocus();
		}
	}
	
	private static void action_B_Disconnect(){
		try{
			chatClient.DISCONNECT();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

