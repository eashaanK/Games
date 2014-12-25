package ek_server_basic;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Server extends StoppableThread implements Runnable {

	private final int port, maxClients;
	private ServerSocket server;
	private final String serverName;
	public ArrayList<User> sockets = new ArrayList<User>();
	private EKConsole console;
	
	/**
	 * Starts a new Server on the current computer
	 * @param port this server's port
	 * @param maxClients if -, then infinite
	 */
	public Server(int port, int maxClients){
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
	public Server(int port, int maxClients, String name){
		this.port = port;
		this.maxClients = maxClients;
		this.serverName = name;
	}
	
	public void run(){
		//check if Server can even run
		
		if(!this.isActive())
		{
			String tempError = "Server: " + this.serverName + " quit right before it could do anything. Check class that starts Server Thread.";
			System.err.println(tempError);
			JOptionPane.showMessageDialog(null, tempError, this.getName(), JOptionPane.ERROR_MESSAGE);
			this.fullStop();
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
	
	/*******
	 * THE CIENT MUST INSTANTLY SEND ITS NAME TO SERVER
	 */
	
	private void startLimitedServer(){
		for(int i = 0; i < this.maxClients && this.isActive(); i++){
			Socket socket;
			try {
				console.println("Wating for client to join: " + this.sockets.size() + "/" + this.maxClients);
				socket = server.accept();
				//Scanner in = new Scanner(socket.getInputStream());
			//	String name = in.nextLine();
				//this.sockets.add(new User(socket, name));
				//System.out.println("Client joined: " + socket.toString());
				console.println("Client joined: " + socket.toString());
				ServerManager bSM = new ServerManager(socket, this.console);
				Thread t = new Thread(bSM);
				t.start();
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
				Scanner in = new Scanner(socket.getInputStream());
				String name = in.nextLine();
				this.sockets.add(new User(socket, name));
				//System.out.println("Client joined: " + socket.toString());
				console.println("Client joined: " + socket.toString());
				ServerManager bSM = new ServerManager(socket, console);
				Thread t = new Thread(bSM);
				t.start();
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
		this.stop();
		this.disconnect();
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
		console.fullStop();
	}
	
	public boolean isActive(){
		return super.isActive();
	}
	
	public ServerSocket getServerSocket(){
		return this.server;
	}
	
	public String getName(){
		return this.serverName;
	}
	
	public EKConsole getConsole(){
		return this.console;
	}
}
