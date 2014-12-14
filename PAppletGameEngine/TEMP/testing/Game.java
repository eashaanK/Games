package testing;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class Game extends PApplet{

	Thing t;
	ArrayList<Thing> things = new ArrayList<Thing>();
	
	public static void main(String args[]) {
		PApplet.main(new String[] {/* "--present", */"testing.Game" });
	}

	
	public void setup(){
		size(2000, 2000);
		this.frameRate(60);
		this.rectMode(CENTER);
		things = new ArrayList<Thing>();

		t  = new Thing(this, 100, 100, 30, 30, Color.blue, 10);
		add(t);
	}
	
	public void draw(){
		background(255, 255, 255);
		render();
		t.lookAt(mouseX, mouseY);
	}
	
	public void add(Thing t){
		things.add(t);
	}
	
	public void render(){
		for(int i = things.size() - 1; i >= 0; i--){
			if(!things.get(i).isAlive()){
				things.remove(i);
			}
			else{
				things.get(i).draw();
				things.get(i).updatePhysics(this.frameRate/60, height, 0.01f);
			}
		}
	}
	
	public void mousePressed(){
		t.addImpulse(1, -10);
	}
	
}
