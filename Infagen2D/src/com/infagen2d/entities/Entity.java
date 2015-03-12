package com.infagen2d.entities;

import com.infagen2d.components.Transform;

public abstract class Entity
{

	private Transform transform;
	private String name;
	private float health;
	private boolean canMove;
	
	
	public Entity(String name, int x, int y, int w, int h){
		this.name = name;
		this.transform = new Transform(x, y, w, h);
		this.health = 100;
		this.canMove = true;
	}

	public Transform gettransform() {
		return transform;
	}

	public void settransform(Transform transform) {
		this.transform = transform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public boolean canMove() {
		return canMove;
	}

	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}
	
	public void moveBy(int dx, int dy){
		if(this.canMove){
			this.transform.setX(this.transform.getX() + dx);
			this.transform.setY(this.transform.getY() + dy);
		}
	}
	
	public String toString(){
		return name + " " + this.transform.toString() + " Health: " + health;
	}
}
