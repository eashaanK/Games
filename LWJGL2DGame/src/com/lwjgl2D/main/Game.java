package com.lwjgl2D.main;

import com.lwjgl2D.gui.TexturedButton;

public class Game {

	TexturedButton button;
	
	public void init(){
		button = new TexturedButton(100, 100, 100, 100, "Button", "GrayButtonTexture", "GrayButtonTexturePressed");
	}
	
	float x;
	
	public void update(){
	
		button.update();
		
		float vx = 0.1f;
		
		float x = button.getX();
		
		x += vx * DisplayManager.getDelta();
		button.setX(x);
	}
	
	public void close(){
		System.out.println("Game closed. Good bye!");
	}
}
