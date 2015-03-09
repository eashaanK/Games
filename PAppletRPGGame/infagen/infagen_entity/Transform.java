package infagen_entity;

import processing.core.PVector;

public class Transform {

	protected PVector position;
	
	protected float width, height;
	
	public Transform(PVector position, float width, float height) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
	}

	public PVector getPosition() {
		return position;
	}

	public void setPosition(PVector position) {
		this.position = position;
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

	public void moveBy(float dx, float dy){
		this.position.x += dx;
		this.position.y += dy;
	}
	public String toString(){
		return this.position.toString() + " w: " + width + " h: " + height; 
	}
}
