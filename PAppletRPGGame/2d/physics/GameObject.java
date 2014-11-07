package physics;

import java.awt.Color;
import java.awt.Rectangle;

import processing.core.PApplet;

public class GameObject {
	
	public Vector2D position;
	public Size2D size;
	public String name;
	private PApplet parent;
	public Color color;
	public boolean destroy = false;
	public PhysicsBody physicsBody;
	
	public GameObject(PApplet p, Color c, float x, float y, float w, float h){
		this.parent = p;
		this.color = c;
		this.position = new Vector2D(x, y);
		this.size = new Size2D(w, h);
		this.physicsBody = new PhysicsBody();
	}
	
	public GameObject(PApplet p, Color c, float x, float y, float s){
		this.parent = p;
		this.color = c;
		this.position = new Vector2D(x, y);
		this.size = new Size2D(s, s);
		this.physicsBody = new PhysicsBody();
	}
	
	public void draw(){
		if(!destroy)
		{
			parent.fill(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
			parent.rect(position.x, position.y, size.width, size.height);
		}
	}
	
	public void update(){
		if(!destroy){
			//do something
			this.physicsBody.update();
			this.position.x = physicsBody.currentX;
			this.position.y = physicsBody.currentY;

		}
			
	}
}
