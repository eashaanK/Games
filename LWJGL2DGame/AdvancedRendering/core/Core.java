package core;

import org.lwjgl.opengl.Display;

import window.Window;

public class Core {

	public static void main(String[] args){
		Window.createWindow();
		
		Core.initOpenGL();
		
		
		while(!Display.isCloseRequested()){
			updateOpenGL();
			
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		Window.close();
	}

	private static void updateOpenGL() {
		
	}

	private static void initOpenGL() {
		
	}
}
