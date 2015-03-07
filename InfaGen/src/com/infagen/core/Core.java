package com.infagen.core;

import org.lwjgl.opengl.Display;

import com.infagen.loaders.Loader;
import com.infagen.renderEngines.DisplayManager;
import com.infagen.renderEngines.MasterRenderer;
import com.infagen.renderEngines.Time;

public class Core {

	public static final int WIDTH = 1280, HEIGHT = 800;
	public static final String TITLE = "Infagen";
	public static final int FPS_CAP = 120;
	
	private static Game game;
	
	public static void main(String[] args){
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();

		MasterRenderer masterRend = new MasterRenderer();
		
		game = new Game(loader);
		
		Time.initalize();
		while(!Display.isCloseRequested()){
			Time.updateDelta();
			//game logic
			game.update(masterRend);
			//render
			DisplayManager.updateDisplay();

		}
		masterRend.cleapUP();
		game.close();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
