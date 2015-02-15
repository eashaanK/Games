package com.lwjgl2D.entities.interfaces;

import java.awt.Rectangle;

public interface GameObjectInterface {

	public void draw();
	public void update(float delta);
	public void setPos(float x, float y);
	public void setX(float x);
	public void setY(float y);
	public void setWidth(float w);
	public void setHeight(float h);
	public void setName(String name);
	public void setHitbox(Rectangle rect);
	
	public float getX();
	public float getY();
	public float getW();
	public float getH();
	public boolean intersects(GameObjectInterface g);
	public String getName();
	public Rectangle getHitbox();
	
	public String toString();
}
