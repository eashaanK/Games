package com.infagen.renderEngines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.util.vector.Vector3f;

import com.infagen.gameObject.Camera;
import com.infagen.gameObject.GameObject;
import com.infagen.gameObject.Light;
import com.infagen.gameObject.Transform;
import com.infagen.helpers.Maths;
import com.infagen.model.TexturedModel;
import com.infagen.shaders.StaticShader;

public class MasterRenderer {

	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer(shader);
	
	private Map<TexturedModel, List<GameObject>> gameObjects = new HashMap<TexturedModel, List<GameObject>>();
	
	private float maxDistance = 100f;
	private Transform cameraTransform;
	
	public void render(Light light, Camera camera){
		this.cameraTransform = camera.getTransform();
		renderer.prepare();
		shader.start();
		shader.loadLight(light);
		shader.loadViewMatrix(camera);
		
		renderer.render(gameObjects);
		
		shader.stop();
		gameObjects.clear();
	}
	
	private boolean shouldSkip(GameObject entity){
		if(this.cameraTransform == null)
			return false;
		Vector3f cameraPos = this.cameraTransform.getPosition();
		Vector3f gPos = entity.getTransform().getPosition();
		float dis = Maths.getDistance(cameraPos, gPos);
		System.out.println(dis);
		return dis > this.maxDistance;
	}
	public void processEntity(GameObject entity){
		boolean shouldSkip = shouldSkip(entity);
		
		if(shouldSkip)
			return;
	
		TexturedModel entityModel = entity.getTexturedModel();
		List<GameObject> batch = gameObjects.get(entityModel);
		if(batch != null){
			batch.add(entity);
		}
		else{
			List<GameObject> newBatch = new ArrayList<GameObject>();
			newBatch.add(entity);
			gameObjects.put(entityModel, newBatch);
		}
	}
	
	public float getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(float maxDistance) {
		this.maxDistance = maxDistance;
	}

	public void cleapUP(){
		shader.cleanUp();
	}
}
