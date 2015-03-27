package conponent;

import processing.core.PApplet;

public class Tester extends PApplet{

	GameObject object;
	
	public void setup(){
		size(500, 500);
		object = new GameObject("Testing Object");
		object.addComponent(ComponentType.TRANSFORM, new Transform());
	}
	
	public void draw(){
		background(0, 0, 0);
		
		object.update(0.2f);
		object.render(this);
	}
	

}
