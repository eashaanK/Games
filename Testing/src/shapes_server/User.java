package shapes_server;

import java.net.Socket;

public class User {

	public Socket socket;
	public String name;
	
	public User(Socket s, String name){
		this.socket =s;
		this.name = name;
	}
}
