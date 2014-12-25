package ek_server_basic;

import java.awt.Color;
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
	
}
