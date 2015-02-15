package com.lwjgl2D.mulitplayer;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GameServer extends Thread{

	private final int port, maxClients;
	private ServerSocket server;
	private final String serverName;
	public ArrayList<Socket> sockets = new ArrayList<Socket>();
	private EKConsole console;
	private boolean isActive = false;
	
	/**
	 * Starts a new Server on the current computer
	 * @param port this server's port
	 * @param maxClients if -, then infinite
	 */
	public GameServer(int port, int maxClients){
		this.port = port;
		this.maxClients = maxClients;
		this.serverName = "Basic Server";
	}
	
	/**
	 * Starts a new Server on the current computer.
	 * @param port this server's port
	 * @param maxClients if -, then infinite
	 * @param name Server's name
	 */
	public GameServer(int port, int maxClients, String name){
		this.port = port;
		this.maxClients = maxClients;
		this.serverName = name;
	}
	
	public void run(){
		//check if Server can even run
		isActive = true;
		if(!this.isActive())
		{
			String tempError = "Server: " + this.serverName + " quit right before it could do anything. Check class that starts Server Thread.";
			System.err.println(tempError);
			JOptionPane.showMessageDialog(null, tempError, this.getName(), JOptionPane.ERROR_MESSAGE);
			this.interrupt();
		}
		
		try {
			server = new ServerSocket(this.port);
			
			console = new EKConsole(new Dimension(800, 400), "Server Console", Color.black, Color.white);
			Thread t = new Thread(console);
			t.start();
			System.out.println("Server EKConsole degugger launched");
			
			if(this.maxClients > 0)
				startLimitedServer();
			else
				startInfiniteServer();
			
		} catch (IOException e) {
			String tempError = "Server: " + this.serverName + " could not start a server. Check BasicServer class.";
			System.err.println(tempError);
			console.println(tempError);
			JOptionPane.showMessageDialog(null, tempError, this.getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	private void startLimitedServer(){
		for(int i = 0; i < this.maxClients && this.isActive(); i++){
			Socket socket;
			try {
				console.println("Wating for client to join: " + this.sockets.size() + "/" + this.maxClients);
				socket = server.accept();
				this.sockets.add(socket);
				//System.out.println("Client joined: " + socket.toString());
				console.println("Client joined: " + socket.toString());
				GameManager bSM = new GameManager(socket, this.console, this.sockets.size() -1);
				bSM.start();
				console.println();
			} catch (IOException e) {
				String tempError = "Server: " + this.serverName + " could not add clients(Limited Client Method). Check BasicServer class or the Client.";
				System.err.println(tempError);
				console.println(tempError);
				JOptionPane.showMessageDialog(null, tempError, this.getName(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	
	private void startInfiniteServer(){
		while(this.isActive()){
			Socket socket;
			try {
				console.println("Wating for client to join: " + this.sockets.size() + "/" + "infinite");
				socket = server.accept();
				this.sockets.add(socket);
				//System.out.println("Client joined: " + socket.toString());
				console.println("Client joined: " + socket.toString());
				GameManager bSM = new GameManager(socket, console, this.sockets.size() -1);
				bSM.start();

				console.println();
			} catch (IOException e) {
				String tempError = "Server: " + this.serverName + " could not add clients(Infinite Client Method). Check BasicServer class or the Client.";
				System.err.println(tempError);
				console.println(tempError);
				JOptionPane.showMessageDialog(null, tempError, this.getName(), JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	public void disconnect(boolean displayMessage){
		this.interrupt();
		//System.out.println(this.serverName + " isActive: " + this.isActive());
		console.println(this.serverName + " isActive: " + this.isActive());
		try {
			server.close();
			//System.out.println("Server Socket closed.");
			console.println("Server Socket closed.");
		} catch (IOException e) {
			String tempError = "Server: " + this.serverName + " could not close for some reason. Check BasicServer class.";
			System.err.println(tempError);
			console.println(tempError);
			if(displayMessage)
				JOptionPane.showMessageDialog(null, tempError, this.getName(), JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	public boolean isActive(){
		return isActive;
	}
	
	public ServerSocket getServerSocket(){
		return this.server;
	}
	
	public String getServerName(){
		return this.serverName;
	}
	
	public EKConsole getConsole(){
		return this.console;
	}
}
