package Tester;

import java.util.ArrayList;

import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;

/**
 * https://www.youtube.com/watch?v=ZyzXBYVvjsg&list=PLRIWtICgwaX0u7Rf9zkZhLoLuZVfUksDP&index=15
 * 15
 * @author eashaan
 * 
 */

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		TexturedModel dragonModel = new TexturedModel(OBJLoader.loadObjModel(
				"Stall Model and Texture/stall.obj", loader), new ModelTexture(
				loader.loadTexture("Stall Model and Texture/stallTexture")));
		ModelTexture texture = dragonModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);

		ArrayList<Entity> allEntities = new ArrayList<Entity>();
		
		for(int i = 0; i < 1; i++){
			float x = (float)(Math.random() * 100 - 50);
			float y = (float)(Math.random() * 100 - 50);
			float z = (float)(Math.random() * -300);
			
			Entity entity = new Entity(dragonModel, new Vector3f(x, y, z), (float)(Math.random() * 180f), (float)(Math.random() * 180f), 0, 1);
			allEntities.add(entity);
		}
		
		Light light = new Light(new Vector3f(0, 20, -20f), new Vector3f(1, 1, 1));

		Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("Terrain/grass")));
		Terrain terrain2 = new Terrain(1, -1, loader, new ModelTexture(loader.loadTexture("Terrain/sand")));

		
		Camera camera = new Camera();
		camera.moveBy(0, 1, 0);

		MasterRenderer mRenderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			camera.updateMove();
			//terrains
			mRenderer.processTerrain(terrain);
			mRenderer.processTerrain(terrain2);
			//entities
			for(Entity e: allEntities){
				mRenderer.processEntity(e);
			}
			//camera and light
			mRenderer.render(light, camera);
			//display
			DisplayManager.updateDisplay();

		}
		mRenderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
