package physics;

import java.awt.Color;

import processing.core.PApplet;

public class Main extends PApplet{

	GameObject g;
	public void setup(){
		size(500, 500);
		g = new GameObject(this, new Color(255, 0, 0, 100), width/2, height/2, 100);
		g.physicsBody.dynamic = true;
		g.physicsBody.timeInc = (1/this.frameRate);
		g.physicsBody.gravityY = 9.81f;
		g.physicsBody.gravityX = 0;

	}
	
	public void draw(){
		background(255, 255, 255);
		g.draw();
		g.update();
		
		if(g.position.y + g.size.height >= height)
		{
			System.out.println("LOL");
			g.physicsBody.gravityY *= -1;
		}
	}
	public static void main(String args[]) {
		PApplet.main(new String[] {/* "--present", */"physics.Main" });
	}
}
