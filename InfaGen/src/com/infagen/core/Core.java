package com.infagen.core;

import org.lwjgl.opengl.Display;

import com.infagen.renderEngines.DisplayManager;
import com.infagen.renderEngines.Time;

public class Core {

	public static final int WIDTH = 1280, HEIGHT = 800;
	public static final String TITLE = "Infagen";
	public static final int FPS_CAP = 120;
	
	private static Game game;
	
	public static void main(String[] args){
		DisplayManager.createDisplay();
		
		game = new Game();
		
		Time.initalize();
		while(!Display.isCloseRequested()){
			Time.updateDelta();
			//game logic
			game.update();
			//render
			DisplayManager.updateDisplay();

		}
		game.close();
		
		DisplayManager.closeDisplay();
	}
}
