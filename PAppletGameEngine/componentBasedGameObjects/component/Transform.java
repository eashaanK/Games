package component;

import processing.core.PVector;

public class Transform {

	public static final Transform zero = new Transform(new PVector(0, 0, 0), new PVector(0, 0, 0), new PVector(0, 0, 0));
	public PVector position;
	public PVector rotation;
	public PVector scale;
	
	public Transform(PVector position, PVector rotation, PVector scale) {
		super();
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}
	
	public void move(PVector dP, float delta){
		this.position.x += dP.x * delta;
		this.position.y += dP.y * delta;
		this.position.z += dP.z * delta;
	}
	
	public void rotate(PVector dP, float delta){
		this.rotation.x += dP.x * delta;
		this.rotation.y += dP.y * delta;
		this.rotation.z += dP.z * delta;
	}
	
}
