package com.lwjgl2D.mulitplayer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class GameServer {
	
	private DatagramSocket socket;
	
	public GameServer(){
		try {
			this.socket = new DatagramSocket(1331);


		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		while(true){
			byte[] data = new byte[1024]; //data  being sent to and from the server
			DatagramPacket packet = new DatagramPacket(data, data.length); //this is gonna be to and from the server
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String message = new String(packet.getData());
			if(message != null)
				System.out.println("SERVER RECIEVED : " +  message);
		}
	}
	
	
	/**
	 * Sends the data to server
	 * @param data
	 */
	public void sendData(byte[] data){
		DatagramPacket packet = new DatagramPacket(data, data.length, serverIpAddress, 1331); //send data to this
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
