package infagen_entity;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Entity {
	protected Transform transform;
	protected String name;
	protected float health;
	
	public Entity(String name, float x, float y, float w, float h){
		transform = new Transform(new PVector(x, y), w, h);
		this.name = name;
		health = 100;
	}
	
	public String getName(){
		return name;
	}
	
	public float getX(){
		return transform.position.x;
	}
	
	public float getY(){
		return transform.position.y;
	}
	
	public void setX(float x){
		this.transform.position.x = x;
	}
	
	public void setY(float y){
		this.transform.position.y = y;
	}
	
	public float getHealth(){
		return health;
	}
	
	public void setHealth(float h){
		this.health = h;
	}
	
	public abstract boolean isAlive();
	public abstract void attack(Entity e);
	public abstract void takeDamage(float d);
	public abstract void render(PApplet parent);
	public abstract void update(float delta);
	public abstract void onClose();
	
	public Transform getTransform(){
		return transform;
	}
}
