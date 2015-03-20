package com.infagen2D.network;

public class Packet01Disconnect extends Packet{

	private String username;
	
	/**
	 * reveiving data
	 * @param data
	 */
	public Packet01Disconnect(byte[] data) {
		super(01);
		this.username = this.readData(data);
	}
	
	/**
	 * Sending data
	 * @param username
	 */
	public Packet01Disconnect(String username) {
		super(01);
		this.username = username;
	}

	@Override
	public void writeData(GameClient client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}

	@Override
	public byte[] getData() {
		return ("01" + this.username).getBytes();
	}

	public String getUsername() {
		return this.username;
	}

}
