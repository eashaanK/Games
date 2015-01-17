package main;

import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controls.Controls;
import controls.Key;
import gameObject.Player;
import helpers.DrawHelp;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

//204, 2, 255 hair
//eyes 201, 127, 246
//skin 193, 137, 228
//clothes 214, 192, 227
public class Game extends PApplet{

	public static Player player;
	private Controls controls;
	public static final int WIDTH = 800, HEIGHT = 800;
	public static ControlP5 gui;

	
	public void setup(){
		size(WIDTH, WIDTH);
		controls = new Controls();
		player = new Player(this, "boy_images/BlueBoySpriteSheet122X163.png", width/2f, height/2f, "BOB");
		initGUI();
	}
	
	public void draw(){
		background(255, 255, 255);
		//draw everything relative to world
		textSize(15);
		fill(0, 0, 0);
		text("(" + mouseX + " , " + mouseY + ")", mouseX, mouseY);
	
		this.moveWorld();
		fill(1, 1, 255);
		rect(10, 10, 100, 20);
		text("10, 10", 10, 10);
		
		
		//draw everything relative to screen
		player.update();
		player.draw();
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
	
	public void controlEvent(ControlEvent theEvent){
		 if(theEvent.getController().getName().equals("Button 1")){
			 System.out.println("Button 1 pressed");
		 }
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	private void initGUI(){
		gui = new ControlP5(this);
		Button tempB = gui.addButton("Button 1");	
	}
	
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

