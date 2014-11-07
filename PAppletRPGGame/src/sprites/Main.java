package sprites;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet{

	Sprite sprite;
	public void setup(){
		size(700, 700);
		sprite = new Sprite(9,  1f);
		sprite.addImage(loadImage("images/GreenBoyD1.png"));
		sprite.addImage(loadImage("images/GreenBoyD2.png"));
		sprite.addImage(loadImage("images/GreenBoyD3.png"));
		sprite.addImage(loadImage("images/GreenBoyD4.png"));
		sprite.addImage(loadImage("images/GreenBoyD5.png"));
		sprite.addImage(loadImage("images/GreenBoyD6.png"));

	}
	
	public void draw(){
		background(255, 255, 255);
		fill(255, 0, 0);
		textSize(30);
		sprite.update();
		image(sprite.nextImage(), width/2, height/2);
		this.imageMode(CENTER);
	}
}
