package ek_server_basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import rpg_game_components.MultiplayerPlayer;

public class ServerManager extends StoppableThread implements Runnable{

	private EKConsole console;
	private User user;
	private static final String JOIN = "JOIN";
	private static final String JOIN_SUCCESSFUL = "JOIN_SUCCESSFUL";
	private static final String JOIN_FAIL = "JOIN_FAIL";
	private static final String LIST = "LIST";
	private static final String FETCH = "FETCH"; //user request to get the next message
	private static final String FETCH_PLAYER_BOUNDS = "FETCH_PLAYER_BOUNDS"; //user request to get the next message
	private static final String DISCONNECT = "DISCONNECT";
	private static final String SEND_MESSAGE = "SEND_MESSAGE"; //if user wants to make everyone see meessage
	private static final String SEND_PLAYER_BOUNDS = "SEND_PLAYER_BOUNDS";


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
						this.handleSendPlayerBounds(parts);
					}
					
					else if(parts[0].equals(FETCH_PLAYER_BOUNDS)){
						handleFetchPlayerBounds(out);
					}
					
					
					else if(parts[0].equals(DISCONNECT)){
						handleDisconnect(out);
					}
					
					else if(parts[0].equals(FETCH)){
						handleFetch(out);
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
	
		return getRestOfMessage(index, parts.length, parts);
	}
	
	private String getRestOfMessage(int index, int endIndex, String[] parts){
		String ans = "";
		for(int i = index; i < endIndex; i++)
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
	private void handleFetch(PrintWriter out){
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
	 * Fetch Player bOUnds
	 */
	private void handleFetchPlayerBounds(PrintWriter out){
		MultiplayerPlayer player = Server.allMultiPlayers.poll();
		if(player != null){
			out.println(FETCH_PLAYER_BOUNDS + "/" + player.getName() + "/" + player.getX() + "/" + player.getY() + "/" + player.getWidth() + "/" + player.getHeight() + "/" + player.getImageType() + "/" + player.getImagePath());
		}
		else
		{
			out.println(FETCH_PLAYER_BOUNDS + "/" + "NULL");
		}
		out.flush();
	//console.println(FETCH_PLAYER_BOUNDS);
	}
	
	/**
	 * Send PLAYER BOUNDS
	 * @param parts
	 */
	private void handleSendPlayerBounds(String[] parts){
		String name = parts[1];
		int xPos = Integer.parseInt(parts[2]);
		int yPos = Integer.parseInt(parts[3]);
		int width = Integer.parseInt(parts[4]);
		int height = Integer.parseInt(parts[5]);
		String imageType = parts[6];
		String imagePath = this.getRestOfMessage(7, 9, parts);
		//console.println("Server received Player : name: " + name + " x:" + xPos + " y:" + yPos + " w: " + width + " h:" + height );
		

		//console.println("imageType: " + imageType + " imagePath: " + imagePath + " image Width: " + imageW + " imageHeight: " + imageH);

	//	out.println(SEND_PLAYER_BOUNDS + "/" + name + "/" + xPos + "/" + yPos + "/" + width + "/" + height + "/" + imageType + "/" + imagePath + "/" + imageW + "/" + imageH);
	//	out.flush();
		
		Server.allMultiPlayers.add(new MultiplayerPlayer(xPos, yPos, width, height, name, imagePath, imageType));
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
