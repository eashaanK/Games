package model;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

public class Model {

	private List<Vector3f> vertices = new ArrayList<Vector3f>();
	private List<Vector3f> normals = new ArrayList<Vector3f>();
	private List<Face> faces = new ArrayList<Face>();
	
	public Model(){
		
	}

}
