package better_physics;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet{

	float vx,  vy = 0, gy = 9.81f, px, py, r = 25, t;
	boolean down = true;
	
	public void setup(){
		size(700, 700);
		this.frameRate(100);
		t = 0;
		px = width/2;
		this.ellipseMode(CENTER);
		this.rectMode(CENTER);
		this.shapeMode(CENTER);
		this.imageMode(CENTER);
	}
	
	public void draw(){
		background(255, 255, 255);
	
		physics();
		fill(255, 0, 0);
		ellipse(px, py, r/2, r/2);
		text(t, 100, 100);
		text(t, 100, 100);

	}
	
	public void physics(){
		t += 1/this.frameRate;
		
		if(py + r >= height)
		{
			down = false;
			vy *= -1;
		}
		
		if(py - r <= 0)
			down = true;
		
		vy += gy;
		py +=  vy*t;
	
	}
}
