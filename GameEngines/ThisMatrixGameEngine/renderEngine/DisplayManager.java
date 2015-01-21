package renderEngine;

import org.lwjgl.opengl.Display;

public class DisplayManager {

	public static final int WIDTH = 1280, HEIGHT = 720;
	
	public static void createDisplay(){
		Display.setDisplayMode(WIDTH, HEIGHT);
	}
	
	public static void updateDisplay(){
		
	}
	
	public static void closeDisplay(){
		
	};
}
