package game;

import java.util.ArrayList;

import logic.Key;
import logic.PKeyboard;
import logic.PMouse;
import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import Components.Entity;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;

public class Game extends PApplet{
	ControlP5 gui;
	
	PVector pPos, aPos, pVel, aVel;
	float angle = 0, gravity = 9.81f;
	
	public void setup(){	
		size(500, 500);
		this.frameRate(60);
		this.rectMode(CENTER);
		this.ellipseMode(CENTER);
		this.imageMode(CENTER);
		this.smooth();
		gui = new ControlP5(this);
		Button tempB = gui.addButton("Button 1");	
		
		pPos = new PVector();
		aPos = new PVector();
		
		pVel = new PVector(1f, 1f);
	}
	
	public void draw(){
		background(255, 255, 255);
		
		fill(255);
		this.rect(pPos.x, pPos.y, 50, 50);
	
		this.basicFPSControls(pPos, pVel);
	
		
		gui.draw();
	}
	
	public void updateEntities(ArrayList<Entity> allEntities){
		for(int i = allEntities.size() - 1; i >= 0; i--){
			if(allEntities.get(i).isCloseRequested())
				allEntities.remove(i);
			else
			{
				allEntities.get(i).update(allEntities);
				allEntities.get(i).render();
			}
		}
	}
	
	public void controlEvent(ControlEvent theEvent){
		 if(theEvent.getController().getName().equals("Button 1")){
			 //console.println("Button 1 pressed");
		 }
	}
	
	public void basicFPSControls(PVector pos, PVector vel){
		if(PKeyboard.keyHeld(Key.W))
			pos.y-= vel.y;
		if(PKeyboard.keyHeld(Key.S))
			pos.y+= vel.y;
		
		if(PKeyboard.keyHeld(Key.A))
			pos.x-= vel.x;
		if(PKeyboard.keyHeld(Key.D))
			pos.x+= vel.x;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		PKeyboard.addKey(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		PKeyboard.removeKey(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		PMouse.addButton(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		PMouse.removeButton(e);
	}
}
