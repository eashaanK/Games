package engine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	public static void createWindow(int width, int height, String title){
		try {
			ContextAttribs attribs = new ContextAttribs(3,2).withForwardCompatible(true).withProfileCore(true);
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create(new PixelFormat(), attribs);
			Keyboard.create();
			Mouse.create();
			AL.create();
			Display.setVSyncEnabled(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		GL11.glViewport(0, 0, width, height);
	}
	
	public static void render(){
		Display.sync((int) MainComponent.FRAME_CAP); //MIGHT NOT WORK
		Display.update();
	}
	
	public static void cleanUp()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
		AL.destroy();
	}
}
