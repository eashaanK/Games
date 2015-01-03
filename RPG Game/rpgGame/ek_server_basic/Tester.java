package ek_server_basic;

import java.awt.Rectangle;

public class Tester {

	public static void main(String[] args){
		Server server = new Server(8888, 1, "RPG Server");
		Thread t1 = new Thread(server);
		t1.start();
		
	
		
		
		Client client = new Client("localhost", 8888);
		Thread t2 = new Thread(client);
		t2.start();
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		client.sendPlayerBounds(new Rectangle(10, 10, 100, 100));
		client.sendImage("I WILL SHIT MY PANTS", 69, 69);
		
	}
}
