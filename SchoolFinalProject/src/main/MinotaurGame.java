package main;

import input.PKeyboard;
import input.PMouse;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

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

	float delta;
	long lastTime;
	final float TIME_SPEED = 100;
	
	boolean hasSetupClose = false;

	ControlP5 gui;
	ListBox reminders;
	
	Entity player;

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public void setup(){
		size(800, 800);
		this.frameRate(60);
		this.smooth();
		gui = new ControlP5(this);
		reminders = gui.addListBox("Reminders")
				.setPosition(0, 0).setSize(width/3,  height/2).setItemHeight(15).setBarHeight(30).setColorBackground(color(255, 128)).setColorActive(color(0, 100, 255)).setColorForeground(color(0, 100, 255));
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
		
		player = new Entity("Player", new Transform(new PVector(0, 0), 100, 100, 0), true);
		for(int i = 0; i < 1; i++){
			entities.add(new Entity("Enemy", new Transform(new PVector((float)Math.random() * 10, 0), 100, 100, 0), false));
		}
		//other = ;

		lastTime = System.nanoTime();
	}
	
	public void draw(){
		if(!hasSetupClose){
			ChangeWindowListener();
		}

		long currentTime = System.nanoTime();
		delta = (currentTime - lastTime)/(1000000000 * TIME_SPEED);
		background(0,0,0);
		//my player
		player.input(delta, 5, 5);
		player.draw(delta, this, true);
		//enemies
		for(int i = this.entities.size() - 1; i >= 0; i--){
			entities.get(i).draw(delta, this, true);
		}
		//gui
		translate(player.T().X()  , player.T().Y()  );

		gui.draw();

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
