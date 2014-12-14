package testing;

import java.awt.Color;

import processing.core.PApplet;

public class Thing {

	private PApplet parent;
	private float x, y, width, height, angle;
	private Color color;
	private boolean isAlive;
	private float vy = 0, vx = 0, xAcc;
	private boolean addedImpulse;
	private float mass;

	public Thing(PApplet parent, float x, float y, float width, float height, Color c, float mass) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = c;
		this.isAlive = true;
		this.mass = mass;
	}
	
	public void draw(){
		if(isAlive){
			parent.fill(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
			parent.pushMatrix();
			parent.translate(x, y);
			parent.rotate(angle);
			parent.rect(0, 0, width, height);
			parent.popMatrix();
		}
	}
		
	public void updatePhysics(float gravity, int ground, float cof){
		if((this.y + height/2) < ground || this.addedImpulse){ //not on ground
			vy+=gravity;
			y += vy;
			this.addedImpulse = false;
		}
		else //on ground
		{
			y = ground - height /2;
			vy = 0;
			
			xAcc = -gravity * cof;
			if(vx > 0)
				this.vx += xAcc;
			else
				vx = 0;

		}
		
		this.x += vx;
		
		//System.out.println(vx);
	}
	
	public void update(){
		this.x+=vx;
		this.y+=vy;
	}
	
	public void addImpulse(float dvx, float dvy){
		this.vx += dvx;
		this.vy += dvy;
		this.addedImpulse = true;
	}
	
	public void follow(float tx, float ty, float v){
		lookAt(tx, ty);
		if(ty > this.y){
			vx = -parent.sin(angle) * v;
			vy = parent.cos(angle) * v;
		}
		else if(ty < this.y){
			vx = -parent.sin(angle) * v;
			vy = -parent.cos(angle) * v;
		}
		
	}
	
	public void lookAt(float tx, float ty){
		float deltaX = tx - x;
		float deltaY = -ty + y;
		
		angle = parent.atan(deltaX / deltaY); //in radians
	}
	
	public void kill(){
		isAlive = false;
	}

	public PApplet getParent() {
		return parent;
	}

	public void setParent(PApplet parent) {
		this.parent = parent;
	}

	public float getAngle(){
		return angle;
	}
	
	public void setAngle(float a){
		angle = a;
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}
	
	public float getMass(){
		return mass;
	}
	
	public void setMass(float m){
		this.mass = m;
	}

	public float getxAcc() {
		return xAcc;
	}

	public void setxAcc(float xAcc) {
		this.xAcc = xAcc;
	}
	
}
