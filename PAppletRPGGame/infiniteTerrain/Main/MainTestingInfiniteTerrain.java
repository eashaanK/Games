package Main;

import processing.core.PApplet;

public class MainTestingInfiniteTerrain extends PApplet{

	World world;
	
	public void setup(){
		size(800, 800);
		world =  new World(new Player(0, 0, 10, 10));
	}
	
	public void draw(){
		background(255,255, 255);
		world.render(this);
		world.update();
	}
}
