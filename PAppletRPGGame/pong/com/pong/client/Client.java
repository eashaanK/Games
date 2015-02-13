package com.pong.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread
{

	private Socket socket;
	private String host;
	private int port;
	private boolean isRunning = false;
	
	public Client(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run(){
		
		isRunning = true;
		
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			isRunning = false;
			e.printStackTrace();
		} catch (IOException e) {
			isRunning = false;
			e.printStackTrace();
		}
		
		System.out.println("Client started running");
		
		while(isRunning){
		
		}
	}
	
	
}
