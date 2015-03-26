package com_components;

import org.lwjgl.util.vector.Vector3f;

public class Transform implements Component{

	private Vector3f pos, rot;
	private float scale;
	
	public Transform(float x, float y, float z, float rotX, float rotY, float rotZ, float scale){
		this.pos = new Vector3f(x, y, z);
		this.rot = new Vector3f(rotX, rotY, rotZ);
		this.scale = scale;
	}
	
	public Transform(){
		this(0, 0, 0, 0, 0, 0, 1);
	}
	
	public Transform(float x, float y, float z){
		this(x, y, z, 0, 0, 0, 1);
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getRot() {
		return rot;
	}

	public void setrot(Vector3f rot) {
		this.rot = rot;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public float getZ(){
		return pos.z;
	}
	
	public void translate(float dx, float dy, float dz, float delta){
		this.pos.x += dx * delta;
		this.pos.y += dy * delta;
		this.pos.z += dz * delta;
	}

	public void rotate(float dx, float dy, float dz, float delta){
		this.rot.x += dx * delta;
		this.rot.y += dy * delta;
		this.rot.z += dz * delta;
	}

	@Override
	public String toString(){
		return pos.toString() + " scale: " + scale;
	}
}
