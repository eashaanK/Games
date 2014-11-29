package game;

import java.awt.Color;

import logic.PKeyboard;
import logic.PMouse;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import Components.Entity;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;

public class Game extends PApplet{
	Entity e;
	ControlP5 gui;
	
	public void setup(){
		size(800, 800);
		this.frameRate(60);
		this.rectMode(CENTER);
		this.ellipseMode(CENTER);
		this.imageMode(CENTER);
		this.smooth();
		e = new Entity(this, 100, 100, 50, 50, 0, Color.red);
		gui = new ControlP5(this);
		Button tempB = gui.addButton("Button 1");		
	
	}
	
	public void draw(){
		background(255, 255, 255);
		e.update();
		e.render();
		gui.draw();
	}
	
	public void controlEvent(ControlEvent theEvent){
		 if(theEvent.getController().getName().equals("Button 1")){
			 System.out.println("Button 1 pressed");
		 }
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
