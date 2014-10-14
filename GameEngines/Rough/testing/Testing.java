package testing;

import processing.core.PApplet;

public class Testing extends PApplet
{

	float x, y, z, a = PI / 3;
	public void setup(){
		size(900, 900, OPENGL);
	}
	
	public void draw(){
		background(0);
		this.lights();
		
		a += 0.1;
		
		beginCamera();
		perspective();
		rotateY((float) Math.toRadians(1));
		endCamera();

		
		pushMatrix();
		translate(50, 50, 0);
		rotateY(a);
		box(45);
		popMatrix();
		
		printCamera();

		
	}
	
	public void keyPressed(){
		if(keyCode == UP || keyCode == 'w' || keyCode == 'W')
		{
			System.out.println("Now moving forward");
			z += 10;
		}

	}

}
