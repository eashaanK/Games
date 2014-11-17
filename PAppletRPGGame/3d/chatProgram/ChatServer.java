package chatProgram;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * send the list of users to the client
 * for specific command: "!@#$%^&*()"
 * @author eashaan
 *
 */

public class ChatServer {

	public static ArrayList<Socket> connections = new ArrayList<Socket>();
	public static ArrayList<String> users = new ArrayList<String>();
	public static String specialCommand = "!@#$%^&*()";
	
	public static void main(String[] args){
		try{
			final int PORT = 8888;
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("Waiting for clients to join...");
			
			while(true){
				Socket socket = server.accept();
				//connections.add(socket);
				
				System.out.println("Client connected : " + socket.toString());
				addUser(socket);
				
				ChatManager chat = new ChatManager(socket);
				Thread t = new Thread(chat);
				t.start();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends the client the list of the users
	 * @param socket
	 * @throws IOException
	 */
	public static void addUser(Socket socket) throws IOException{
		Scanner input = new Scanner(socket.getInputStream());
		String username = input.nextLine();
		connections.add(socket);
		users.add(username);
		
		for(int i = 0; i < connections.size(); i++){
			Socket tempSoc = connections.get(i);
			PrintWriter output = new PrintWriter(tempSoc.getOutputStream());
			output.println(specialCommand + users);
			output.flush();
		}
	}
}
