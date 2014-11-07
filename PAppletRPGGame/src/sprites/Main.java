package sprites;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet{

	Sprite sprite;
	public void setup(){
		size(700, 700);
		sprite = new Sprite(9,  1f);
		sprite.addImage(loadImage("images/GreenBoy/GreenBoyL1.png"));
		sprite.addImage(loadImage("images/GreenBoy/GreenBoyL2.png"));
		sprite.addImage(loadImage("images/GreenBoy/GreenBoyL3.png"));
	//	sprite.addImage(loadImage("images/GreenBoy/GreenBoyL4.png"));
		//sprite.addImage(loadImage("images/GreenBoy/GreenBoyL5.png"));
		//sprite.addImage(loadImage("images/GreenBoy/GreenBoyL6.png"));

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
