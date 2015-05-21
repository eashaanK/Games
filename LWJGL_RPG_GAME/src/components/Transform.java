package components;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class Transform {

	private Vector3f position;
	private Quaternion rotation;
	private Vector3f scale;
	
	public Transform(Vector3f position, Quaternion rotation, Vector3f scale) {
		super();
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Quaternion getRotation() {
		return rotation;
	}

	public void setRotation(Quaternion rotation) {
		this.rotation = rotation;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}
	
	public void rotate(float dxR, float dyR, float dzR, float delta){
		rotation.x += dxR * delta;
		rotation.y += dyR * delta;
		rotation.z += dzR * delta;
	}
	
	public void translate(float dx, float dy, float dz, float delta){
		this.position.x += dx * delta;
		this.position.y += dy * delta;
		this.position.z += dz * delta;
	}
	
	public void grow(float dx, float dy, float dz, float delta){
		this.scale.x += dx * delta;
		this.scale.y += dy * delta;
		this.scale.z += dz * delta;
	}
	
	public String toString(){
		return this.position.toString() + " " + this.rotation.toString() + " " + this.scale.toString();
	}
	
}
