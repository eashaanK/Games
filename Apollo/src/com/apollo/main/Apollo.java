package com.apollo.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import processing.core.PApplet;
import controlP5.ControlEvent;
import controlP5.ControlP5;
import controlP5.ListBox;
import controlP5.ListBoxItem;

public class Apollo extends PApplet{

	boolean hasSetupClose = false;

	ControlP5 gui;
	ListBox reminders;
	
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
		

		frame.setTitle("Apollo");

	}
	
	public void draw(){
		if(!hasSetupClose){
			ChangeWindowListener();
		}
		
		background(0,0,0);

		gui.draw();

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

}
