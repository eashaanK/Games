package Main;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	public void setup() {
		size(700, 700, P3D);
		this.ellipseMode(CENTER);
		this.rectMode(CENTER);
		this.shapeMode(CENTER);
		this.imageMode(CENTER);
	}

	public void draw() {
		background(0);
		
		//perspective();
		
		if(mousePressed)
		{
			lights();
		}
		
		fill(255, 0, 0);
		box(mouseX, mouseY, 100,     0, 0, 0,    200);
	}
	
	private void box(float x, float y, float z, float rx, float ry, float rz, float s){
		pushMatrix();
		translate(x, y, z);
		rotateX(rx);
		rotateY(ry);
		rotateZ(rz);
		box(s);
		popMatrix();
	}
}
