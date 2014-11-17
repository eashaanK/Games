package chatProgram;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatManager implements Runnable{

	Socket sock;
	private Scanner input;
	private PrintWriter output;
	String message = "";
	
	public ChatManager(Socket s){
		this.sock = s;	
	}
	
	/**
	 * Removes any connections that might be empty
	 * outputs if anyone disconnected
	 * @throws IOException 
	 */
	private void checkDisconnected() throws IOException{
		if(!sock.isConnected()){
			for(int i = 0; i < ChatServer.connections.size(); i++){
				if(ChatServer.connections.get(i) == sock)
					ChatServer.connections.remove(i);
			}
			
			for(int i = 0; i < ChatServer.connections.size(); i++){
				Socket tempSoc = ChatServer.connections.get(i);
				PrintWriter	out = new PrintWriter(tempSoc.getOutputStream());
				out.println(sock.getLocalAddress().getHostName() + " disconnected");
				out.flush();
				System.out.println(tempSoc.getLocalAddress().getHostName() + " disconnected");
			}
		}
	}

	public void run() {
		try{
			try{
				input = new Scanner(sock.getInputStream());
				output = new PrintWriter(sock.getOutputStream());
				
				while(true){
					checkDisconnected();
					
					if(!input.hasNext())
						return;
					this.message = input.nextLine();
					
					System.out.println("Client said: " + this.message);
					
					for(int i = 0; i < ChatServer.connections.size(); i++){
						Socket tempSoc = ChatServer.connections.get(i);
						PrintWriter	out = new PrintWriter(tempSoc.getOutputStream());
						out.println(this.message);
						out.flush();
						System.out.println("Sent to: " + tempSoc.getLocalAddress().getHostName());
					}
				}
			}
			finally{
				sock.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
