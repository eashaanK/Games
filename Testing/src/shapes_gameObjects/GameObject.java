package shapes_gameObjects;

import helpers.DrawHelp;

import java.awt.Color;

import processing.core.PApplet;
import shapes_controls.Controls;
import shapes_controls.Key;

public class GameObject {

	public static int playerSize = 40;
	protected float x, y, a;
	protected PApplet parent;
	protected Color c;
	protected float speed = 2;
	protected String name;
	
	public GameObject(PApplet parent, float x, float y, float a, Color c, String name){
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.c = c;
		this.name = name;
	}
	
	public void draw(){
		parent.pushMatrix();
		parent.fill(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
		parent.translate(x, y);
		parent.rotate(a);
		this.parent.rect(0, 0, playerSize, playerSize);
		parent.popMatrix();
		
		parent.textSize(10);
		parent.fill(0, 0, 0);
		parent.text(toString(), x - 50, y - 20);
	}
	
	public void update(Controls controls){
		if(controls.keyHeld(Key.W))
		{
			y-=speed;
		}
		
		if(controls.keyHeld(Key.A))
		{
			x -= speed;
		}
		
		if(controls.keyHeld(Key.S))
		{
			y+=speed;
		}
		
		if(controls.keyHeld(Key.D))
		{
		 x += speed;
		}
	}
	
	public float X(){
		return x;
	}
	
	public float Y(){
		return y;
	}
	
	public float A(){
		return a;
	}
	
	public Color C(){
		return c;
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return  "(" + x + ", " + y + ") A: " + a + " " + name;
	}
	
	public PApplet getParent(){
		return parent;
	}
	
}
