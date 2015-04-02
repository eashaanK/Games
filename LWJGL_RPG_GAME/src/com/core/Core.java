package com.core;

import org.lwjgl.opengl.Display;

import com.renderEngine.DisplayManager;

public class Core {

	public static void main(String[] args){
		DisplayManager.createDisplay();
		while(!Display.isCloseRequested()){
			DisplayManager.updateDisplay();
		}
		DisplayManager.closeDisplay();
	}
}
