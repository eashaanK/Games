package com.pong.main;

import processing.core.PApplet;

import com.pong.client.Client;

public class Main extends PApplet
{
	
	public void setup(){
		size(800, 800);
		Client c = new Client("localhost", 8888);
		c.start();
		
		
		this.frame.setTitle("Pong");

	}
	
	public void draw(){
		background(0,0,0);
		
	}

}
