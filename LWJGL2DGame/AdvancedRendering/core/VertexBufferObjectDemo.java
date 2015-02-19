package core;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glGenBuffers;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

import static org.lwjgl.opengl.GL15.*;

import window.Window;


public class VertexBufferObjectDemo {

	
	final static int vertexSize = 3;
	final static int colorSize = 3;
	final static int amountOfVertices = 3;
	static FloatBuffer vertexBuffer, colorBuffer;
	
	static int vboVertexHandle, vboColorHandle;
	
	public static void main(String[] args){
		Window.createWindow();
		
		initOpenGL();
		
		while(!Display.isCloseRequested()){
			updateOpengl();
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		Window.close();
	}

	private static void updateOpengl() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		//bind vertex , then color
		GL15.glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		GL11.glVertexPointer(vertexSize, GL11.GL_FLOAT,  0, 0L);
		
		GL15.glBindBuffer(GL_ARRAY_BUFFER, vboColorHandle);
		GL11.glColorPointer(colorSize, GL11.GL_FLOAT, 0, 0L);
		
		//repeat code -- enable color and vertex and draw them and disable
		GL11.glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		glDrawArrays(GL_TRIANGLES, 0, amountOfVertices);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_VERTEX_ARRAY);
		
	}

	private static void initOpenGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(1, 1, 1, 1, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		vertexBuffer = BufferUtils.createFloatBuffer(vertexSize * amountOfVertices);
		vertexBuffer.put(new float[]{-0.5f, -0.5f, 0, 0.5f, -0.5f, 0, 0.5f, 0.5f, 0});
		vertexBuffer.flip();
		
		colorBuffer = BufferUtils.createFloatBuffer(amountOfVertices * colorSize);
		colorBuffer.put(new float[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
		colorBuffer.flip();
		
		vboVertexHandle = GL15.glGenBuffers();
		GL15.glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle); //warn opengl that use the following buffer for upcoming drawing stuff
		GL15.glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW); //stores the data in the float buffer in the ghostly vertex buffer (yeahh...), which is a piece of memory on the graphics card
		GL15.glBindBuffer(GL_ARRAY_BUFFER, 0); //to un bind
		
		vboColorHandle = glGenBuffers();
		GL15.glBindBuffer(GL_ARRAY_BUFFER, vboColorHandle);
		GL15.glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
		GL15.glBindBuffer(GL_ARRAY_BUFFER, 0); //unbind the buffer
		
		
	}
}
