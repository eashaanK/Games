package com.infagen.gameObject;

import org.lwjgl.util.vector.Vector3f;

/**
 * pitch = rotX
 * yaw = rotY
 * roll = rotZ
 * @author eashaan
 *
 */
public class Camera {

	private Transform transform;
	
	public Camera(Transform transform){
		this.transform = transform;
	}
	
	public Camera(){
		this(new Transform(new Vector3f(0, 0, 0), 0, 0, 0, 1));
	}
	
	public Transform getTransform(){
		return this.transform;
	}
	
	public void setTransform(Transform t)
	{
		this.transform = t;
	}
	
	public String toString(){
		return "Camera: " + this.transform.toString();
	}
}
