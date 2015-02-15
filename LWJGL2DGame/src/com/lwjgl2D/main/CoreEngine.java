package com.lwjgl2D.main;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

import com.lwjgl2D.entities.implementations.Player;
import com.lwjgl2D.entities.interfaces.AbstractGameObject;

public class CoreEngine {
	
	public static ArrayList<AbstractGameObject> gameObjects = new ArrayList<AbstractGameObject>();

	
	public static void main(String[] args){
		Game game = new Game();
		
		DisplayManager.createDisplay();
		
		CoreEngine.initOPENGL();
		
		game.init();
		
		DisplayManager.setTime();
		
		while(!Display.isCloseRequested()){
			updateOPENGL();
			float delta = DisplayManager.updateDelta();
			game.update(delta);
			for(AbstractGameObject g : gameObjects){
				g.update(delta);
				g.draw();
				
			}
			DisplayManager.updateDisplay();
		}
		
		DisplayManager.closeDisplay();
		game.close();
	}
	
 
	
	public static void add(AbstractGameObject g){
		CoreEngine.gameObjects.add(g);
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
