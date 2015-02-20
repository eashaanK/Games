package model;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

public class Face {

	public Vector3f vertex = new Vector3f(); // just 3 indices, not vertices of normals
	public Vector4f normal = new Vector4f();
	
	public Face(Vector3f vertex, Vector4f normal){
		this.vertex = vertex;
		this.normal = normal;
	}
}
