package components;

import input.Key;
import input.PKeyboard;
import processing.core.PApplet;

public class Entity {

	private Transform transform;
	private boolean isOurPlayer = false;
	
	public Entity(Transform transform, boolean isOurPlayer){
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
	
	public void draw(float delta, PApplet parent){
		
		parent.fill(0, 255, 255);
		if(!isOurPlayer)
			parent.rect(this.transform.X(), this.transform.Y(), this.transform.getWidth(), this.transform.getHeight());
		else{
			parent.rect(parent.width/2 - transform.getWidth()/2, parent.height/2 - transform.getHeight()/2, this.transform.getWidth(), this.transform.getHeight()); //player
			parent.translate(-this.transform.X(), -this.transform.Y());
		}
		
	}
	
	public Transform T(){
		return this.transform;
	}
}
