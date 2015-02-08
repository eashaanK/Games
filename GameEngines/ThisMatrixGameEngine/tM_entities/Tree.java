package tM_entities;

import org.lwjgl.util.vector.Vector3f;

import tM_models.TexturedModel;

public class Tree extends Entity{

	public Tree(TexturedModel model, Vector3f pos, float rotX, float rotY,
			float rotZ, float scale) {
		super(model, pos, rotX, rotY, rotZ, scale);
	}

	public Tree(TexturedModel model, int index, Vector3f pos, float rotX, float rotY, float rotZ, float scale) {
		super(model, index, pos, rotX, rotY, rotZ, scale);
	}
}
