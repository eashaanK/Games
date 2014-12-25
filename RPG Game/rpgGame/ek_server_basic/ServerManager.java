package ek_server_basic;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ServerManager implements Runnable {

	private EKConsole console;
	private User user;
	private static final String JOIN = "JOIN";

	
	public ServerManager(Socket socket, EKConsole console) {
		user = new User(socket);
		this.console = console;
	}

	@Override
	public void run() {
		try {
			Scanner in = new Scanner(user.socket.getInputStream());
			String message = in.nextLine();
			
			String[] parts = message.split("/");
			
			if(parts[0].equals(JOIN)){
				user.name = parts[1];
				console.println(user.name);

			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
