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
	public String name = "Tester Client";
	private static final String JOIN = "JOIN";
	private static final String DISCONNECT = "DISCONNECT";
	private static final String SEND_MESSAGE = "SEND_MESSAGE";
	private static final String SEND_PLAYER_BOUNDS = "SEND_PLAYER_BOUNDS";
	private static final String SEND_IMAGE = "SEND_IMAGE";

	
	public Client(String host, int port){
		try {
			this.socket = new Socket(host, port);
			console = new EKConsole(500, 500, "RPG Game Client Debugger", Color.white, Color.GREEN);
			Thread t1 = new Thread(console);
			t1.start();
		} catch (UnknownHostException e) {
			console.println("UKNOWN HOST: " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			console.println(e.toString());
			e.printStackTrace();
		}
	}

	//SEND THE NAME FIRST
	@Override
	public void run() {
		try {
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			out.println(JOIN + "/" + name);
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//x , y, w, h
	public void sendPlayerBounds(Rectangle rect){
		out.println(Client.SEND_PLAYER_BOUNDS + "/" + rect.x + "/" + rect.y + "/" + rect.width + "/" + rect.height);
		out.flush();
	}
	
	public void sendMessage(String message){
		out.println(SEND_MESSAGE + "/" + message);
		out.flush();
	}
	
	public void sendImage(String path, int width, int height){
		out.println(Client.SEND_IMAGE + "/" + width + "/" + height + "/" + path);
		out.flush();
	}
	
}
