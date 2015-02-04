package com.base.engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;

import com.base.ref.Ref;

public class Mesh {

	private int vbo; //pointer to where vertex is stored
	private int size; //how much data in pointer
	
	public Mesh(){
		vbo = GL15.glGenBuffers();
		size = 0;
	}
	
	public void addVertices(Vertex[] vertices){
		size =vertices.length * Ref.VERTEX_SIZE;	//how many vertices * how big one vertex is = how much datat needs to be stored
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		//GL15.glBufferData(GL15.GL_ARRAY_BUFFER, , );
	}
}
