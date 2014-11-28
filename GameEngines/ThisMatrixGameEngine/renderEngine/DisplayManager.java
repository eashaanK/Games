package renderEngine;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final int FPS = 120;
	
	public static void createDisplay(){
		ContextAttribs attribs = new ContextAttribs(3,2);
		attribs.withForwardCompatible(true);
		attribs.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("First DISPLAY");
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Could not create a Display", "Error in Game", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT); //bottom left
	}
	
	public static void updateDisplay(){
		Display.sync(FPS);
		Display.update();
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}

}
