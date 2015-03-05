package com.infagen.gameObject;

import org.lwjgl.util.vector.Vector3f;

public class Transform {

	protected Vector3f position;
	
	protected float rotX, rotY, rotZ;
	protected float scale;
	
	public Transform(Vector3f position, float rotX, float rotY, float rotZ,
			float scale) {
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setX(float x){
		this.position.x = x;
	}
	
	public void setY(float y){
		this.position.y = y;
	}
	
	public void setZ(float z){
		this.position.z = z;
	}
	
	public float getX(){
		return this.position.x;
	}
	
	public float getY(){
		return this.position.y;
	}
	
	public float getZ(){
		return this.position.z;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void moveBy(float dx, float dy, float dz){
		this.position .x+= dx;
		this.position .y+= dy;
		this.position .z+= dz;
	}
	
	public void rotateBy(float dx, float dy, float dz){
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
	
	public String toString(){
		return "p: " + this.position.toString() + " r: (" +  rotX + ", " + rotY + ", " + rotZ + ") s: " + scale;
	}
	
}
