package serverComponents;

import gameObject.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client extends StoppableThread implements Runnable{
	
	public String host;
	public int port;
	public Socket socket;
	public PrintWriter out;
	public static final String PLAYER = "PLAYER";
	public static final String MAP = "MAP";
	public static final String MULTIPLAYER_GAME_STATUS = "MULTIPLAYER_GAME_STATUS";
	public static final String USERS = "USERS";
	public static final String MESSAGE = "MESSAGE";
	public static final String DISCONNECT = "DISCONNECT";
	public static final String AI = "AI";


	
	public Client(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void run(){
		try {
			socket = new Socket(host, port);
			out = new PrintWriter(socket.getOutputStream());
			ClientFetch fetch = new ClientFetch(socket.getInputStream());
			Thread tFetch = new Thread(fetch);
			tFetch.start();
			
			//int messageCounter = 0, mapCounter = 0, 
		//	while(this.isActive()){
		//		if(System.nanoTime() % 1000000 == 0);
		//	}
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.toString() + "\n Could not Connect to host", "Uknown Host", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString() + "\n Could not Connect to host", "IOException", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/**
	 * PLAYER/x/y/width/height/name/health/(,...,pixels)/currentR, currentC, soundFilename
	 * @param p
	 */
	public void sendPlayer(Player p){
		out.println(this.PLAYER + "/" + p.X() + "/" + p.Y() + "/" + p.getWidth() + "/" + p.getHeight() + "/" + p.getName() + "/" + p.getHealth() + "/" + this.turnIntArrayToString(p.getCurrentImage().pixels) + "/" + p.getSprite().currR + "/" + p.getSprite().currC + "/" + "NO SOUND FILE YET");
		out.flush();
	}
	
	/**
	 * MAP/Its actual name, not the location
	 */
	public void sendMap(String name){
		out.println(this.MAP + "/" + name);
		out.flush();
	}
	
	/**
	 * MULTIPLAYER_GAME_STATUS/byte
	 * @param status
	 */
	public void sendMultiplayerGameStatus(byte status){
		out.println(this.MULTIPLAYER_GAME_STATUS + "/" + status);
		out.flush();
	}
	
	/**
	 * MESSAGE/userName/message
	 * @param message
	 * @param userName
	 */
	public void sendMessage(String message, String userName){
		if(userName.contains("/"))
			throw new IllegalArgumentException("The name u tried to send contained a /: " + userName);
		out.println(this.MESSAGE + "/" + userName + "/" + message);
		out.flush();
	}
	
	/*
	public void sendAI(){
		
	}*/
	
	/**
	 * Disconnect/playerName
	 */
	public void sendDisconnect(String name){
		out.println(this.DISCONNECT + "/");
		out.flush();
	}
	
	//////////////////////////////////////////////////Helper/////////////////////////////////////////////////////
	private String turnIntArrayToString(int[] t){
		String ans = "(";
		for(int i = 0; i < t.length - 1; i++){
			ans += t[i] + ", ";
		}
		ans += t[t.length - 1] + ")";
		return ans;
	}

}
