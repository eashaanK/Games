package com.base.game;

import org.lwjgl.input.Keyboard;

import com.base.input.Input;

public class Game {
	
	public Game(){
		
	}
	
	public void input(){
		if(Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("LOL");
	}
	
	public void update(){
		
	}
	
	public void render(){
		
	}
}
