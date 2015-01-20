package shapes_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Manager extends StoppableThread implements Runnable {

	private EKConsole console;
	private int id;
	private Socket socket;
	private Scanner in;
	private PrintWriter out;
	
	public Manager(Socket socket, EKConsole console, int i) {
		this.socket = socket;
		this.console = console;
		this.id = i;
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			this.fullStop();
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while(this.isActive()){
			if(socket.isClosed()) //this doesn't work
				console.println("SOMEONE DISCONNECTED WITHOUT PROPER PROTOCOL");
			
			
			if(in.hasNext()){
				String message = in.nextLine();
				String[] parts = message.split(C.REGEX);
				if(parts[0].equals(C.JOIN)){
					join(parts, message);
				}
				else if(parts[0].equals(C.PLAYER_INFO)){
					playerInfo(message);
				}
			
			}
		}
		
	}

	//change the decoy to old
	private void playerInfo(String message) {
		message = message.replace(C.DECOY, C.OLD);
		this.sendToAll(message);
	}

	private void join(String[] parts, String message) {
		Server.users.add(new User(socket, parts[2]));
		message = message.replace(C.DECOY, C.NEW);
		//if successful
		message = message.replace(C.JOIN, C.JOIN_CONFIRMED);
		out.println(message);
		out.flush();
		this.sendToAll(parts[2] + " joined");
	}
	
	private void sendToAll(String message){
		for(int i = 0; i < Server.users.size(); i++){
			Socket tempSoc = Server.users.get(i).socket;
			try {
				PrintWriter out = new PrintWriter(tempSoc.getOutputStream());
				out.println(message);
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


}
