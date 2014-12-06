package ek_Misc;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import ek_Threads.StoppableThread;

public class EKConsole extends StoppableThread implements Runnable{
	
	private static JFrame mainWindow = new JFrame();

	
	private JTextArea ta_console = new JTextArea();
	private JScrollPane sp_console = new JScrollPane();
	
	private Color textColor, backgroundColor;
	
	public EKConsole(String title, Color tc, Color bkg){
		this.mainWindow.setTitle(title);
		this.println("Console started");
		this.textColor = tc;
		this.backgroundColor = bkg;
	}
	
	public void run(){
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow();
	}
	
	private void mainWindow(){
		mainWindow.setSize(600, 500);
		mainWindow.setResizable(true);
		configureMainWindow();
		mainWindow.setVisible(true);
		
	}
	
	private void configureMainWindow(){
		mainWindow.setLayout(null);

		ta_console.setColumns(20);
		ta_console.setFont(new Font("Tahoma", 0, 12));
		ta_console.setBackground(backgroundColor);
		ta_console.setForeground(this.textColor);
		ta_console.setLineWrap(true);
		ta_console.setRows(5);
		ta_console.setEditable(false);

		sp_console
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_console
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_console.setViewportView(ta_console);
		mainWindow.add(sp_console);
		sp_console.setBounds(0, 0, mainWindow.getWidth(), mainWindow.getHeight());
		sp_console.setAutoscrolls(true);

	}
	
	public void printTimeStamp(){
		ta_console.append("(" + Helper.getTimeStamp() + ")");
	}
	
	public void printlnTimeStamp(){
		ta_console.append("(" + Helper.getTimeStamp() + ")" + "\n");
	}
	
	public void print(){
		ta_console.append("");
	}
	
	public void println(){
		ta_console.append("\n");
	}
	
	public void print(String message){
		
		ta_console.append("(" + Helper.getTimeStamp() + ")" + message);
	}
	
	public void println(String message){
		ta_console.append("(" + Helper.getTimeStamp() + ")" + message + "\n");
	}
}
