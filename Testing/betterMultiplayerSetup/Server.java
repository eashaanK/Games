

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;


public class Server extends StoppableThread implements Runnable {

	private final int port, maxClients;
	private ServerSocket server;
	private final String serverName;
	public ArrayList<Socket> sockets = new ArrayList<Socket>();
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
			this.stopThread();
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
				Manager bSM = new Manager(socket, this.console, this.sockets.size() -1);
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
				this.sockets.add(socket);
				//System.out.println("Client joined: " + socket.toString());
				console.println("Client joined: " + socket.toString());
				Manager bSM = new Manager(socket, console, this.sockets.size() -1);
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
		this.stopThread();

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
		console.stopThread();

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
