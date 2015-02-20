package core;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import camera.Camera;
import camera.EulerCamera;
import window.Window;

public class MineFront {

	public static void main(String[] args){
		Window.createWindow();
		
		Camera camera = new EulerCamera();
		camera.applyPerspectiveMatrix();
		
		glInit();
		
		while(!Display.isCloseRequested()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			Window.updateDelta();
			
			
			
			camera.processKeyboard(Window.getDelta(), 5);
			camera.processMouse();

			System.out.println(camera.toString());
			
			Window.updateDisplay();
		}
		
		Window.closeWindow();
	}

	private static void glInit() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
	}

	
}
