package guis;

import org.lwjgl.util.vector.Vector2f;

public class GuiTexture {

	private int textureID;
	private Vector2f position;
	private Vector2f scale;
	private float rotX;
	private float rotY;
	private float rotZ;
	
	public GuiTexture(int textureID, Vector2f position, Vector2f scale) {
		this(textureID, position, scale, 0, 0, 0);
	}
	
	public GuiTexture(int textureID, Vector2f position, Vector2f scale, float rotX, float rotY, float rotZ) {
		this.textureID = textureID;
		this.position = position;
		this.scale = scale;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
	}
	
	public int getTextureID() {
		return textureID;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	public Vector2f getScale() {
		return scale;
	}
	public void setScale(Vector2f scale) {
		this.scale = scale;
	}

	public float getRotX() {
		return rotX;
	}

	public float getRotY() {
		return rotY;
	}
	
	public float getRotZ() {
		return rotZ;
	}
	
	public void rotateBy(float dx, float dy, float dz){
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
}
