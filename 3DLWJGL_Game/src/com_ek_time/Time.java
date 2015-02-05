package com_ek_time;

public class Time {

	private static double delta;
	
	public static long getTime(){
		return System.nanoTime();
	}
	
	public static double getDelta(){
		return delta;
	}
	
	public static void setDelta(double delta){
		Time.delta = delta;
	}

}
