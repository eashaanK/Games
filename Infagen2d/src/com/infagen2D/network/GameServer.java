package com.infagen2D.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.infagen2D.core.Game;
import com.infagen2D.entities.PlayerMP;
import com.infagen2D.network.Packet.PacketTypes;

public class GameServer extends Thread {
	private DatagramSocket socket;
	private Game game;
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();

	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(1331);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * String message =new String(packet.getData());
			 * System.out.println("Server recieved: " + message);
			 * if(message.trim().equalsIgnoreCase("ping")){
			 * System.out.println("Server is returning pong...");
			 * this.sendData("pong".getBytes(), packet.getAddress(),
			 * packet.getPort()); }
			 */
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
	}

	private void parsePacket(byte[] data, InetAddress address, int port) {
		String message = new String(data).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 2));
		Packet packet = null;
		switch (type) {
		default: 
		
		case INVALID:
			break;

		case LOGIN:
			packet = new Packet00Login(data);// recieve data
			System.out.println("[" + address.getHostAddress() + ": " + port + "]" + ((Packet00Login)packet).getUsername() + " has connected to Server");
			PlayerMP player = new PlayerMP(game.level, ((Packet00Login)packet).getUsername(), 100, 100, address, port);// otherPlayer;
			this.addConnection(player, (Packet00Login) packet);
			break;

		case DISCONNECT:
			packet = new Packet01Disconnect(data);// recieve data
			System.out.println("[" + address.getHostAddress() + ": " + port + "]" + ((Packet01Disconnect)packet).getUsername() + " has left Server");
			this.removeConnection((Packet01Disconnect) packet);
			break;

		}
	}
	public void addConnection(PlayerMP player, Packet00Login packet) {
		boolean alreadyConnected = false;
		for(PlayerMP p: this.connectedPlayers){
			if(player.getName().equalsIgnoreCase(p.getName())){ //dealing with player already in game
				if(p.ipAddress == null){
					p.ipAddress = player.ipAddress;
				}
				
				if(p.port == -1){
					p.port = player.port;
				}
				
				alreadyConnected = true;
			}else{
				sendData(packet.getData(), p.ipAddress, p.port);
				packet = new Packet00Login(p.getName());
				this.sendData(packet.getData(), player.ipAddress, player.port); //this sends the 2nd player data about previous player
			}
		}
		if(!alreadyConnected){
			this.connectedPlayers.add(player);
			//packet.writeData(this);
		}
	}
	
	public void removeConnection(Packet01Disconnect packet) {
		//PlayerMP player = this.getPlayerMP(packet.getUsername());
		this.connectedPlayers.remove(this.getPlayerMPIndex(packet.getUsername()));
		packet.writeData(this); //this basically goes in the packet class and tells server again to send data to all clients
	}
	
	public PlayerMP getPlayerMP(String username){
		for(PlayerMP player: this.connectedPlayers){
			if(player.getName().equals(username)){
				return player;
			}
		}
		return null;
	}
	
	public int getPlayerMPIndex(String username){
		int index = 0;
		for(PlayerMP player: this.connectedPlayers){
			if(player.getName().equals(username)){
				break;
			}
			index++;
		}
		return index;
	}


	public void sendData(byte[] data, InetAddress ipAddress, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length,
				ipAddress, port);
		try {
			this.socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendDataToAllClients(byte[] data) {
		for (PlayerMP p : this.connectedPlayers) {
			sendData(data, p.ipAddress, p.port);
		}
	}
}
