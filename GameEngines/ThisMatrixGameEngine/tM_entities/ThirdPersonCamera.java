package tM_entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import tM_terrains.Terrain;

public class ThirdPersonCamera extends Camera{

	private float distanceFromPlayer = 10;
	private float angleAroundPlayer;

	public ThirdPersonCamera(Vector3f pos, Player player, float pitch, float distance) {
		super(pos, player);
		this.pitch = pitch;
		this.distanceFromPlayer = distance;
		
	}

	@Override
	public void move(Terrain terrain) {		
		if(Mouse.isInsideWindow()){
			calculateZoom();
			calculatePitch();
			calculateAngleAroundPlayer();
		}
		
		float horizontalDistance = this.calculateHorizontalDistance();
		float verticalDistance = calculateVerticalDistance();
		
		calculateCameraPosition(horizontalDistance, verticalDistance);
		
		this.yaw = 180 - (getPlayer().getRotY() + this.angleAroundPlayer);
		
		float terrainHeight = terrain.getHeightOfTerrainRelativeToWorld(pos.x, pos.z);
		if(pos.y < terrainHeight)
			pos.y = terrainHeight;
		//System.out.println()
	}
	
	private void calculateZoom(){
		float zoomLevel = Mouse.getDWheel() * 0.001f;
		distanceFromPlayer -= zoomLevel;
	}
	
	private void calculatePitch(){
		if(Mouse.isButtonDown(0)){ //right
			float pitchChange = Mouse.getDY() * 0.1f;
			pitch -= pitchChange;
		}
	}

	private void calculateAngleAroundPlayer(){
		if(Mouse.isButtonDown(0)){
			float angleChange = Mouse.getDX() * 0.3f;
			angleAroundPlayer -= angleChange;
		}
	}
	
	private float calculateHorizontalDistance(){
		return (float)(distanceFromPlayer * Math.cos(Math.toRadians(pitch)));
	}
	
	private float calculateVerticalDistance(){
		return (float)(distanceFromPlayer * Math.sin(Math.toRadians(pitch)));
	}
	
	private void calculateCameraPosition(float horizDistance, float verticDistance){
		float theta = getPlayer().getRotY() + this.angleAroundPlayer;
		float offsetX = (float) (horizDistance * Math.sin(Math.toRadians(theta)));
		float offsetZ = (float) (horizDistance * Math.cos(Math.toRadians(theta)));
		pos.x = getPlayer().getPos().x - offsetX;
		pos.z = getPlayer().getPos().z - offsetZ;
		pos.y = getPlayer().getPos().y + verticDistance + getPlayer().getCameraOffset();;
	
		
	}
	
	
}
