package ek_server_basic;

import java.util.Scanner;

public class ClientFetch extends StoppableThread implements Runnable{

	private Scanner in;
	public ClientFetch(Scanner in){
		this.in = in;
	}

	@Override
	public void run() {
		while(this.isActive()){
			if(in.hasNext())
			{
				String message = in.nextLine();
				Client.console.println("Client recieved: " + message);
			}
		}
		
	}
}
