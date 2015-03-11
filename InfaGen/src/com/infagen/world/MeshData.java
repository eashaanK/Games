package com.infagen.world;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

public class MeshData {
	 public List<Vector3f> vertices = new ArrayList<Vector3f>();
     public List<Integer> triangles = new ArrayList<Integer>();
     public List<Vector2f> uv = new ArrayList<Vector2f>();
 
    public List<Vector3f> colVertices = new ArrayList<Vector3f>();
     public List<Integer> colTriangles = new ArrayList<Integer>();
 
    public MeshData() { }
	
}
