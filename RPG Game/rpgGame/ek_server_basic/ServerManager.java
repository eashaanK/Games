package ek_server_basic;

import java.awt.Rectangle;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerManager extends StoppableThread implements Runnable{

	private EKConsole console;
	private User user;
	private static final String JOIN = "JOIN";
	private static final String DISCONNECT = "DISCONNECT";
	private static final String SEND_MESSAGE = "SEND_MESSAGE";
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
			
			while(this.isActive()){
				String message = in.nextLine();
				
				String[] parts = message.split("/");
				//Join request
				if(parts[0].equals(JOIN)){
					user.name = parts[1];
					Server.addUser(user);
					console.println(user.name + " joined" + " Total number of people: " + Server.users.size());
				}
				else if(parts[0].equals(SEND_MESSAGE)){
					console.println("Server received message: " + parts[1]);
				}
				
				else if(parts[0].equals(SEND_PLAYER_BOUNDS)){
					int xPos = Integer.parseInt(parts[1]);
					int yPos = Integer.parseInt(parts[2]);
					int width = Integer.parseInt(parts[3]);
					int height = Integer.parseInt(parts[4]);
					console.println("Server received Player Bounds: x:" + xPos + " y:" + yPos + " w: " + width + " h:" + height);
				}
				
				else if(parts[0].equals(ServerManager.SEND_IMAGE)){
					int width = Integer.parseInt(parts[1]);
					int height = Integer.parseInt(parts[2]);
					String path = "";
					for(int i = 3; i < parts.length; i++)
						path += parts[i];
					console.println("Server received image: " + path + " w: " + width + " h: " + height);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console.println("ServerManager of " + user.name + " stopped running");
	}

}
