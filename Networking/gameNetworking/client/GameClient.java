package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import packets.PacketType;

public class GameClient extends Thread{

	public static void main(String[] args) throws IOException{
		GameClient client = new GameClient("Bob" + (int)(Math.random() * 100));
		}
	
	private DatagramSocket socket;
	public static final int serverPort = 1331;
	private InetAddress host;
	public static final String DELIMETER = ",";
	private final String username;
	private boolean isRunning = false;
	
	/**
	 * PURPOSE: attempt to send join to server. 
	 * 				  If all works great, then start listening
	 * 				  Then, close the socket
	 * @param username
	 */
	public GameClient(String username){
		this.username = username;
		try {
			attemptJoin();
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (UnknownHostException e) {
			System.exit(-1);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void attemptJoin() throws IOException{
		socket = new DatagramSocket();
		host = InetAddress.getByName("localhost");
		try {
			this.sendJoinRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean isAllowedToJoin = (this.recieveData() == PacketType.LOGIN);
		if(isAllowedToJoin){
			Thread t = new Thread(this);
			t.start();
		}
		else{
			System.err.println("YOU WERE REJECTED FROM JOINING!");
		}
	}
	
	@Override
	public void run(){
		Scanner scanner = new Scanner(System.in);
		this.isRunning = true;
		while(this.isRunning){
			System.out.print("What would you like to send to server?: ");
			try {
				String user = scanner.nextLine();
				if(user.equals("quit"))
					isRunning = false;
				else{
					this.sendData(PacketType.MESSAGE, this.socket, user, this.host, serverPort); //this will happen in playerMP's class or when Gui button is pressed
					this.recieveData();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();
	}
	
	
	public void close(){
		try {
			this.sendData(PacketType.DISCONNECT, socket, "", host, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}
	
	private PacketType recieveData() throws IOException{
		byte[] buffer = new byte[5000];
		DatagramPacket recievePacket = new DatagramPacket(buffer, buffer.length);
		socket.receive(recievePacket);
		String rawMessage =  this.getMessage(buffer);
		String[] messageParts = rawMessage.split(DELIMETER);
		System.out.println("Server responded: " + rawMessage);
		return this.lookupPacket(messageParts[0]);
	}
	
	/**
	 * Only to be run once:
	 * @throws IOException
	 */
	private void sendJoinRequest() throws IOException{
		this.sendData(PacketType.LOGIN, socket, "", host, serverPort);
	}
	
	/**
	 * Only to send data over:
	 * @param socket
	 * @param message
	 * @param host
	 * @param serverPort
	 * @throws IOException
	 */
	private void sendData(PacketType packetType, DatagramSocket socket, String message, InetAddress host, int serverPort) throws IOException{
		String actualMessage = packetType.getID() + DELIMETER + username + DELIMETER + message;
		byte[] data = actualMessage.getBytes();
		DatagramPacket request = new DatagramPacket(data, data.length, host, serverPort);
		socket.send(request);
		//System.out.println("Client Sent to server" + message);
	}
	
	private String getMessage(byte[] array){
		return new String(array);
	}
	
	private byte[] getBytes(String m){
		return m.getBytes();
	}
	
	public PacketType lookupPacket(String id){
		try{
			return lookupPacket(Integer.parseInt(id));

		}catch(NumberFormatException e){
			e.printStackTrace();
			return PacketType.INVALID;
		}
	}
	
	public PacketType lookupPacket(int id){
		for(PacketType p: PacketType.values()){
			if(p.getID() == id)
				return p;
		}
		
		return PacketType.INVALID;
	}
}
