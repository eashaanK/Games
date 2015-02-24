package com.infgen.main;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class DM {
	
	private static long currentFrame;
	private static float delta;

	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "InfaGen";
	public static final int FPS_CAP = 60;
	
	public static final float SCALE = 1;
	
	public static void createWindow(){
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle(TITLE);
			Display.create();
			AL.create();
			GL11.glViewport(0, 0, WIDTH, HEIGHT);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDisplay(){
		Display.sync(FPS_CAP);
		Display.update();
	}
	
	public static void closeDisplay(){
		Display.destroy();
		AL.destroy();
	}
	
	public static long getTime(){
		return (Sys.getTime() * 1000)/Sys.getTimerResolution();
	}
	
	public static float getDelta(){
		return delta;
	}
	
	public static void updateDelta(){
		long lastTime = getTime();
		delta = ((float)(lastTime - currentFrame)) / 1000f;
		currentFrame = lastTime;
	}
	
	public static void assignCurrentFrame(){
		DM.currentFrame = getTime();
	}
}
