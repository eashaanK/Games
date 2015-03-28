package com.infagen2D.components;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.infagen2D.core.Game;

public class WindowHandler implements WindowListener{

	private final Game game;
	
	public WindowHandler(Game game){
		this.game = game;
		this.game.frame.addWindowListener(this);
	}
	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	/**
	 * starts closing
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		//Packet01Disconnect packet = new Packet01Disconnect(this.game.player.getName());
		//packet.writeData(this.game.socketClient);
		game.socketClient.close();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	/**
	 * minimized
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	/**
	 * unminimized
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

}
