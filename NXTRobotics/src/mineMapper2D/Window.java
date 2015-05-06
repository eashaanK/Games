package mineMapper2D;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * Is Responsible for the Window
 * @author eashaan
 *
 */
public class Window {

	public final static int WIDTH = 1000, HEIGHT = 800;
	public static final int FPS_CAP  = 120;
	public static final String TITLE = "Mine-Mapper by Richard, David and Eashaan";
	
	private static long lastFrameTime;
	private static float delta;


	
	public static void createWindow(){
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
			System.out.println("Your Opengl Version is: " + GL11.glGetString(GL11.GL_VERSION));
			GL11.glViewport(0, 0, WIDTH, HEIGHT);
			lastFrameTime = getCurrentTime();

		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDelta(){
		long currentFrameTime  = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime) / 1000f; //seconds
		lastFrameTime = currentFrameTime;
	}
	
	public static void updateDisplay(){
		
		Display.sync(FPS_CAP);
		Display.update();
		
		
	}
	
	public static float getDelta(){
		return delta;
	}
	
	public static void closeDisplay(){
		
		Display.destroy();
		
	}
	
	private static long getCurrentTime(){
		return Sys.getTime() * 1000/Sys.getTimerResolution(); //time in milliseconds
	}

}
