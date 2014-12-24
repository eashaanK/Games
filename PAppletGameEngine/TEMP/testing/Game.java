package testing;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Game extends PApplet{

	Thing2D t, t2;
	ArrayList<Thing2D> things = new ArrayList<Thing2D>();
	
	public static void main(String args[]) {
		PApplet.main(new String[] {/* "--present", */"testing.Game" });
	}

	
	public void setup(){
		size(700, 700);
		this.frameRate(1000000);
		this.rectMode(CENTER);
		things = new ArrayList<Thing2D>();

		t  = new Thing2D(this, 100, 100, 30, 30, Color.blue, 10);
		t2  = new Thing2D(this, width/2, height/2, 30, 30, Color.green, 10);
		add(t);
		add(t2);
	}
	
	public void draw(){
		background(255, 255, 255);
		
		render();
		
		t.follow(mouseX, mouseY, 0.5f);
		
		t2.follow(mouseX, mouseY, 1);
		
		
		
		fill(0);
		textSize(30);
		text(this.frameRate, 100, 100);
	}
	
	public void add(Thing2D t){
		things.add(t);
	}
	
	public void render(){
		
		for(int i = things.size() - 1; i >= 0; i--){
			if(!things.get(i).isAlive()){
				things.remove(i);
			}
			else{
				things.get(i).draw();
				
			}
		}
	}
	
	public void mousePressed(){
		t.addImpulse(1, -10);
	}
	
}
