package game;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import logic.PKeyboard;
import logic.PMouse;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import Components.Entity;
import controlP5.Button;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import ek_Misc.EKConsole;

public class Game extends PApplet{
	ControlP5 gui;
	
	public static EKConsole console = new EKConsole(new Dimension(600, 500), "Testing", Color.white, Color.black);
	
	private ArrayList<Entity> allEntities = new ArrayList<Entity>();
	
	public void setup(){
		Thread t1 = new Thread(console);
		t1.start();
	
		size(800, 800);
		this.frameRate(60);
		this.rectMode(CENTER);
		this.ellipseMode(CENTER);
		this.imageMode(CENTER);
		this.smooth();
		gui = new ControlP5(this);
		Button tempB = gui.addButton("Button 1");	
		
		allEntities.add(new Entity(this, 100, 100, 50, 50, 0, Color.red));
	}
	
	public void draw(){
		background(255, 255, 255);
		for(int i = this.allEntities.size() - 1; i >= 0; i--){
			if(this.allEntities.get(i).isCloseRequested())
				this.allEntities.remove(i);
			else
			{
				this.allEntities.get(i).update(this.allEntities);
				this.allEntities.get(i).render();
			}
		}
		gui.draw();
	}
	
	public void controlEvent(ControlEvent theEvent){
		 if(theEvent.getController().getName().equals("Button 1")){
			 console.println("Button 1 pressed");
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
