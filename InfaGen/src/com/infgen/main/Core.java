package com.infgen.main;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

import com.infgen.helpers.Draw;


public class Core {

	
	
	public static void main(String[] args){
		DM.createWindow();
		
		initOpengl();
		
		DM.assignCurrentFrame();
		while(!Display.isCloseRequested()){
			updateOpengl();
			
			DM.updateDelta();
			//game.update()
			DM.updateDisplay();
		}
		DM.closeDisplay();
	}

	private static void updateOpengl() {
		GL11.glClear(GL_COLOR_BUFFER_BIT);
		GL11.glClearColor(0, 0, 0, 1);
		
	}
	
	private static void initOpengl() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0,  DM.WIDTH, DM.HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);	
	}
	
	
	
}
