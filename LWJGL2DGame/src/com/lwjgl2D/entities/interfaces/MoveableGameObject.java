package com.lwjgl2D.entities.interfaces;

public interface MoveableGameObject extends GameObjectInterface {

	public float getDX();
	
	public float getDY();
	
	public void setDX(float dx);
	
	public void setDY(float dy);
	
	public void setMass(float mass);
	
	public float getMass();
	
	public boolean canMove();
	
	public void setCanMove(boolean b);
	
	public String toString();
}
