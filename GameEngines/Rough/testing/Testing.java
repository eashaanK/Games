package testing;

import processing.core.PApplet;

public class Testing extends PApplet
{

	public void setup(){
		size(900, 900, OPENGL);
	}
	
	public void draw(){
		background(255, 255, 255);
		pushMatrix();
		lights();
		//translate(mouseX, mouseY, 0);
		beginShape(POINTS);
		fill(255, 0, 0);
		vertex(30, 20);
		vertex(85, 20);
		vertex(85, 75);
		vertex(30, 75);
		endShape();
		popMatrix();
	}

}
