package ek_server_basic;

import java.net.Socket;

public class User
{
	public Socket socket = null;
	public String name = null;
	
	public User(Socket socket, String name) {
		this.socket = socket;
		this.name = name;
	}
	
	public User(Socket socket) {
		this.socket = socket;
		this.name = null;
	}
	
	public String toString(){
		return name + " " + socket.toString();
	}
}
