package com.infagen.gameObject;

import org.lwjgl.util.vector.Vector3f;

import com.infagen.model.TexturedModel;

public class GameObject {

	private TexturedModel model;
	private Transform transform;
	private String name = "GameObject";
	
	public GameObject(String name, TexturedModel model, Transform transform) {
		this.setName(name);
		this.model = model;
		this.transform = transform;
	}
	
	public GameObject(String name, TexturedModel model) {
		this(name, model, new Transform(new Vector3f(0, 0, 0), 0, 0, 0, 1));
	}

	public TexturedModel getTexturedModel() {
		return model;
	}

	public void setTexturedModel(TexturedModel model) {
		this.model = model;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name + " " + this.transform.toString() + this.model.toString();
	}
	
	
}
