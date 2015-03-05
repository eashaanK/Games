package com.infagen.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import com.infagen.model.RawModel;

public class OBJLoader {

	public static RawModel loadObjModel(String filename, Loader loader){
		filename = filename.replace(".obj", "");
		FileReader fr = null;
		try {
			fr = new FileReader(new File("src/models/" + filename + ".obj"));
		} catch (FileNotFoundException e) {
			System.err.println("Couldnt load obj file: " + filename);
			e.printStackTrace();
		}
		
		BufferedReader reader = new BufferedReader(fr);
		String line;
		List<Vector3f> vertices = new ArrayList<Vector3f>();
		List<Vector2f> textures = new ArrayList<Vector2f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Integer> indices = new ArrayList<Integer>();



		
	}
}
