package main;

import processing.core.PApplet;

import component.Game;

public class Main extends PApplet {

	Game game;
	
	public void setup(){
		size(800, 800);
		game = new Game();
	}
	
	public void draw(){
		game.Input();
		game.Update();
	}
}
