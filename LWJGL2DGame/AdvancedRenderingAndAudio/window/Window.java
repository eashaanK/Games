package window;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final int FPS_CAP = 60;
	public static final String NAME = "Advanced Rendering";
	
	private static long lastFrame;
	private static float delta;
	
	public static void createWindow(){
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(NAME);
			Display.create();
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		System.out.println("Your OPENGL version is: " + GL11.glGetString(GL11.GL_VERSION));
	}
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void closeWindow(){
		Display.destroy();
		AL.destroy();
	}
	
	public static long getTime(){
		return (Sys.getTime() * 1000)/Sys.getTimerResolution();
	}
	
	public static void setTime(){
		lastFrame = getTime();
	}
	
	public static void updateDelta(){
		long currentFrame = getTime();
		delta = (float)(currentFrame - lastFrame);
		lastFrame = currentFrame;
	}
	
	public static float getDelta(){
		return delta;
	}
}
