package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f pos = new Vector3f(0, 0, 0);
	private float pitch; //High or low aim or rot around x
	private float yaw; //y axis left or right
	private float roll;  //z axis tilted
	
	public Camera(){
		
	}

	public void moveBy(float dx, float dy, float dz){
		this.pos.x += dx;
		this.pos.y += dy;
		this.pos.z += dz;
	}
	
	public void updateMove(){
		float speed = 0.02f;
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			this.moveBy(0, 0, -speed);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			this.moveBy(speed, 0, 0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			this.moveBy( -speed, 0, 0);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			this.moveBy(0, 0, speed);
		}
	}
	
	public Vector3f getPos() {
		return pos;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
}
