package testing;

import java.awt.Color;

import processing.core.PApplet;

public class Projectile extends Thing{

	public Projectile(PApplet parent, float x, float y, float width,
			float height, Color c, float mass) {
		super(parent, x, y, width, height, c, mass);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void updatePhysics(float gravity, int ground, float cof){
		float y = this.getY();
		float vy = this.getVy();
		float height = this.getHeight();
		
		if((y + height/2) < ground){
			vy+=gravity;
			y += vy;
		}
		else
		{
			y = ground - height /2;
			vy = 0;
		}
	}


}
