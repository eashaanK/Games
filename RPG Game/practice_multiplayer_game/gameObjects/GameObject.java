package gameObjects;

import java.io.Serializable;

import processing.core.PVector;
import betterMultiplayerSetup.Client;

public class GameObject implements Serializable{

	private PVector pos;
	private float w, h;
	private String name;
	
	private final int id;

	public GameObject(float x, float y, float w, float h, String name, int id){
		this.pos = new PVector(x, y);
		this.w = w;
		this.h = h;
		this.name = name;
		this.id = id;
	}
	

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public float X(){
		return pos.x;
	}
	
	public float Y(){
		return pos.y;
	}
	
	public void setX(float x){
		this.pos.x = x;
	}
	
	public void setY(float y){
		this.pos.y = y;
	}
	
	public void moveBy(float xd, float yd){
		pos.x += xd;
		pos.y += yd;
	}
	
	public String toString(){
		return name + " " + pos.toString() + " w: " + w + " h: " + h + " id: " + this.id;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof GameObject){
			GameObject other = (GameObject)o;
			return (other.id == this.id);
		}
		
		
		return false;
	}
	
}
