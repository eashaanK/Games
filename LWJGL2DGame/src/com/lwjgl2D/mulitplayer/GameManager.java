package com.lwjgl2D.mulitplayer;

import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class GameManager extends Thread{

	private Socket socket ;
	private int id;
	private EKConsole console;
	private boolean isActive;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	
	public GameManager(Socket socket, EKConsole console, int i) {
		this.socket = socket;
		this.id = i;
		this.console = console;
	}
	
	@Override
	public void run(){
		isActive = true;
		while(isActive){
		
				
				try {
					out = new ObjectOutputStream(socket.getOutputStream());
					out.flush();
					
					this.sendString("Hi Client");
					
					
					
					in = new ObjectInputStream(socket.getInputStream());

					
					Object o = in.readObject();
					
					System.out.println(o);
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		console.println("Manager " + id + " stopped running");
	}
	
	public void sendString(String string){
		try {
			out.writeObject(string);
			out.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
