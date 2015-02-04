package com.base.engine;

import org.lwjgl.util.vector.Vector3f;

public class Vertex {


	private Vector3f pos;

	public Vertex(Vector3f pos) {
		super();
		this.pos = pos;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	
	
}
