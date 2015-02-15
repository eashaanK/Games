package com.lwjgl2D.entities.interfaces;

import java.awt.Rectangle;

public abstract class AbstractGameObject implements GameObjectInterface{

	protected float x, y, width, height;
	protected String name;
	protected Rectangle hitbox = new Rectangle();
	
	public AbstractGameObject(float x, float y, float w, float h, String name){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.name = name;
	}
	
	@Override
	public abstract void draw();

	@Override
	public abstract void update(float delta);

	@Override
	public void setPos(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(float x) {
		this.x = x;
	}

	@Override
	public void setY(float y) {
		this.y = y;
	}

	@Override
	public void setWidth(float w) {
		this.width = w;
	}

	@Override
	public void setHeight(float h) {
		this.height = h;
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public float getW() {
		return width;
	}

	@Override
	public float getH() {
		return height;
	}

	@Override
	public boolean intersects(GameObjectInterface g) {
		hitbox.setBounds((int)x, (int)y, (int)width, (int)height);
		return hitbox.intersects(g.getX(), g.getY(), g.getW(), g.getH());
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setHitbox(Rectangle rect){
		this.hitbox = rect;
	}
	
	@Override
	public Rectangle getHitbox(){
		return hitbox;
	}
	
	@Override
	public String toString(){
		return this.getName() + " (" + this.getX() + ", " + this.getY() + ") " + this.getW() + " " + this.getH() + " " + this.hitbox.toString();
	}

}
