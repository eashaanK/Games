package components;

import javazoom.jl.player.Player;
import input.Key;
import input.PKeyboard;
import processing.core.PApplet;

public class Entity {

	private Transform transform;
	private boolean isOurPlayer = false;
	private String name;
	
	public Entity(String name, Transform transform, boolean isOurPlayer){
		this.name = name;
		this.transform = transform;
		this.isOurPlayer = isOurPlayer;
	}
	
	public void input(float delta, float xSpeed, float ySpeed){
		if(isOurPlayer){
			if(PKeyboard.keyHeld(Key.W)){
				this.transform.move(0, -ySpeed, delta);
			}
			if(PKeyboard.keyHeld(Key.S)){
				this.transform.move(0, ySpeed, delta);
			}
			if(PKeyboard.keyHeld(Key.A)){
				this.transform.move(-xSpeed, 0, delta);
			}
			if(PKeyboard.keyHeld(Key.D)){
				this.transform.move(xSpeed, 0, delta);
			}
		}
	}
	
	public void draw(float delta, PApplet parent, boolean debug){
		
		

		if(!isOurPlayer){
			parent.fill(0, 255, 255);
			parent.rect(this.transform.X(), this.transform.Y(), this.transform.getWidth(), this.transform.getHeight());
		    debugDraw(parent, false);
		}
		else{
			parent.fill(0, 255, 255);
			parent.rect(parent.width/2 - transform.getWidth()/2, parent.height/2 - transform.getHeight()/2, this.transform.getWidth(), this.transform.getHeight()); //player
		    debugDraw(parent, true);

			parent.translate(-this.transform.X(), -this.transform.Y());

			/*  float xOffset = transform.X() - (parent.width / 2);
		      float yOffset = transform.Y() - (parent.height / 2);
		      parent.rect(parent.width/2 - transform.getWidth()/2, parent.height/2 - transform.getHeight()/2, this.transform.getWidth(), this.transform.getHeight()); //player
		      parent.translate(-xOffset, -yOffset);*/
		}


		
	}
	
	private void debugDraw(PApplet parent, boolean translate){
		parent.fill(255, 255, 255);
		parent.textSize(20);
		if(translate)
			parent.text(this.name, parent.width/2 - transform.getWidth()/2, parent.height/2 - transform.getHeight()/2);
		else
			parent.text(this.name, transform.X(), transform.Y());
	}
	
	public Transform T(){
		return this.transform;
	}
}
