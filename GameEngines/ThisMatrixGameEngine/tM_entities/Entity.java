package tM_entities;

import org.lwjgl.util.vector.Vector3f;

import tM_models.TexturedModel;

public class Entity {

	private TexturedModel model;
	protected Vector3f pos;
	protected float rotX, rotY, rotZ;
	protected float scale;
	
	private int textureIndex = 0;

	public Entity(TexturedModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale) {
		this(model, 0, pos, rotX, rotY, rotZ, scale);
	}
	
	public Entity(TexturedModel model, int index, Vector3f pos, float rotX, float rotY, float rotZ, float scale) {
		this.textureIndex = index;
		this.model = model;
		this.pos = pos;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}

	public void moveBy(float dx, float dy, float dz){
		this.pos.x += dx;
		this.pos.y += dy;
		this.pos.z += dz;
	}
	
	public void rotateBy(float dx, float dy, float dz){
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;
	}
	
	public TexturedModel getModel() {
		return model;
	}

	public void setModel(TexturedModel model) {
		this.model = model;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}
	
	public void setX(float x){
		this.pos.x = x;
	}
	
	public void setY(float y){
			this.pos.y = y;
	}

	public void setZ(float z){
		this.pos.z = z;
	}
	
	public float getX(){
		return pos.x;
	}
	
	public float getY(){
		return pos.y;
	}
	
	public float getZ(){
		return pos.z;
	}
	
	public float getTextureXOffset(){
		int column = textureIndex % model.getTexture().getNumberOfRows();
		return (float)column/(float)model.getTexture().getNumberOfRows();
	}
	
	public float getTextureYOffset(){
		int row = textureIndex / model.getTexture().getNumberOfRows() ;
		return (float)row/(float)model.getTexture().getNumberOfRows();
	}
	
	
}
