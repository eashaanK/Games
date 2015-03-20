package com.infagen2D.network;

public class Packet02Move extends Packet{

	private String username;
	private int x, y;
	
	private int numSteps = 0;
	private boolean isMoving;
	private int movingDir = 1;

	private final String DILIMETER  = ",";
	/**
	 * reveiving data
	 * @param data
	 */
	public Packet02Move(byte[] data) {
		super(02);
		String[] dataArray = readData(data).split(DILIMETER);
		this.username = dataArray[0];
		this.x = Integer.parseInt(dataArray[1]);
		this.y = Integer.parseInt(dataArray[2]);
		this.numSteps = Integer.parseInt(dataArray[3]);
		this.isMoving = Integer.parseInt(dataArray[4]) == 1;
		this.movingDir = Integer.parseInt(dataArray[5]);

	}
	
	/**
	 * Sending data
	 * @param username
	 */
	public Packet02Move(String username, int x, int y, int numSteps, boolean isMoving, int movingDir) {
		super(02);
		this.username = username;
		this.x = x;
		this.y = y;
		this.numSteps = numSteps;
		this.isMoving = isMoving;
		this.movingDir = movingDir;
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
		return ("02" + this.username + DILIMETER + this.x + DILIMETER + this.y + DILIMETER + this.numSteps + DILIMETER + (this.isMoving ? 1 : 0) + DILIMETER + this.movingDir).getBytes();
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
	
	@Override
	public String toString(){
		return this.username + " has moved to: (" + x + ", " + y + ")";
	}

	public int getNumSteps() {
		return numSteps;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public int getMovingDir() {
		return movingDir;
	}
}
