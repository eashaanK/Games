package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import packets.PacketType;

public class GameServer extends Thread {

	private static boolean isRunning = false;
	static DatagramSocket socket;
	public static final int serverPort = 1331;
	public static final String DELIMETER = ",";
	public List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

	public static void main(String[] args) throws IOException {
		new GameServer();

	}

	public GameServer() throws SocketException {
		socket = new DatagramSocket(1331);
		new Thread(this).start();

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
		System.out.println("Server Recieved: " + rawMessage);

		int packetID = Integer.parseInt(messageParts[0]);

		PacketType packetType = this.lookupPacket(packetID);

		switch (packetType) {
		case INVALID:
			break;
		case LOGIN:
			handleLogin(messageParts[1], request);
			break;
		case DISCONNECT:

			break;
		case MESSAGE:
			handleMessage(messageParts[1], messageParts[2], request);
			System.out.println("Someone is attempting to message the server");
			break;
		}
	}

	private void handleMessage(String username, String message, DatagramPacket request) {
		//PlayerMP p = this.getPlayerFromPacket(request.getAddress(), request.getPort());
		try {
			sendToAll(PacketType.MESSAGE, username + " " + message);
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
	private void handleLogin(String username, DatagramPacket request) {
		System.out.println(username + " joined");
		try {
			this.connectedPlayers.add(new PlayerMP(username, request.getAddress(), request.getPort()));
			//this.sendData(PacketType.LOGIN, username + " joined", request.getAddress(), request.getPort());
			//SEND TO ALL
			sendToAll(PacketType.LOGIN, username + " joined");
		} catch (IOException e) {
			e.printStackTrace();
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
	
	public PlayerMP getPlayerFromPacket(InetAddress ipAddress, int port){
		for (PlayerMP p : this.connectedPlayers) {
			if(p.ipAddress.equals(ipAddress) && p.port == port){
				return p;
			}

		}
		
		return null;
	}

	private class PlayerMP {
		String name;
		InetAddress ipAddress;
		int port;

		public PlayerMP(String name, InetAddress ipAddress, int port) {
			this.name = name;
			this.ipAddress = ipAddress;
			this.port = port;
		}
	}
}
