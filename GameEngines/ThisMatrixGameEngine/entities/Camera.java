package entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import toolbox.Maths;

public class Camera {

	private Vector3f pos = new Vector3f(0, 0, 0);
	private float pitch; //High or low aim or rot around x
	private float yaw; //y axis left or right
	private float roll;  //z axis tilted
	
	//Default 3rd Person settings
	private static final float PITCH_LIMIT = 90, MAX_DISTANCE_FROM_PLAYER = 10f;
	private float distanceFromPlayer = 10;
	private float angleAroundPlayer = 0;
	
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
	
	private void calculateZoom(){
		float zoomLevel = Mouse.getDWheel() * 0.1f;
		this.distanceFromPlayer -= zoomLevel;
	}
	
	private void calculatePitch(){
		if(Mouse.isButtonDown(1)){
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
		//FPS
		else if(this.player.isFPV()){
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
			//pitch += Mouse.getDY();
		}

	}
	
	private void calculateAngleAroundPlayer(){
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() * 0.3f;
			this.angleAroundPlayer -= angleChange;
		}
	}
	
	private float calculateHorizontalDistance(){
		return (float)(this.distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance(){
		return (float)(this.distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateCameraPosition(float horizontalDistance, float verticalDistance){
		float theta = player.getRotY() + this.angleAroundPlayer;
		float offsetX = (float)(horizontalDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float)(horizontalDistance * Math.cos(Math.toRadians(theta)));
		pos.x = player.getPos().x - offsetX;
		pos.z = player.getPos().z - offsetZ;
		pos.y = player.getPos().y + verticalDistance;
		//FPS its rot = player.rotY()		
			//restrict the head movement
			if(!this.player.isFPV()){ //3rd person
				
				
				if(this.pitch > PITCH_LIMIT){
					this.pitch =PITCH_LIMIT;
				}
				else if(this.pitch < 0){
					this.pitch = 0;
				}
				if(this.distanceFromPlayer < 1)
					distanceFromPlayer = 1;
				else if(distanceFromPlayer >= MAX_DISTANCE_FROM_PLAYER)
					distanceFromPlayer = MAX_DISTANCE_FROM_PLAYER;
				
			}

			else //first person
			{
				if(this.pitch > 60){
					this.pitch = 60;
					//System.out.println("Pitch of FP camera is highest");
				}
				else if(this.pitch < 1){
					this.pitch = 1;
				}
				if(player.isFPV())
					this.distanceFromPlayer = 0;
				
				System.out.println(Mouse.getX());
				//System.out.println(Window.GetWidth()/2 + " "  +  Window.GetHeight()/2);
			}
		
		
		
		pos.y = player.getPos().y + player.getCameraOffset(); //currentHeight of player

	}
	
	public void move(){
		this.calculateZoom();
		this.calculatePitch();
		this.calculateAngleAroundPlayer();
		
		float horizontalDistance = this.calculateHorizontalDistance();
		float verticalDistance = this.calculateVerticalDistance();

		this.calculateCameraPosition(horizontalDistance, verticalDistance);
		this.yaw = 180 - (player.getRotY() + this.angleAroundPlayer);
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
