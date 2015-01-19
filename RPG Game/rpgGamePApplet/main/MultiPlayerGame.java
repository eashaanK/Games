package main;

import gameObject.Player;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import serverComponents.Client;
import controls.Key;

public class MultiPlayerGame {
	public PApplet parent;
	private float width, height;
	public Player player;
	public Client client;

	public MultiPlayerGame(PApplet parent){
		this.parent = parent;
		this.width = parent.width;
		this.height = parent.height;
		player = new Player(parent, "boy_images/BlueBoySpriteSheet122X163.png", width/2f, height/2f, "BOB");
		this.attempConnect();
	}
	
	private void attempConnect(){
		client = new Client("localhost", 8888);
	}
	
	public void drawPostTranslate(){
		player.update();
		player.draw();
		updatePlayerMovements();
	}
	
	public void keyPressed(KeyEvent e){
	}
	
	public void keyReleased(KeyEvent e){
	}
	
	public void mousePressed(MouseEvent e){
	}
	
	public void mouseReleased(MouseEvent e){
	}
	
	public void allowMovement(){
		player.setCanMove(true);
	}
	
	public void disallowMovement(){
		player.setCanMove(false);
	}
	
	private void updatePlayerMovements(){
		if(!player.canMove()){
			System.err.println("Player could not move");
			return;
		}
		PVector speed = new PVector(5, 5);
		if(Game.controls.keyHeld(Key.W))
			player.moveBy(0, -speed.y); 
		
		if(Game.controls.keyHeld(Key.S))
			player.moveBy(0, speed.y);

		if(Game.controls.keyHeld(Key.A))
			player.moveBy(-speed.x, 0); 
		
		if(Game.controls.keyHeld(Key.D))
			player.moveBy(speed.x, 0); 
	}
}
