package com.infagen2D.entities;

import com.infagen2D.components.Transform;

public abstract class Entity {

	private Transform transform;
	private String name;
	private float health;
	private boolean isActive = false;

	public Entity(int x, int y, int w, int h, String name){
		health = 100;
		this.transform = new Transform(x, y, w, h);
		this.name = name;
		isActive = false;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
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
	
	public void moveBy(int dx, int dy){
		if(this.isActive){
			this.transform.setX(this.transform.getX() + dx);
			this.transform.setY(this.transform.getX() + dy);
		}
	}
	
	public boolean isActive(){
		return this.isActive;
	}
	
	public void setIsActive(boolean iA){
		this.isActive = iA;
	}
	
	public void takeDamage(float damage){
		this.setHealth(this.getHealth() - damage);
	}
	
	public boolean isAlive(){
		return this.getHealth() > 0;
	}
	
	@Override
	public String toString(){
		return name + " " + this.transform.toString() + " health: " + this.getHealth();
	}
}
