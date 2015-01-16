package ek_server_basic;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends StoppableThread implements Runnable{

	private Socket socket;
	public static EKConsole console;
	private Scanner in;
	private PrintWriter out;
	private ClientFetch fetch;
	public static final String JOIN = "JOIN";
	public static final String JOIN_SUCCESSFUL = "JOIN_SUCCESSFUL";
	public static final String JOIN_FAIL = "JOIN_FAIL";
	public static final String LIST = "LIST";
	public static final String FETCH = "FETCH"; //user request to get the next message
	public static final String FETCH_PLAYER_BOUNDS = "FETCH_PLAYER_BOUNDS"; //user request to get the next message
	public static final String DISCONNECT = "DISCONNECT";
	public static final String SEND_MESSAGE = "SEND_MESSAGE"; //if user wants to make everyone see meessage
	public static final String SEND_PLAYER_BOUNDS = "SEND_PLAYER_BOUNDS";
	
	public Client(String host, int port){
		console = new EKConsole(500, 500, "RPG Game Client Debugger", Color.white, Color.GREEN);
		try {
			this.socket = new Socket(host, port);
			Thread t1 = new Thread(console);
			t1.start();
		} catch (UnknownHostException e) {
			console.println("UKNOWN HOST: " + e.toString());
			e.printStackTrace();
			this.fullStop();
		} catch (IOException e) {
			console.println(e.toString());
			e.printStackTrace();
			this.fullStop();
		}
	}

	//SEND THE NAME FIRST
	@Override
	public void run() {
		if(this.isActive()){
			try {
				
				Thread t = new Thread(console);
				t.start();
				
				in = new Scanner(socket.getInputStream());
				out = new PrintWriter(socket.getOutputStream());
				
				fetch = new ClientFetch(in);
				Thread tFetch = new Thread(fetch);
				tFetch.start();
				
				while(this.isActive()){
					if(System.nanoTime() % 10000000 == 0){
						fetch();
					}
					
					if(System.nanoTime() % 100000 == 0){
						fetchPlayerBounds();
					}
					
					if(System.nanoTime() % 10000000 == 0)
						list();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.err.println("Client Run method ended");
	}
	
	@Override
	public void fullStop(){
		super.fullStop();
		this.console.fullStop();
		fetch.fullStop();
		System.out.println("Disconnected everything");
	}
	
	private void fetchPlayerBounds(){
		out.println(this.FETCH_PLAYER_BOUNDS);
		out.flush();
	}
	
	private void fetch(){
		out.println(this.FETCH);
		out.flush();
	}
	
	private void list(){
		out.println(this.LIST);
		out.flush();
	}
	
	public void joinRequest(String name){
		out.println(JOIN + "/" + name);
		out.flush();
	}
	
	//x , y, w, h
	public void sendPlayerBounds(String name, Rectangle rect, String imageType, String path){
		out.println(Client.SEND_PLAYER_BOUNDS + "/" + name + "/" + rect.x + "/" + rect.y + "/" + rect.width + "/" + rect.height + "/" + imageType + "/" + path);
		out.flush();
	}
	
	public void sendMessage(String message){
		out.println(SEND_MESSAGE + "/" + message);
		out.flush();
	}
	
	
}
