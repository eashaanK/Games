package com.infgen.main;

import org.lwjgl.opengl.Display;

public class Core {

	public static void main(String[] args){
		DM.createWindow();
		
		initOpengl();
		
		while(!Display.isCloseRequested()){
			//updateOpengl();
			
			DM.updateDelta();
			
			//game.update()
			DM.updateDisplay();
		}
		
		DM.closeDisplay();
	}

	private static void initOpengl() {
		
	}
	
	
}
