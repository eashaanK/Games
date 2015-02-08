package tM_entities;

import org.lwjgl.util.vector.Vector3f;

public class Light {

	private Vector3f pos;
	private Vector3f color;
	private Vector3f attentuation;
	
	public Light(Vector3f pos, Vector3f color) {
		this.pos = pos;
		this.color = color;
		attentuation = new Vector3f(1, 0, 0);
	}
	
	public Light(Vector3f pos, Vector3f color, Vector3f att) {
		this.pos = pos;
		this.color = color;
		attentuation = att;
	}
	
	public Vector3f getAttentuation(){
		return this.attentuation;
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

}
