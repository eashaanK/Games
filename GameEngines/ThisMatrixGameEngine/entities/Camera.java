package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import terrains.Terrain;
import toolbox.Maths;

public abstract class Camera {

	protected Vector3f pos = new Vector3f(0, 0, 0);
	protected float pitch; //High or low aim or rot around x
	protected float yaw; //y axis left or right
	protected float roll;  //z axis tilted
	

	
	//1stPerson
	public static final float HORIZONTAL_SENSITIVITY = 6;
	
	private Player player;
	
	public Camera(Vector3f pos, Player player){

		this.pos = pos;
		this.player = player;
	}

	public void moveBy(float dx, float dy, float dz){
		this.pos.x += dx;
		this.pos.y += dy;
		this.pos.z += dz;
	}
	

	public abstract void move(Terrain terrain);
	
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
	
	public Player getPlayer(){
		return this.player;
	}
	
	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	
}
