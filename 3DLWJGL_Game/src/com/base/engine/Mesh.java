package com.base.engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.base.ref.Ref;

public class Mesh {

	private int vbo; //pointer to where vertex is stored
	private int size; //how much data in pointer
	
	public Mesh(){
		vbo = GL15.glGenBuffers();
		size = 0;
	}
	
	public void addVertices(Vertex[] vertices){
		size =vertices.length ;	//how many vertices * how big one vertex is = how much datat needs to be stored
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);

		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL15.GL_STATIC_DRAW); //what type of date we are sending it
		
		

	}
	
	public void draw(){


		
		GL20.glEnableVertexAttribArray(0);
		
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		
		
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, Ref.VERTEX_SIZE * 4, 0);
		
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, size);
		
		GL20.glDisableVertexAttribArray(0);

	}
}
