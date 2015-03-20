package com.infagen2D.components;

public class Ref {
	
	public static int getRandom(int min, int max){
		return (int)(Math.random() * (max - min) + min);
	}
	
	public static float getRandom(float min, float max){
		return (float)(Math.random() * (max - min) + min);
	}
}
