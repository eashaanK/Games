package window;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL11.*;

public class Window {

	public static final int WIDTH = 1280, HEIGHT = 800;
	public static String TITLE = "Practice";
	public static final int FPS_CAP = 120;
	
	private static long lastFrame;
	private static float delta;
	
	public static void createWindow(){		
		/*ContextAttribs attribs = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);*/
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			//Display.create(new PixelFormat(), attribs);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		System.out.println("OPENGL version: " + GL11.glGetString(GL11.GL_VERSION));
	}
	
	public static void setTime(){
		Window.lastFrame = Window.getTime();
	}
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
		
		long currentTime = getTime();
		delta = (float)(currentTime - lastFrame);
		lastFrame = currentTime;
	}
	
	public static float getDelta(){
		return delta;

	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static void closeDisplay(){
		Display.destroy();
	}
	
	public static long getTime(){
		return (Sys.getTime() * 1000 ) / Sys.getTimerResolution();
	}
}
