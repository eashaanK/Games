package com.lwjgl2D.mulitplayer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient extends Thread{

	private int port;
	private String host;
	private Socket socket;
	private PrintWriter out;
	private Scanner in;
	private boolean isActive;
	
	public GameClient(String host, int port){
		this.host = host;
		this.port = port;
		
		try {
			socket = new Socket(host, port);
			out = new PrintWriter(socket.getOutputStream());
			in = new Scanner(socket.getInputStream());


		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void run(){
		isActive = true;
		while(isActive){
			if(in.hasNext()){
				String message = in.nextLine();
				System.out.println("Client  recieved: " + message);
			}
		}
		
		System.out.println("Client stopped running");
	
	}
	
	public void sendString(String string){
		
		out.println(string);
		out.flush();
	}
}
