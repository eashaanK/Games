package com.infagen.renderEngines;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import com.infagen.core.Core;
public class DisplayManager {

	public static void createDisplay() {
		
		ContextAttribs attribs = new ContextAttribs(3, 2).withForwardCompatible(true).withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(Core.WIDTH, Core.HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle(Core.TITLE);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, Core.WIDTH, Core.HEIGHT);
	}

	public static void updateDisplay() {
		Display.sync(Core.FPS_CAP);
		Display.update();
	}

	public static void closeDisplay() {
		Display.destroy();
	}
}
