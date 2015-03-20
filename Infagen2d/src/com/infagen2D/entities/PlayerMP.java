package com.infagen2D.entities;

import java.net.InetAddress;

import com.infagen2D.components.InputHandler;
import com.infagen2D.level.Level;

public class PlayerMP extends Player{
	
	public InetAddress ipAddress;
	public int port;

	/**
	 * For player from this computer
	 * @param level
	 * @param name
	 * @param x
	 * @param y
	 * @param input
	 * @param ipAddress
	 * @param port
	 */
	public PlayerMP(Level level, String name, int x, int y, InputHandler input, InetAddress ipAddress, int port) {
		super(level, name, x, y, input);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	/**
	 * For players from other computers
	 * @param level
	 * @param name
	 * @param x
	 * @param y
	 * @param ipAddress
	 * @param port
	 */
	public PlayerMP(Level level, String name, int x, int y, InetAddress ipAddress, int port) {
		super(level, name, x, y, null);
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
	@Override
	public void tick(){
		super.tick();
	}

}
