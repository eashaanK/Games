package components;

import processing.core.PVector;

public class Transform {

	private PVector pos;
	private float width, height;
	private float rotation;
	public Transform(PVector pos, float width, float height, float rotation) {
		super();
		this.pos = pos;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
	}
	public PVector getPos() {
		return pos;
	}
	public void setPos(PVector pos) {
		this.pos = pos;
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
	public float getRotation() {
		return rotation;
	}
	public void setRotation(float rotation) {
		this.rotation = rotation;
	}
	
	public float X(){
		return this.pos.x;
	}
	
	public float Y(){
		return this.pos.y;
	}

	
	public void move(float dx, float dy, float delta){
		this.pos.x += dx * delta;
		this.pos.y += dy * delta;
	}
	
	@Override
	public String toString() {
		return "Transform [pos=" + pos + ", width=" + width + ", height="
				+ height + ", rotation=" + rotation + "]";
	}
	
	
}
