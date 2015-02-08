package tM_entities;

import org.lwjgl.util.vector.Vector3f;

public class LightModel extends Light{

	private boolean isOn = false;
	private Entity lightEntity;
	
	public LightModel(Vector3f pos, Vector3f color, Vector3f att, Entity lightEntity) {
		super(pos, color, att);
		this.isOn = true;
		this.lightEntity = lightEntity;
	}
	
	public Entity getLightEntity(){
		return lightEntity;
	}
	
	public boolean isOn(){
		return this.isOn;
	}
	
	public void tunLightOn(){
		this.isOn = true;
	}
	public void turnLightOff(){
		this.isOn = false;
	}

}
