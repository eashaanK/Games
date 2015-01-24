package main;

import betterMultiplayerSetup.Server;


public class ServerTester {

	public static void main(String[] args){
		Server server = new Server(8888, -1, "Testing Server");
		
		Thread serverThread = new Thread(server);
		serverThread.start();
		
		
	}
}
