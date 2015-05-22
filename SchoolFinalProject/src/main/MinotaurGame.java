package main;

import input.PKeyboard;
import input.PMouse;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import processing.event.MouseEvent;

import components.Entity;
import components.Transform;

import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.ListBox;
import controlP5.ListBoxItem;

public class MinotaurGame extends PApplet{

	boolean hasSetupClose = false;

	ControlP5 gui;
	ListBox reminders;
	
	Entity player;
	Entity other;
	
	public void setup(){
		size(800, 800);
		this.frameRate(60);
		this.smooth();
		gui = new ControlP5(this);
		reminders = gui.addListBox("Reminders")
				.setPosition(100, 100).setSize(width/3,  height/2).setItemHeight(15).setBarHeight(30).setColorBackground(color(255, 128)).setColorActive(color(0, 100, 255)).setColorForeground(color(0, 100, 255));
		reminders.setTitle("Reminders");
		reminders.captionLabel().toUpperCase(true);
		reminders.captionLabel().setColor(0xffff0000);
		reminders.captionLabel().style().marginTop = 3;
		reminders.valueLabel().style().marginTop = 3;
		  
		  for (int i=0;i<80;i++) {
		    ListBoxItem lbi = reminders.addItem("item "+i, i);
		    lbi.setColorBackground(0xffff0000);
		  }
		

		frame.setTitle("Minotaur");
		
		player = new Entity(new Transform(new PVector(0, 0), 100, 100, 0), true);
		other = new Entity(new Transform(new PVector(0, 0), 100, 100, 0), false);


	}
	
	public void draw(){
		if(!hasSetupClose){
			ChangeWindowListener();
		}

		background(0,0,0);
		
		//my player
		player.input(0.1f, 5, 5);
		player.draw(0.1f, this);

		other.draw(0.1f, this);
		//other entities
		//fill(0, 0, 255);
		//rect(enemyX, enemyY, 50, 50);
		
		//gui
		translate(player.T().X(), player.T().Y());
		gui.draw();

		System.out.println(player.T());
	}
	
	public void cleanUp(){
		
	}
	
	private void ChangeWindowListener()
	{
	  WindowListener[] wls = frame.getWindowListeners();
	  println("Found " + wls.length + " listeners");
	  if (wls.length > 0)
	  {
	    frame.removeWindowListener(wls[0]); // Suppose there is only one...
	    frame.addWindowListener(new WindowAdapter()
	    {
		public void windowClosing(WindowEvent we)
		{
		    System.out.println("Have a Nice Day");
		    // Do something useful here
		    cleanUp();
		    exit();
		}
	    });
		  hasSetupClose = true;
	  }
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
