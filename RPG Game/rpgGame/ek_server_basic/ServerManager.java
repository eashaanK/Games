package ek_server_basic;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerManager extends StoppableThread implements Runnable{

	private EKConsole console;
	private User user;
	private static final String JOIN = "JOIN";
	private static final String DISCONNECT = "DISCONNECT";
	private static final String SEND_MESSAGE = "SEND_MESSAGE";
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
					console.println("Server received: " + parts[1]);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		console.println("ServerManager of " + user.name + " stopped running");
	}

}
