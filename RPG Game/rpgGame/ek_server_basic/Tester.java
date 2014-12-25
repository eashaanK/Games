package ek_server_basic;

import java.awt.Color;

import javax.swing.JFrame;

public class Tester {

	public static void main(String[] args){
		Server server = new Server(8888, 1, "RPG Server");
		Thread t1 = new Thread(server);
		t1.start();
		
	
		
		
		Client client = new Client("localhost", 8888);
		Thread t2 = new Thread(client);
		t2.start();
		
	}
}
