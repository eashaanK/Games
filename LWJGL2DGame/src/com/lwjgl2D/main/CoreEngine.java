package com.lwjgl2D.main;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;

public class CoreEngine {

	public static void main(String[] args){
		Game game = new Game();
		
		DisplayManager.createDisplay();
		
		CoreEngine.initOPENGL();
		
		game.init();
		
		DisplayManager.setTime();
		
		while(!Display.isCloseRequested()){
			updateOPENGL();
			game.update();
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
		game.close();
	}
	
	private static void initOPENGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, DisplayManager.WIDTH, DisplayManager.HEIGHT, 0, 1, -1); //upper left is 0, 0
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);

	}
	
	private static void updateOPENGL(){
		glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT); // add | GL_DEPTH_BUFFER_BIT for 3D
		glClearColor(0, 0, 0, 1);

		//glColor3f()
	}
}
