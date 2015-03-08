package com.infagen.renderEngines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.infagen.gameObject.Camera;
import com.infagen.gameObject.GameObject;
import com.infagen.gameObject.Light;
import com.infagen.gameObject.Terrain;
import com.infagen.gameObject.Transform;
import com.infagen.helpers.Maths;
import com.infagen.model.TexturedModel;
import com.infagen.shaders.StaticShader;
import com.infagen.shaders.TerrainShader;

public class MasterRenderer {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000f;
	
	private Matrix4f projectionMatrix;

	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private Map<TexturedModel, List<GameObject>> gameObjects = new HashMap<TexturedModel, List<GameObject>>();
	private List<Terrain> terrains  = new ArrayList<Terrain>();
	
	private float maxDistance = 100f;
	private Transform cameraTransform;
	
	public MasterRenderer(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		this.createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		terrainRenderer = new TerrainRenderer(terrainShader, projectionMatrix);
	}
	
	public void prepare(float r, float g, float b){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(r, g, b, 1);
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = (float)Display.getWidth() / (float)Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}
	
	public void render(Light light, Camera camera, float r, float g, float b){
		this.cameraTransform = camera.getTransform();
		prepare(r, g, b);
		shader.start();
		shader.loadLight(light);
		shader.loadViewMatrix(camera);
		renderer.render(gameObjects);
		shader.stop();
		
		terrainShader.start();
		terrainShader.loadLight(light);
		terrainShader.loadViewMatrix(camera);
		terrainRenderer.render(terrains);
		terrainShader.stop();
		
		terrains.clear();
		gameObjects.clear();
	}
	
	public void processTerrain(Terrain t){
		terrains.add(t);
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
		terrainShader.cleanUp();
	}
}
