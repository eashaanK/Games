package com.infagen.gameObject;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	private Transform transform;
	private Vector3f color; //intensity
	public Light(Transform transform, Vector3f color) {
		this.transform = transform;
		this.color = color;
	}
	
	public Light(float rIntensity, float gIntensity, float bintensity) {
		this(new Transform(new Vector3f(0, 0, 0), 0, 0, 0, 1), new Vector3f(rIntensity, gIntensity, bintensity));
		this.transform = transform;
		this.color = color;
	}
	
	public Transform getTransform() {
		return transform;
	}
	public void setTransform(Transform transform) {
		this.transform = transform;
	}
	public Vector3f getColor() {
		return color;
	}
	public void setColor(Vector3f color) {
		this.color = color;
	}
	
	public String toString(){
		return "Light: " + transform.toString() + " intensity: " + color.toString(); 
	}
}
