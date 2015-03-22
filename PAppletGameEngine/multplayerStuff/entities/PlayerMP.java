package entities;

import java.net.InetAddress;

public class PlayerMP {
	public String name;
	public InetAddress ipAddress;
	public int port;
	public float x, y;
	public static final String P_DELIMITER = "/";

	public PlayerMP(String name, InetAddress ipAddress, int port) {
		this(name, 0, 0, ipAddress, port);
	}
	
	public PlayerMP(String name, float x, float y) {
		this(name, x, y, null, -1);
	}
	
	public PlayerMP(String name, float x, float y, InetAddress ipAddress, int port) {
		this.name = name;
		this.ipAddress = ipAddress;
		this.port = port;
		this.x = x;
		this.y = y;
	}

	/**
	 * Server will use this method send newly joined peopl OLD_PERSON_WHO_WAS_ALREADY_HERE_BEFORE_U
	 */
	@Override
	public String toString(){
		return name + P_DELIMITER + x + P_DELIMITER + y;
	}
}
