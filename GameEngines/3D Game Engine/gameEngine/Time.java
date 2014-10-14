package gameEngine;

public class Time {

	public static final long SECOND = 1000000000L;
	private static double delta;
	
	public static long getTime(){
		return System.nanoTime();
	}
	
	public static double getDelta(){
		return delta;
	}
	
	public static void setDelta(double de){
		Time.delta = de;
	}
	
	public String toString(){
		return "Time : " + getTime();
	}
}
