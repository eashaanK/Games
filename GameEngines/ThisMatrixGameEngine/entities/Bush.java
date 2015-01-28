package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.Loader;
import textures.ModelTexture;

public class Bush extends Entity{
	
	public Bush(TexturedModel model, Vector3f pos, float rotX, float rotY,
			float rotZ, float scale) {
		this(model, 0, pos, rotX, rotY, rotZ, scale);
	}
	
	//real contructor
	public Bush(TexturedModel model, int index, Vector3f pos, float rotX, float rotY, float rotZ, float scale) {
		super(model, index, pos, rotX, rotY, rotZ, scale);
	}
	
	

}
