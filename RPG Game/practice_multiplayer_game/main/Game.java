package main;

import multilayer_shit.Client;
import processing.core.PApplet;

public class Game extends PApplet{
	
	private Client client;
	
	
	
	public void setup(){
		size(500, 500);
		client = new Client(8888);
		client.findServer("localhost");
	}
	
	public void draw(){
		background(255, 255, 255);
	}
}
