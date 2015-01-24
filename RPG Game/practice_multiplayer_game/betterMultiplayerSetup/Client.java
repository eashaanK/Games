package betterMultiplayerSetup;
import gameObjects.GameObject;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client extends StoppableThread implements Runnable{

	private int port;
	private String host;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private void setUpInputStreams(Socket socket) {
		try {
			//this.out = new ObjectOutputStream(socket.getOutputStream());
			//out.flush();
			this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setUpOutputStreams(Socket socket) {
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			//this.in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Client(String host, int port){
		this.port = port;
		this.host = host;
		try {
			this.socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			this.stopThread();
			e.printStackTrace();
		} catch (IOException e) {
			this.stopThread();
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
	}
	
	public void sendGameObject(GameObject g){
		//System.out.println("Preparing to send a gameObject");
		this.setUpOutputStreams(socket); //MUST RESET OUTPUT STREAM AND INPUT STREAM EVERY TIME

		try {
			out.writeObject(g);
			out.flush();

			//System.out.println("Message sent");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
