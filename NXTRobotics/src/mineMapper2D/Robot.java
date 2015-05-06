package mineMapper2D;

import java.awt.Color;
import java.awt.Rectangle;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 * The Robot that traverses the Mine
 * @author eashaan
 *
 */
public class Robot {

	private Vector2f pos;
	private float angle;
	private Vector2f speed;
	private Vector3f color;
	private float width = 10, height = 20;
	
	public Robot(float x, float y, float angle, float r, float g, float b){
		this.pos = new Vector2f(x, y);
		this.angle = angle;
		this.color = new Vector3f(r, g, b);
		speed = new Vector2f(0, 0);
	}
	
	public void setDimension(float w, float h){
		width = w;
		height = h;
	}
	
	public void turnRight(){
		angle += 90;
	}
	
	public void turnLeft(){
		angle -= 90;
	}
	
	public void faceForward(){
		angle = 0;
	}
	
	public void faceLeft(){
		angle = -90;
	}
	
	public void faceRight(){
		angle = 90;
	}
	
	public void faceBack()
	{
		angle = 180;
	}
	
	public float X(){
		return this.pos.x;
	}
	
	public float Y(){
		return this.pos.y;
	}
	
	public float Rot(){
		return this.angle;
	}
	
	public void setColor(Color c){
		this.color.x = (float)(c.getRed()) / 255f;
		this.color.y = (float)(c.getGreen()) / 255f;
		this.color.z = (float)(c.getBlue()) / 255f;
	}
	
	public void goForward(float maxSpeed){
		this.speed.x = (float) (Math.sin(Math.toRadians(angle)) * maxSpeed);
		this.speed.y = (float) (Math.cos(Math.toRadians(angle)) * maxSpeed);	
	}
	
	public void stop(){
		this.speed.x = 0;
		this.speed.y = 0;
	}
	
	public void update(float delta){
		this.pos.x += delta * speed.x;
		this.pos.y += delta * speed.y;
	}
	
	public void render(){
		Draw.color(color.x, color.y, color.z, 1);
		Draw.drawRect(pos.x, pos.y, width, height, angle);
		Draw.color(1 - color.x, 1 - color.y, 1 - color.z, 1);
		Draw.drawTri(pos.x, pos.y, width, height/2, -angle);

	}

	public float getH() {
		return this.height;
	}
	
	public float getW() {
		return this.width;
	}
	
	public Rectangle getCollider(){	
		int w = 0, h = 0;
		if(angle % 180 == 0){ //standing vertically
			w = (int) width;
			h = (int) height;
		}
		else{
			w = (int) height;
			h = (int) width;
		}
		
		return new Rectangle((int)pos.x, (int)pos.y, w, h);
	}

}
