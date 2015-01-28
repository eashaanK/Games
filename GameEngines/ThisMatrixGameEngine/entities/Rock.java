package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Rock extends Entity{

	public Rock(TexturedModel model, Vector3f pos, float rotX, float rotY,
			float rotZ, float scale) {
		super(model, pos, rotX, rotY, rotZ, scale);
	}
	
	public Rock(TexturedModel model, int index, Vector3f pos, float rotX, float rotY, float rotZ, float scale) {
		super(model, index, pos, rotX, rotY, rotZ, scale);
	}

}
