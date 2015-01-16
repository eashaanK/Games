package main;

import controls.Controls;
import controls.Key;
import gameObject.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class Game extends PApplet{

	public static Player player;
	private Controls controls;
	
	
	public void setup(){
		size(800, 800);
		controls = new Controls();
		player = new Player(this, width/2f, height/2f, "BOB");
	}
	
	public void draw(){
		background(255, 255, 255);
		//draw everything relative to world
		this.moveWorld();
		fill(1, 1, 255);
		rect(10, 10, 100, 20);
		text("10, 10", 10, 10);
		
		
		//draw everything relative to screen
		player.update(this);
		player.draw(this);
		updatePlayerMovements();
			
		
	}
	
	
	
	//////////////////////////////////CONTROLS///////////////////////////////////////
	@Override
	public void keyPressed(KeyEvent e){
		controls.addKey(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		controls.removeKey(e.getKeyCode());
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		controls.addButton(e.getButton());
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		controls.removeButton(e.getButton());
	}
	
	/////////////////////////////////////////////////////////////////////////
	private void moveWorld(){
		g.translate(-player.X() + width / 2, -player.Y() + height / 2);
		fill(0, 255, 0);
		rect(0, 0, width, height);
	}
	
	public static void allowMovement(){
		player.setCanMove(true);
	}
	
	public static void disallowMovement(){
		player.setCanMove(false);
	}
	
	private void updatePlayerMovements(){
		if(!player.canMove()){
			System.err.println("Player could not move");
			return;
		}
		PVector speed = new PVector(5, 5);
		if(controls.keyHeld(Key.W))
			player.moveBy(0, -speed.y); 
		
		if(controls.keyHeld(Key.S))
			player.moveBy(0, speed.y);

		if(controls.keyHeld(Key.A))
			player.moveBy(-speed.x, 0); 
		
		if(controls.keyHeld(Key.D))
			player.moveBy(speed.x, 0); 

	
	}
}

