package com.infagen2D.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import com.infagen2D.core.Game;
import com.infagen2D.entities.PlayerMP;
import com.infagen2D.level.Level;
import com.infagen2D.network.Packet.PacketTypes;

public class GameClient extends Thread{

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Game game;
	
	public GameClient(Game game, String ipAddress){
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run(){
		while(true){
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length); 
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			this.handleLogin((Packet00Login) packet, address, port);
			break;

		case DISCONNECT:
			//TODO: Make screen notice appear
			packet = new Packet01Disconnect(data);// recieve data
			System.out.println("[" + address.getHostAddress() + ": " + port + "]" + ((Packet01Disconnect)packet).getUsername() + " has left game");
			game.level.removePlayerMP(((Packet01Disconnect)packet).getUsername());
			break;
		case MOVE:
			packet = new Packet02Move(data);
		//	System.out.println(((Packet02Move)packet).toString());
			this.handleMove(((Packet02Move)packet));
			break;

		}
	}
	
	private void handleLogin(Packet00Login packet , InetAddress address,  int port){
		//TODO: Make screen notice appear
		
		System.out.println("[" + address.getHostAddress() + ": " + port + "]" + packet.getUsername() + " has joined the game"); 
		PlayerMP player = new PlayerMP(game.level, packet.getUsername(), packet.getX(), packet.getY(), address, port, packet.getSeed());// otherPlayer;
	
	
		
		game.level.addEntity(player);
	
	}
	
	private void handleMove(Packet02Move packet) {
		this.game.level.movePlayer(packet.getUsername(), packet.getX(), packet.getY(), packet.getNumSteps(), packet.isMoving(), packet.getMovingDir());
	}
	
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
