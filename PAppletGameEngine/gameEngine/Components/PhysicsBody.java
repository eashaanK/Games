package Components;

import java.awt.Rectangle;

import processing.core.PVector;

public class PhysicsBody extends Rectangle{
	
	
	protected int v = 0;
	protected int t = 1;
	protected int g = 1;
	protected boolean enabled = true;	
	
	public PhysicsBody(){
		
	}
	
	public void updatePhysics(PVector pos){
		if(this.enabled){
			v += g * t;
			pos.y += v * t;
		}
	}

}
