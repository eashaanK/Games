package Components;

import game.Game;

import java.awt.Color;

import logic.Key;
import logic.PKeyboard;
import logic.Type;
import processing.core.PApplet;
import processing.core.PVector;

public class Entity {
	private PVector pos;
	private float angle;
	private float width, height;
	private PApplet parent;
	private Color color;
	
	public Entity(PApplet p, float x, float y, float w, float h, float angle, Color c){
		this.pos = new PVector(x, y);
		this.angle = angle;
		this.width = w;
		this.height = h;
		this.parent = p;
		this.color = c;
	
	}
	
	public void render(){
		//parent.stroke(0);
		//parent.strokeWeight(1);
		parent.noStroke();
		parent.fill(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
		parent.pushMatrix();
		parent.translate(pos.x, pos.y);
		parent.rotate(angle);
		parent.rect(0, 0, width, height);
		parent.popMatrix();
	}
	
	public void update(){
		if(PKeyboard.keyHeld(Key.W))
			this.pos.y--;
		if(PKeyboard.keyHeld(Key.D))
			this.pos.x++;
		if(PKeyboard.keyHeld(Key.S))
			this.pos.y ++;
		if(PKeyboard.keyHeld(Key.A))
			this.pos.x--;
	}
	
	public void setPosX(float x){
		this.pos.x = x;
	}
	
	public void setPosY(float y){
		this.pos.y = y;
	}
	
	public void setPos(float x, float  y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	public PVector getPos() {
		return pos;
	}

	public void setPos(PVector pos) {
		this.pos = pos;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public PApplet getParent() {
		return parent;
	}

	public void setParent(PApplet parent) {
		this.parent = parent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String toString(){
		return "Entity (" + pos.x + ", " + pos.y + ") angle: " + angle + " width: " + width + " height: " + height;
	}
}
