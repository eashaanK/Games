package renderEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import shaders.StaticShader;
import shaders.TerrainShader;
import terrains.Terrain;
import entities.Bush;
import entities.Camera;
import entities.Entity;
import entities.Flower;
import entities.Grass;
import entities.Light;
import entities.Rock;
import entities.Tree;

public class MasterRenderer {

	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private static float skyR = 0.529f, skyG = 0.808f, skyB = 0.980f;
	//private static float skyR = 171f/255f, skyG = 236f/255f, skyB = 242f/255f;

	private Matrix4f projectionMatrix;
	
	private StaticShader shader = new StaticShader();
	private EntityRenderer renderer;
	private TerrainRenderer terrainRenderer;
	private TerrainShader terrainShader = new TerrainShader();
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();

	
	public MasterRenderer(){
		enableCulling();
		this.createProjectionMatrix();
		renderer = new EntityRenderer(shader, projectionMatrix);
		this.terrainRenderer = new TerrainRenderer(this.terrainShader, this.projectionMatrix);
	}
	
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void render(Light sun, Camera camera){
		prepare();
		//render entities
		shader.start();
		shader.loadSkyColor(skyR, skyG, skyB);
		shader.loadLight(sun);
		shader.loadViewMatrix(camera);
		this.renderer.render(entities);
		shader.stop();
		//render terrain
		this.terrainShader.start();
		this.terrainShader.loadSkyColor(skyR, skyG, skyB);
		this.terrainShader.loadLight(sun);
		this.terrainShader.loadViewMatrix(camera);
		this.terrainRenderer.render(terrains);
		this.terrainShader.stop();
		//clear all lists
		this.terrains.clear();
		entities.clear();
	}
	
	public void processTerrain(Terrain terrain){
		this.terrains.add(terrain);
		for(Tree tree : terrain.getTrees()){
			this.processEntity(tree);
		}
		
		for(Grass grass : terrain.getGrass()){
			this.processEntity(grass);
		}
		
		for(Bush bush : terrain.getBushes()){
			this.processEntity(bush);
		}
		
		for(Rock rock : terrain.getRocks()){
			this.processEntity(rock);
		}
		
		for(Flower flower : terrain.getFlowers()){
			this.processEntity(flower);
		}
		
	}
	
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch != null){
			batch.add(entity);
		}
		else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	/**
	 * Clear Color
	 * @param color
	 */
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(skyR, skyG, skyB, 1);
	}
	
	private void createProjectionMatrix(){
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = this.FAR_PLANE - this.NEAR_PLANE;
		
		projectionMatrix = new Matrix4f();
		projectionMatrix.m00 = x_scale;
		projectionMatrix.m11 = y_scale;
		projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		projectionMatrix.m23 = -1;
		projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		projectionMatrix.m33 = 0;
	}

	/**
	 * CLEAN UP ALL TERRAIN SHADERS
	 */
	public void cleanUp(){
		this.shader.cleanUp();
		this.terrainShader.cleanUp();
		System.out.println("<Master Renderer> Remember to clean up ALL shaders");
	}
}
