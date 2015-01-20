package shapes_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	}

	@Override
	public void run() {
		
		while(this.isActive()){
			if(socket.isClosed()) //this doesn't work
				console.println("SOMEONE DISCONNECTED WITHOUT PROPER PROTOCOL");
			
			if(in.hasNext()){
				String message = in.nextLine();
				String[] parts = message.split(C.REGEX);
				if(parts[0].equals(C.JOIN_CONFIRMED)){
					System.out.println("YAYA!!!!!!!!");
				}
				else if(parts[0].equals(C.JOIN_FAIL)){
					JOptionPane.showMessageDialog(null,  "Server denied your connection on terms of your information.", C.JOIN_FAIL + " Error", JOptionPane.ERROR_MESSAGE);
					this.fullStop();
				}
				else if(parts[0].equals(C.PLAYER_INFO)){
					//playerInfo(parts);
				}
			
			}
		}
		
	}


}
