package multilayer_shit;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Manager extends StoppableThread implements Runnable {

	private Socket socket;
	private EKConsole console;
	private int id;
	private Scanner in;
	private PrintWriter out;

	public Manager(Socket socket, EKConsole console, int id) {
		this.socket = socket;
		this.console = console;
	}

	@Override
	public void run() {
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			// console.println("Manager " + id + " received: " + in.nextLine());

			while (this.isActive()) {
				if (in.hasNext()) {
					String message = in.nextLine(); //only fetch messages
					console.println(message);
					if(message.equals(C.FETCH_MESSAGE)){
						continue;
					}
					else if(message.equals(C.FETCH_USERS)){
						continue;
					}
					String parts[] = message.split(C.REGEX);
				
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*********************************************** Only allowed to use PrintWriter for fetch commands ********************************************/
	
}
