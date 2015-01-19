package gameObject;

import java.awt.Color;

import main.Game;
import helpers.DrawHelp;
import processing.core.PApplet;



public class Player extends GameObject{
	
	public static final float MAX_HEALTH = 10;

	public Player(PApplet p, String spritePath, float x, float y, String name) {
		super(p, spritePath, x, y, 40, 40, name);
		this.getSprite().changeSpeed(0.3f);
		this.setHealth(MAX_HEALTH);
		System.out.println(health);

	}
	
	public Player(PApplet p, int[] pixels, float x, float y, String name) {
		super(p, pixels, x, y, 40, 40, name);
		this.getSprite().changeSpeed(0.3f);
		this.setHealth(MAX_HEALTH);
		System.out.println(health);

	}
	
	@Override
	public void draw(){
		super.draw();
		//draw health bar
		float percentHealth = (health / MAX_HEALTH);
		int redColor = (int)(255 - 255 * percentHealth);
		int greenColor = (int)(255 * percentHealth);
		DrawHelp.drawFixedRect(parent, Color.white, 30, 740, 740, 12, pos.x, pos.y);
		if(redColor <= 255 && greenColor >= 0){
			DrawHelp.drawFixedRect(parent, new Color(redColor, greenColor, 0, 255), 31, 741, 739 * percentHealth, 11, pos.x, pos.y);
		}

	}
	

	
}
