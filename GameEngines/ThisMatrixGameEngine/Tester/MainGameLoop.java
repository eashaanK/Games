package Tester;

import java.util.ArrayList;

import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;

/**
 * https://www.youtube.com/watch?v=bcxX0R8nnDs&index=11&list=
 * PLRIWtICgwaX0u7Rf9zkZhLoLuZVfUksDP # 11
 * 
 * @author eashaan
 * 
 */

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();

		TexturedModel dragonModel = new TexturedModel(OBJLoader.loadObjModel(
				"Dragon/dragon.obj", loader), new ModelTexture(
				loader.loadTexture("DefaultTexture")));
		ModelTexture texture = dragonModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);

		ArrayList<Entity> allDragons = new ArrayList<Entity>();
		
		

		for(int i = 0; i < 10; i++){
			float x = (float)(Math.random() * 100 - 50);
			float y = (float)(Math.random() * 100 - 50);
			float z = (float)(Math.random() * -300);
			
			Entity entity = new Entity(dragonModel, new Vector3f(x, y, z), (float)(Math.random() * 180f), (float)(Math.random() * 180f), 0, 1);
			allDragons.add(entity);
		}
		
		Light light = new Light(new Vector3f(0, 0, -20f), new Vector3f(1, 1, 1));

		Camera camera = new Camera();

		MasterRenderer mRenderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			camera.updateMove();
			for(Entity e: allDragons){
				mRenderer.processEntity(e);
			}
			mRenderer.render(light, camera);
			DisplayManager.updateDisplay();

		}
		mRenderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
