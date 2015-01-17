package testingRPG;

import processing.core.PApplet;
import processing.core.PImage;

public class RPGTestGame extends PApplet {

	SpriteSheet sprite;

	public void setup() {
		size(800, 800);
		sprite = new SpriteSheet(this, "BlueBoySpriteSheet122X163.png", 40, 40,  0.1f);
	}
	
	
	public void draw() {
		background(255, 255, 255);
		sprite.draw(width/2, height/2);
		

		sprite.update();
		
		String temp = sprite.getCurrentRC();
		System.out.println(temp.substring(temp.length() - 30));
	}
	
	public void mousePressed(){
		sprite.chaneToUp();
	}

	
}