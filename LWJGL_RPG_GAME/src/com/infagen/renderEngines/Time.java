package com.infagen.renderEngines;

import org.lwjgl.Sys;

public class Time {

	protected static float delta;
	private static long lastFrame;
	private static float universeTimeConstant = 1f/100f;
	
	private static long getTime(){
		return (Sys.getTime() * 1000)/Sys.getTimerResolution();
	}
	
	public static void updateDelta(){
		long currentFrame = getTime();	
		delta = (float)(currentFrame - lastFrame) * universeTimeConstant;
		lastFrame = currentFrame;
		
	}
	
	public static void initalize(){
		lastFrame = getTime();
	}
	
	
	public static float getDelta(){
		return delta;
	}
	
	public static void setUniverseTimeConstant(float constant){
		Time.universeTimeConstant = constant;
	}
}
