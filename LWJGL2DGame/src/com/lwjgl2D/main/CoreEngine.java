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

	private static float renderDistance2D;

	private static float updateDistance2D;
	
	public static void main(String[] args){
		Game game = new Game();
		
		DisplayManager.createDisplay();
		
		CoreEngine.initOPENGL();
		
		game.init();
		
		DisplayManager.setTime();
		
		renderDistance2D = 0;
		updateDistance2D = 0;
		
		int gameObjectsDrawnScreen = 0;
		int gameObjectsUpdatedScreen = 0;

		while(!Display.isCloseRequested()){
			updateOPENGL();
			float delta = DisplayManager.updateDelta();
			game.update(delta);
			for(AbstractGameObject g : gameObjects){
				if(g instanceof Player){
					g.update(delta); 
					gameObjectsUpdatedScreen++; 
				}

				else if(  !(g.getX() + g.getW() < 0 - updateDistance2D || g.getX() > DisplayManager.WIDTH + updateDistance2D || g.getY() + g.getH() < 0 - updateDistance2D || g.getY() > DisplayManager.HEIGHT + updateDistance2D)  ){
					
						g.update(delta); 
						gameObjectsUpdatedScreen++; 
					
				}
				if(  !(g.getX() + g.getW() < 0 - renderDistance2D || g.getX() > DisplayManager.WIDTH + renderDistance2D || g.getY() + g.getH() < 0 - renderDistance2D || g.getY() > DisplayManager.HEIGHT + renderDistance2D)  ){
					g.draw(); gameObjectsDrawnScreen++;}
			}
		//	System.out.println("Objects Drawn: " + gameObjectsDrawnScreen + "\t\t Objects Updated: " + gameObjectsUpdatedScreen);
			DisplayManager.updateDisplay();
			gameObjectsDrawnScreen = 0;
			gameObjectsUpdatedScreen = 0;
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
