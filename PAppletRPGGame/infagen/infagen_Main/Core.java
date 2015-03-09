package infagen_Main;

import processing.core.PApplet;

public class Core extends PApplet{
	
	private boolean titleSet = false;
	private static final String TITLE = "INFAGEN";
	
	public void setup(){
		size(800, 800);
		this.rectMode(CENTER);
		this.ellipseMode(CENTER);
		this.imageMode(CENTER);
		
	}
	
	public void draw(){
		background(0, 0, 0);
		
	}
}
