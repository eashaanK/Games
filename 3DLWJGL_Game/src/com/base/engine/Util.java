package com.base.engine;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import com.base.ref.Ref;

public class Util {
	
	public static FloatBuffer createFloatBuffer(int size){
		return BufferUtils.createFloatBuffer(size);
	}

	public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
		FloatBuffer buffer = createFloatBuffer(vertices.length * Ref.VERTEX_SIZE); 
		
		for(int i = 0; i < vertices.length; i++){
			buffer.put(vertices[i].getPos().getX());
			buffer.put(vertices[i].getPos().getY());
			buffer.put(vertices[i].getPos().getZ());
			
		}
		
		buffer.flip(); //Proper order
		return buffer;
	}
}
