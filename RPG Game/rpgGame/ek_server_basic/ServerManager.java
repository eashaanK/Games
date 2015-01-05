package ek_server_basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerManager extends StoppableThread implements Runnable{

	private EKConsole console;
	private User user;
	private static final String JOIN = "JOIN";
	private static final String FETCH = "FETCH"; //user request to get the next message
	private static final String DISCONNECT = "DISCONNECT";
	private static final String SEND_MESSAGE = "SEND_MESSAGE"; //if user wants to make everyone see meessage
	private static final String SEND_PLAYER_BOUNDS = "SEND_PLAYER_BOUNDS";
	private static final String SEND_IMAGE = "SEND_IMAGE";

	//private static final String JOIN = "JOIN";*/

	
	public ServerManager(Socket socket, EKConsole console) {
		user = new User(socket);
		this.console = console;
	}

	@Override
	public void run() {		
		try {
			Scanner in = new Scanner(user.socket.getInputStream());
			PrintWriter out = new PrintWriter(user.socket.getOutputStream());
			
			while(this.isActive()){
				if(in.hasNext()){
					String message = in.nextLine();
					
					String[] parts = message.split("/");
					//Join request
					if(parts[0].equals(JOIN)){
						this.handleJoin(parts);
					}
					else if(parts[0].equals(SEND_MESSAGE)){
						this.handleSendMessage(parts);
					}
					
					else if(parts[0].equals(SEND_PLAYER_BOUNDS)){
						this.handleSendPlayerBounds(parts);
					}
					
					else if(parts[0].equals(ServerManager.SEND_IMAGE)){
						this.handleSendImage(parts);
					}
					
					else if(parts[0].equals(DISCONNECT)){
						handleDisconnect(out);
					}
					
					else if(parts[0].equals(FETCH)){
						handleFetch(parts);
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console.println("ServerManager of " + user.name + " stopped running");
	}
	
	private ArrayList<String> getUserNames(){
		ArrayList<String> names = new ArrayList<String>();
		for(User u: Server.users){
			names.add(u.name);
		}
		return names;
	}
	
	/**
	 * FETCH
	 */
	private void handleFetch(String[] parts){
		console.println("Server recieved Fetch");
	}
	
	/**
	 * JOIN
	 */
	private void handleJoin(String[] parts){
		user.name = parts[1];
		Server.addUser(user);
		console.println(user.name + " joined" + " Total number of people: " + Server.users.size());
	}
	
	/**
	 * SEND MESSAGE
	 */
	private void handleSendMessage(String[] parts){
		console.println("Server received message: " + parts[1]);

	}
	
	/**
	 * IMAGE
	 */
	private void handleSendImage(String[] parts){
		int width = Integer.parseInt(parts[1]);
		int height = Integer.parseInt(parts[2]);
		String path = "";
		for(int i = 3; i < parts.length; i++)
			path += parts[i];
		console.println("Server received image: " + path + " w: " + width + " h: " + height);
	}
	
	/**
	 * PLAYER BOUNDS
	 * @param parts
	 */
	private void handleSendPlayerBounds(String[] parts){
		int xPos = Integer.parseInt(parts[1]);
		int yPos = Integer.parseInt(parts[2]);
		int width = Integer.parseInt(parts[3]);
		int height = Integer.parseInt(parts[4]);
		console.println("Server received Player Bounds: x:" + xPos + " y:" + yPos + " w: " + width + " h:" + height);
	}
	
	/**
	 * DISCONNECT
	 * @param out
	 */
	//add "disconnected to the server List" and delete itself
	private void handleDisconnect(PrintWriter out){
		//Server.allMessages.add(this.user.name + " disconnected");
		//if(PServer.users.contains(this.user.name))
		int index = getUserNames().indexOf(this.user.name);
		Server.users.remove(index);
		this.fullStop();
		Thread.currentThread().interrupt();
	}

}
