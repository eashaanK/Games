package com.base.engine;

import org.lwjgl.input.Keyboard;

import com.base.input.Input;

public class Game {

	public Game(){
		
	}
	
	public void input(){
		if(Input.getMouseUp(1))
			System.out.println("LOL: " + Input.getMousePosition());
	}
	
	public void update(float delta){
	}
	
	public void render(){
		
	}
}
