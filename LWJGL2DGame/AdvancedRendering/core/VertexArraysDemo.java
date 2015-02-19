package core;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import window.Window;

public class VertexArraysDemo {

	final static int amountOfVertices = 3;
	final static int vertexSize = 2;
	final static int colorSize = 3;
	
	static FloatBuffer vertexData, colorData ;
	
	
	public static void main(String[] args){
		Window.createWindow();
		
		VertexArraysDemo.initOpenGL();
		
		
		while(!Display.isCloseRequested()){
			updateOpenGL();
			
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		Window.close();
	}

	private static void updateOpenGL() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glVertexPointer(2, 0, vertexData);
		GL11.glColorPointer(3, 0, VertexArraysDemo.colorData);
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, amountOfVertices);
		
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
	}

	private static void initOpenGL() {
		
		
		vertexData = BufferUtils.createFloatBuffer(amountOfVertices * vertexSize); 
		vertexData.put(new float[] {-0.5f, -0.5f, 0.5f, -0.5f, 0.5f, 0.5f});
		vertexData.flip();
		
		colorData = BufferUtils.createFloatBuffer(amountOfVertices * colorSize);
		colorData.put(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
		colorData.flip();
	}
}
