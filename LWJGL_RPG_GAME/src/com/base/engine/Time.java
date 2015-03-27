package com.base.engine;

public class Time {
	
	public static final long SECOND = 1000000000L;
	private static double delta;

	public static long getTime(){
		return System.nanoTime();
	}
	
	public static double getDelta(){
		return delta;
	}
	
	public static void setDelta(float delta){
		Time.delta = delta;
	}
}
