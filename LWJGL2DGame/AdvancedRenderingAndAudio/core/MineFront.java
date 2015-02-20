package core;

import org.lwjgl.opengl.Display;

import camera.Camera;
import window.Window;

public class MineFront {

	public static void main(String[] args){
		Window.createWindow();
		
		Camera camera = new Camera();
		
		while(!Display.isCloseRequested()){
			
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		Window.closeWindow();
	}

	
}
