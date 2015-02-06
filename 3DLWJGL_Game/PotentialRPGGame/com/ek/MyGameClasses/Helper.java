package com.ek.MyGameClasses;

import com.ek.components.MeshRenderer;
import com.ek.core.GameObject;
import com.ek.core.Vector3f;
import com.ek.rendering.Material;
import com.ek.rendering.Mesh;
import com.ek.rendering.Texture;

public class Helper {

	public static GameObject getPlane(){
		//plane
		GameObject planeObject = new GameObject();
		Mesh mesh = new Mesh("plane3.obj");
		Material material = new Material(new Texture("bricks.jpg"), 1, 8,new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.03f, -0.5f);
		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		planeObject.AddComponent(meshRenderer);
		planeObject.GetTransform().GetPos().Set(0, -1, 5);
		planeObject.GetTransform().SetScale(new Vector3f(1, 1, 1));
		return planeObject;
	}
}
