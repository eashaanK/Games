package com.base.engine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class RenderUtils {

	public static void clearScreen()
	{
		//TODO: Stencil Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); //clears the rendering color buffers and depth buffers
	}
	
	/**
	 * Initialize OPENGL
	 * Sets engine's default state
	 */
	public static void initGraphics(float r, float g, float b, float a){
		GL11.glClearColor(r, g, b, a);
		GL11.glFrontFace(GL11.GL_CW); 
		GL11.glCullFace(GL11.GL_BACK); //does not draw this
		GL11.glEnable(GL11.GL_CULL_FACE); //does not draw the opposite side
		GL11.glEnable(GL11.GL_DEPTH_TEST); //Tells OPENGL to draw things in the right order
		
		//TODO: Depth clamp for later
		GL11.glEnable(GL30.GL_FRAMEBUFFER_SRGB);
	}
	
}
