package com.base.rendering;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtils {

	public static void clearScreen(){
		//TODO: stencil buffer
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public static void initGraphics(){
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		glFrontFace(GL_CW);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_TEST);
		
		//TODO: Depth Clamp lol
		glEnable(GL_FRAMEBUFFER_SRGB);
	}
	
	public static String getOpenGlVersion(){
		return glGetString(GL_VERSION);
	}
	
}
