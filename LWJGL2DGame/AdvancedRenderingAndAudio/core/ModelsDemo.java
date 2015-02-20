package core;

import org.lwjgl.opengl.Display;

import window.Window;

public class ModelsDemo {

	public static void main(String[] args) {
		Window.createWindow();
		
		while(!Display.isCloseRequested()){
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		Window.closeWindow();
	}

}
