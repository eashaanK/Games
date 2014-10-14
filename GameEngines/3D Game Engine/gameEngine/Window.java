package gameEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	public static void createWindow(int w, int h, String t) {
		Display.setTitle(t);
		try {
			 PixelFormat pixelFormat = new PixelFormat();
			 ContextAttribs contextAttributes = new ContextAttribs(3,2).withProfileCore(true);
			
			Display.setDisplayMode(new DisplayMode(w, h));
			
			Display.create(pixelFormat, contextAttributes);
			
			Keyboard.create();
			Mouse.create();
			System.out.println("-Window: New window created");
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void render() {
		Display.update();
	}
	
	public static void dispose(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}

	public static boolean isCloseReqeusted() {
		return Display.isCloseRequested();
	}

	public static int getWidth() {
		return Display.getDisplayMode().getWidth();
	}

	public static int getHeight() {
		return Display.getDisplayMode().getHeight();

	}

	public static String getTitle() {
		return Display.getTitle();

	}
}
