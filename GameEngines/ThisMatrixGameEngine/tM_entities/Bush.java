package tM_entities;

import org.lwjgl.util.vector.Vector3f;

import tM_models.TexturedModel;
import tM_renderEngine.Loader;
import tM_textures.ModelTexture;

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
