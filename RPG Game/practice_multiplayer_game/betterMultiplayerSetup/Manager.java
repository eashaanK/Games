package betterMultiplayerSetup;

import gameObjects.GameObject;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Manager extends StoppableThread implements Runnable {

	private Socket socket;
	private EKConsole console;
	private int id;

	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Manager(Socket socket, EKConsole console, int id) {
		this.socket = socket;
		this.console = console;

	}

	private void setUpInputStreams(Socket socket) {
		try {
			//this.out = new ObjectOutputStream(socket.getOutputStream());
			//out.flush();
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setUpOutputStreams(Socket socket) {
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			//this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// MAKE SURE TO SETUP STREAM EVERYTIME
	@Override
	public void run() {
		
	}

	/*********************************************** Only allowed to use PrintWriter for fetch commands ********************************************/
	private void println(String message){
		System.out.println("<Server> " + message);
	}
}
