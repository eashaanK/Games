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
	private static final String JOIN_SUCCESSFUL = "JOIN_SUCCESSFUL";
	private static final String JOIN_FAIL = "JOIN_FAIL";
	private static final String LIST = "LIST";
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
						this.handleJoin(parts, out);
					}
					else if(parts[0].equals(SEND_MESSAGE)){
						this.handleSendMessage(parts);
					}
					
					else if(parts[0].equals(SEND_PLAYER_BOUNDS)){
						this.handleSendPlayerBounds(parts, out);
					}
					
					else if(parts[0].equals(ServerManager.SEND_IMAGE)){
						this.handleSendImage(parts);
					}
					
					else if(parts[0].equals(DISCONNECT)){
						handleDisconnect(out);
					}
					
					else if(parts[0].equals(FETCH)){
						handleFetch(parts, out);
					}
					
					else if(parts[0].equals(LIST)){
						handleList(out);
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
	
	private String getRestOfMessage(int index, String[] parts){
		String ans = "";
		for(int i = index; i < parts.length; i++)
			ans += parts[i];
		return ans;
	}
	/**
	 * LIST
	 */
	private void handleList(PrintWriter out){
		//console.println("Server recieved LIST");
		out.println(LIST + "/" + getUserNames().toString());
		out.flush();
	}
	
	/**
	 * FETCH
	 */
	private void handleFetch(String[] parts, PrintWriter out){
		//console.println("Server recieved Fetch");
		String messageToSend = Server.allMessages.poll();
		if(messageToSend != null)
		out.println(FETCH + "/" + messageToSend);
		out.flush();
	}
	
	/**
	 * JOIN
	 * check the name illigebility and stuff. Deny Join request of doens match rules
	 */
	private void handleJoin(String[] parts, PrintWriter out){
		boolean success = false;
		//check if name is allowed
		String failMessage = "Connection Denied! ";
		if(!getUserNames().contains(parts[1])) //if name doesn't exist and...
			success = true;
		if(success){
		user.name = parts[1];
		Server.addUser(user);
		console.println(user.name + " joined" + " Total number of people: " + Server.users.size());
		out.println(this.JOIN_SUCCESSFUL);
		}
		else{
			
			console.println(user.name + " was denied connection based on his/her info");
			out.println(this.JOIN_FAIL + "/" + failMessage);
		//	this.fullStop();
		}
	}
	
	/**
	 * SEND MESSAGE
	 */
	private void handleSendMessage(String[] parts){
		String actualMessage = this.getRestOfMessage(1, parts);
	//	console.println("Server received message: " + actualMessage);
		Server.allMessages.add(user.name + " said: " + actualMessage);
		

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
	private void handleSendPlayerBounds(String[] parts, PrintWriter out){
		String name = parts[1];
		int xPos = Integer.parseInt(parts[2]);
		int yPos = Integer.parseInt(parts[3]);
		int width = Integer.parseInt(parts[4]);
		int height = Integer.parseInt(parts[5]);
		String imageType = parts[6];
		console.println("Server received Player : name: " + name + " x:" + xPos + " y:" + yPos + " w: " + width + " h:" + height + " imageType: " + imageType);
		out.println(SEND_PLAYER_BOUNDS + "/" + name + "/" + xPos + "/" + yPos + "/" + width + "/" + height + "/" + imageType);
		out.flush();
	}
	
	/**
	 * DISCONNECT
	 * @param out
	 */
	//add "disconnected to the server List" and delete itself
	private void handleDisconnect(PrintWriter out){
		Server.allMessages.add(this.user.name + " disconnected.");
		out.println(this.DISCONNECT);
		out.flush();
		int index = getUserNames().indexOf(this.user.name);
		Server.users.remove(index);
		this.fullStop();
		Thread.currentThread().interrupt();
	}

}
