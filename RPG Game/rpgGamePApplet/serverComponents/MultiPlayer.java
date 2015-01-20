package serverComponents;

import processing.core.PApplet;
import processing.core.PImage;

public class MultiPlayer {
	
	float x, y, width, height, health;
	String name;
	PImage currentImage;
	
	public MultiPlayer(float x, float y, float width, float height, String name, int[] playerImagePixels, float health){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.name = name;
		this.currentImage = new PImage(40, 40);
		this.currentImage.loadPixels(); //TAKE THESE @ OUT
		this.currentImage.pixels = playerImagePixels;
		this.currentImage.updatePixels();
		this.health = health;
	}
	
	public void draw(PApplet parent){
		parent.image(this.currentImage, this.x, this.y);
		parent.textSize(10);
		parent.fill(0, 0, 0);
		parent.text(name, x - width/2, y - 15);
	}
	
	public void setCurrentPixels(int[] pixels){
		this.currentImage.loadPixels(); //TAKE THIS OUT
		this.currentImage.pixels = pixels;
		this.currentImage.updatePixels();
	}

}
