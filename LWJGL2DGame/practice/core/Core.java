package core;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import org.lwjgl.util.glu.GLU;
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

import game.Game;

import org.lwjgl.opengl.Display;

import window.Window;

public class Core {

	public static void main(String[] args){
		Window.createWindow();
		
		Core.initOpenGL();
		
		Game game = new Game();

		
		Window.setTime();
		while(!Window.isCloseRequested()){
			Core.updateOpenGL();
			float delta = Window.getDelta();
			game.update(delta);
			Window.updateDisplay();
			Display.setTitle(Window.TITLE + " Delta: " + delta);
		}
	
		Window.closeDisplay();
		game.close();
	}

	private static void updateOpenGL() {
		glClear(GL_COLOR_BUFFER_BIT  | GL_DEPTH_BUFFER_BIT); // add | GL_DEPTH_BUFFER_BIT for 3D
		glClearColor(0, 0, 0, 1);
	}

	private static void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		//fov, aspect ratio (width / height), z near, z far
		GLU.gluPerspective((float) 30, Window.WIDTH / Window.HEIGHT, 0.001f, 100);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glEnable(GL_TEXTURE_2D);

	}
	
	
}
