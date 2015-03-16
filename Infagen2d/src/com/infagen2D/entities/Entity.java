package com.infagen2D.entities;

import com.infagen2D.components.Transform;
import com.infagen2D.graphics.Screen;
import com.infagen2D.level.Level;

public abstract class Entity {

	protected Transform transform;
	protected String name;
	protected float health;
	protected boolean isActive = false;
	protected Level level;

	public Entity(int x, int y, int s, String name, Level level){
		this.level = level;
		health = 100;
		this.transform = new Transform(x, y, s);
		this.name = name;
		isActive = false;
	}

	public abstract void tick();
	public abstract void render(Screen screen);
	
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
	
	public Level getLevel(){
		return this.level;
	}
	
	public void setLevel(Level l){
		this.level = l;
	}
	
	@Override
	public String toString(){
		return name + " " + this.transform.toString() + " health: " + this.getHealth();
	}
}
