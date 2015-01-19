package serverComponents;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Manager implements Runnable {

	private Socket socket;
	private EKConsole console;
	private int id;
	
	public Manager(Socket socket, EKConsole console, int id) {
		this.socket = socket;
		this.console = console;
	}

	@Override
	public void run() {
		try {
			Scanner in = new Scanner(socket.getInputStream());
			
			console.println("Manager " + id + " received: " + in.nextLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
