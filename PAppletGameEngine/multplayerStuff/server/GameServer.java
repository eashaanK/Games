package server;

import java.awt.Color;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import packets.PacketType;
import entities.PlayerMP;

public class GameServer extends Thread {

	private static boolean isRunning = false;
	static DatagramSocket socket;
	public static final int serverPort = 1331;
	public static final String DELIMETER = ",";
	public List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	private GameServerDebugger debugger;

	/*public static void main(String[] args) throws IOException {
		new GameServer();

	}*/

	public GameServer() throws SocketException {
		debugger = new GameServerDebugger("Game Server Debugger", 800, 800);
		socket = new DatagramSocket(1331);
		new Thread(this).start();
		
	}
	
	public void showDebugger(boolean show){
		debugger.setVisible(show);
	}

	public void run() {
		byte[] buffer = new byte[1000];
		isRunning = true;
		while (isRunning) {
			try {
				readData(buffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		socket.close();

	}

	private void readData(byte[] buffer) throws IOException {
		DatagramPacket request = new DatagramPacket(buffer, buffer.length);
		socket.receive(request);
		String rawMessage = new String(request.getData());
		String[] messageParts = rawMessage.split(DELIMETER);
		//System.out.println("Server Recieved: " + rawMessage);

		int packetID = Integer.parseInt(messageParts[0]);

		PacketType packetType = this.lookupPacket(packetID);

		switch (packetType) {
		case INVALID:
			break;
		case LOGIN:
			handleLogin(messageParts[1], Float.parseFloat(messageParts[2]), Float.parseFloat(messageParts[3]), request);
			break;
		case DISCONNECT:
			handleDisconnect(messageParts[1], request.getAddress(), request.getPort());
			break;
		case MOVE:
			handleMove(messageParts[1], Float.parseFloat(messageParts[2]), Float.parseFloat(messageParts[3]), request);
			break;
		case MESSAGE:
			handleMessage(messageParts[1], messageParts[2]);
			System.out.println("Someone is attempting to message the server");
			break;
		}
	}

	private void handleDisconnect(String username, InetAddress ipAddress, int port) {
		Object[] data = this.getPlayerFromPacket(ipAddress, port);
		int index = (Integer)(data[1]);
		this.connectedPlayers.remove(index);
		//System.out.println(username + " disconnected");
		debugger.println(username + " disconnected");
		try {
			this.sendToAll(PacketType.PERSON_DISCONNECTED, username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleMove(String usernameWhoMoved, float x, float y, DatagramPacket request) {
	//	System.out.println(usernameWhoMoved + " moved to : " + x + ", " + y);
		debugger.printMoved(usernameWhoMoved, x, y);

		Object[] d = this.getPlayerFromPacket(request.getAddress(), request.getPort());
		PlayerMP player = (PlayerMP)(d[0]);
		int index = (Integer)(d[1]);
		
		player.x = x;
		player.y = y;
		
		this.connectedPlayers.set(index, player);
		
		try {
			this.sendToAll(PacketType.MOVE, usernameWhoMoved + this.DELIMETER + x + this.DELIMETER + y);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void handleMessage(String username, String message) {
		//PlayerMP p = this.getPlayerFromPacket(request.getAddress(), request.getPort());
		try {
			sendToAll(PacketType.MESSAGE, username + this.DELIMETER + message);
			debugger.printMessage(username, message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * check if name is valid, then add their address and ports for later use to
	 * send them data
	 * 
	 * @param messageParts
	 */
	private void handleLogin(String username, float sX, float sY, DatagramPacket request) {
		//System.out.println(username + " joined");
		debugger.println(username + " joined");

		try {
			this.connectedPlayers.add(new PlayerMP(username, sX, sY, request.getAddress(), request.getPort()));
			//this.sendData(PacketType.LOGIN, username + " joined", request.getAddress(), request.getPort());
			//SEND TO ALL
			this.sendData(PacketType.OLD_PERSON_WHO_WAS_ALREADY_HERE_BEFORE_U, this.connectedPlayers.toString(), request.getAddress(), request.getPort());
			sendToAll(PacketType.NEW_PERSON_JUST_JOINED, username + this.DELIMETER + sX + this.DELIMETER + sY, username);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void sendToAll(PacketType packetType, String message,
			String usernameToAvoid) throws IOException {
		for (PlayerMP p : this.connectedPlayers) {
			if( ! p.name.equals(usernameToAvoid)){
				this.sendData(packetType, message, p.ipAddress, p.port);
			}

		}
	}

	public void sendToAll(PacketType packetType, String message) throws IOException {
		for (PlayerMP p : this.connectedPlayers) {
			this.sendData(packetType, message, p.ipAddress, p.port);

		}
	}

	/**
	 * Only to send data over:
	 * 
	 * @param socket
	 * @param message
	 * @param host
	 * @param serverPort
	 * @throws IOException
	 */
	private void sendData(PacketType packetType, String message, InetAddress ipAddress, int port) throws IOException {
		String actualMessage = packetType.getID() + DELIMETER + message;
		byte[] data = actualMessage.getBytes();
		DatagramPacket reply = new DatagramPacket(data, data.length, ipAddress,
				port);
		socket.send(reply);
		// System.out.println("Client Sent to server" + message);
	}

	private String getMessage(byte[] array) {
		return new String(array);
	}

	private byte[] getBytes(String m) {
		return m.getBytes();
	}

	public PacketType lookupPacket(String id) {
		try {
			return lookupPacket(Integer.parseInt(id));

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return PacketType.INVALID;
		}
	}

	public PacketType lookupPacket(int id) {
		for (PacketType p : PacketType.values()) {
			if (p.getID() == id)
				return p;
		}

		return PacketType.INVALID;
	}
	
	/**
	 * PlayerMP is 1st, Integer in second
	 * @param ipAddress
	 * @param port
	 * @return
	 */
	public Object[] getPlayerFromPacket(InetAddress ipAddress, int port){
		Object[] data = new Object[2];
		for(int i = 0; i < this.connectedPlayers.size(); i++){
			PlayerMP p = this.connectedPlayers.get(i);
			if(p.ipAddress.equals(ipAddress) && p.port == port){
				data[0] = p;
				data[1] = i;
				return data;
			}
		}
		
		return null;
	}

	
}
