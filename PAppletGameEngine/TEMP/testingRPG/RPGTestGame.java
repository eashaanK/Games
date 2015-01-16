package testingRPG;

import processing.core.PApplet;
import processing.core.PVector;

public class RPGTestGame extends PApplet{

	PVector player, speed;
	
	public void setup(){
		size(800, 800);
		player = new PVector(0, 0);
		speed = new PVector(1, 1);
	}
	
	public void draw(){
		background(255, 255, 255);
		
		
		
		//draw everything relative to world
		g.translate(-player.x + width / 2, -player.y + height / 2);
		fill(0, 255, 0);
		rect(0, 0, width, height);
		
		fill(1, 1, 255);
		rect(10, 10, 100, 20);
		text("10, 10", 10, 10);
		
		
		//draw everything relative to screen
		fill(255, 0, 0);
		rect(player.x, player.y, 50, 50);
		if(keyPressed){
			String s = key + "";
			s = s.toLowerCase();
			char aK = s.charAt(0);
			switch(aK){
			case 'w':
				player.y -= speed.y;
				break;
			case 'a':
				player.x -= speed.x;
				break;
			case 's':
				player.y += speed.y;
				break;
			case 'd':
				player.x += speed.x;
				break;
			}
		}
			
	}
}
