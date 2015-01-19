package main;

import ek_server_basic.Server;

public class TemporaryServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread tServer = new Thread(new Server(8888, -1));
		tServer.start();
	}

}
