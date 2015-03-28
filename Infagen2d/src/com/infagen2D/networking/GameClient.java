package com.infagen2D.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.infagen2D.core.Game;
import com.infagen2D.entities.PlayerMP;

public class GameClient extends Thread {

	/*
	 * public static void game(String[] args) throws IOException{ GameClient
	 * client = new GameClient("Bob" + (int)(Math.random() * 100)); }
	 */

	private DatagramSocket socket;
	public static final int serverPort = 1331;
	public InetAddress host;
	public static final String DELIMETER = ",";
	public final String username;
	private boolean isRunning = false;
	public final int spawnX, spawnY;
	
	private Game game;

	/**
	 * PURPOSE: attempt to send join to server. If all works great, then start
	 * listening Then, close the socket
	 * 
	 * @param username
	 */
	public GameClient(String username, Game game, int spawnX, int spawnY) {
		this.username = username;
		this.game =game;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}

	public void begin(String address){
		try {
			socket = new DatagramSocket();
			host = InetAddress.getByName(address);
			this.start();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
	/*	try {
			this.sendJoinRequest();
		} catch (IOException e) {
			e.printStackTrace();
		}

		boolean isAllowedToJoin = (this.recieveData() == PacketType.LOGIN);
		if (isAllowedToJoin) {
			Thread t = new Thread(this);
			t.start();
		} else {
			System.err.println("YOU WERE REJECTED FROM JOINING!");
		}*/
	}

	@Override
	public void run() {
		boolean canStart = false;
		try {
			//System.out.println("Join request sent:");
			this.sendJoinRequest();
			//System.out.println("Waiting for Approval");
			Object[] loginConfirm = recieveData();
			canStart = ( (PacketType)(loginConfirm[0]) == PacketType.OLD_PERSON_WHO_WAS_ALREADY_HERE_BEFORE_U);
			DatagramPacket receiveApprovalPacket = (DatagramPacket) loginConfirm[2];
		
			this.isRunning = canStart;
			if(this.isRunning){
				System.out.println("Client connected!");
				String[] playersThatCameBefore = (String[])loginConfirm[1];
				addPlayersThatCameBefore(playersThatCameBefore, receiveApprovalPacket);
				/*parts[0] = parts[0].replace("[", "");
				parts[parts.length - 1] = parts[parts.length - 1].replace("]", "");
				String[] toStringsOfPlayers = (String)(parts[1]);
				for(String toStringOfPlayer : toStringsOfPlayers{
					String
				}*/
				//System.out.println(parts[1]);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
		while (this.isRunning) {
			try {
		
				Object[] data = this.recieveData();
				PacketType packetType = (PacketType)data[0];
				String[] parts = (String[])data[1];
				DatagramPacket recievePacket = (DatagramPacket) data[2];

				
				switch(packetType){
				case MOVE:
					handleMove(parts[1], Integer.parseInt(parts[2].trim()), Integer.parseInt(parts[3].trim()));
					break;
				case MESSAGE:
					
					break;
					
				case PERSON_DISCONNECTED:
					handlePersonDisconnected(parts[1]);
					break;
					
				case NEW_PERSON_JUST_JOINED:
					game.addEntity(new PlayerMP(game.level, parts[1], Integer.parseInt(parts[2].trim()), Integer.parseInt(parts[3].trim()), recievePacket.getAddress(), recievePacket.getPort()));
					break;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		close();
	}
	
	private void handlePersonDisconnected(String username) {
		//Object[] data = this.getPlayerFromName(username);
		//int index = Integer.parseInt((String) data[1]);
		/*game.removeEntity(index);
		game.displayMessgae(username + " disconnected");*/
		//System.out.println(this.username + " recieved disconnect: " + username + " " + (data == null) + game.getEntities().get(1));
		for(int i = 0 ; i < game.getEntities().size(); i++){
			//if(game.getEntities().get(i) instanceof PlayerMP){
				PlayerMP p =  (PlayerMP) game.getEntities().get(i);
				System.out.println("Comparing: " + p.getName() + " " + username + " " + p.getName().trim().equals(username.trim()));
				if(p.getName().trim().equals(username.trim())){
					game.removeEntity(i);
					game.displayMessage(username + " disconnected");
					return;
				}
			//}
		}
	}
	

	public void sendDisconnect(){
		try {
			this.sendData(PacketType.DISCONNECT, socket, "", host, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addPlayersThatCameBefore(String[] playersThatCameBefore, DatagramPacket packet) {
		
		
		for(int i = 1; i < playersThatCameBefore.length; i++){
			String[] perPlayer = playersThatCameBefore[i].split(PlayerMP.P_DELIMITER);
			String name = perPlayer[0].substring(1);
			String xForm = perPlayer[1];
			String yForm = perPlayer[2];
			
		//	if(i == 1)
				//name = name.substring(1);
			if(i == playersThatCameBefore.length - 1){
				if(name.charAt(name.length() - 1) == '[')
					name = name.substring(0, name.length()-1);
			}
		
			//if(name.charAt(0) == ' ')
				//name = name.substring(1);
			
			xForm = xForm.replace("[", "").replace("]", "").trim();
			yForm = yForm.replace("[", "").replace("]", "").trim();

			int x = Integer.parseInt(xForm);
			int y = Integer.parseInt(yForm);
			if(!name.equals(this.username)){
				game.addEntity(new PlayerMP(game.level, name, x, y, packet.getAddress(), packet.getPort()));
				//System.out.println("Adding to " + this.username + "'s list: " + name);
			}

		}
		//System.out.println();

	}

	private void handleMove(String name, int x, int y) {
		if(name.equals(this.username))
			return;
		Object[] data = this.getPlayerFromName(name);
		
		if(data == null){
		/*	game.addEntity(new PlayerMP(name, x, y));
			System.err.println("RECIEVED A NEW PLAYER!");*/
		}
		
	
		
		else{
			PlayerMP p = (PlayerMP)data[0];
			int index = (Integer)data[1];
			p.x = x;
			p.y = y;
			game.setEntity(p, index);
			//System.out.println(this.username + " recieved moved: " + name);
		}
	}

	public void close() {
		try {
			this.sendData(PacketType.DISCONNECT, socket, "", host, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	}

	/**
	 * (PacketType)
	 * String[] partsMessage
	 * DatagramPacket
	 * @return
	 * @throws IOException
	 */
	private Object[] recieveData() throws IOException {
		Object[] data = new Object[3];
		byte[] buffer = new byte[5000];
		DatagramPacket recievePacket = new DatagramPacket(buffer, buffer.length);
		if(socket.isClosed())
		{
			game.displayMessage(this.username + "'s socket is closed");
			return null;
		}
		socket.receive(recievePacket);
		String rawMessage = this.getMessage(buffer);
		String[] messageParts = rawMessage.split(DELIMETER);
		data[0] = this.lookupPacket(messageParts[0]);
		data[1] = messageParts;
		data[2] = recievePacket;
		//System.out.println("Server responded: " + rawMessage);
		return data;
	}

	/**
	 * Only to be run once:
	 * 
	 * @throws IOException
	 */
	private void sendJoinRequest() throws IOException {
		this.sendData(PacketType.LOGIN, socket, spawnX + this.DELIMETER + spawnY, host, serverPort);
	}
	
	public void sendMoveData(int x, int y){
		try {
			this.sendData(PacketType.MOVE, socket, x + this.DELIMETER + y, host, serverPort);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message){
			try {
				this.sendData(PacketType.MESSAGE, this.socket, message, this.host, serverPort);
			} catch (IOException e) {
				e.printStackTrace();
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
	private void sendData(PacketType packetType, DatagramSocket socket,
			String message, InetAddress host, int serverPort)
			throws IOException {
		String actualMessage = packetType.getID() + DELIMETER + username
				+ DELIMETER + message;
		byte[] data = actualMessage.getBytes();
		DatagramPacket request = new DatagramPacket(data, data.length, host,
				serverPort);
		socket.send(request);
		// System.out.println("Client Sent to server" + message);
	}

	private String getMessage(byte[] array) {
		return new String(array);
	}

	private byte[] getBytes(String m) {
		return m.getBytes();
	}
	
	/**
	 * PlayerMP is 1st, Integer index in second
	 * @param ipAddress
	 * @param port
	 * @return
	 */
	public Object[] getPlayerFromName(String name){
		Object[] data = new Object[2];
		for(int i = 0; i < game.getEntities().size(); i++){
			Object entity = game.getEntities().get(i);
			PlayerMP p = null;
			if(entity instanceof PlayerMP)
				p = (PlayerMP)entity;
			if(p != null && p.getName().equals(name)){
				data[0] = p;
				data[1] = i;
				return data;
			}
		}
		
		return null;
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
}
