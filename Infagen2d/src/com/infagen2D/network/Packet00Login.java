package com.infagen2D.network;

public class Packet00Login extends Packet{

	private String username;
	private int x , y, seed;
	private final String DILIMETER  = ",";

	/**
	 * reveiving data
	 * @param data
	 */
	public Packet00Login(byte[] data) {
		super(00);
		String[] dataArray = readData(data).split(DILIMETER);
		this.username = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
		this.seed = Integer.parseInt(dataArray[3]);	
	}
	
	/**
	 * Sending data. Created in client to send to server
	 * @param username
	 */
	public Packet00Login(String username, int x, int y, int seed) {
		super(00);
		this.username = username;
		this.x = x;
		this.y = y;
		this.seed = seed;
	}
	
	/**
	 * created in the game class
	 * @param username
	 * @param x
	 * @param y
	 */
	public Packet00Login(String username, int x, int y) {
		super(00);
		this.username = username;
		this.x = x;
		this.y = y;
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
		return ("00" + this.username + DILIMETER + x + DILIMETER + y + DILIMETER + seed).getBytes();
	}

	public String getUsername() {
		return this.username;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSeed() {
		return seed;
	}

	
}
