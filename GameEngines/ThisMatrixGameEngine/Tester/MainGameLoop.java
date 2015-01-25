package Tester;

import java.awt.Color;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;

/**
 https://www.youtube.com/watch?v=bcxX0R8nnDs&index=11&list=PLRIWtICgwaX0u7Rf9zkZhLoLuZVfUksDP
 * # 11
 * @author eashaan
 *
 */

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		
		RawModel model = OBJLoader.loadObjModel("Dragon/dragon.obj", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("DefaultTexture"));
		TexturedModel staticModel = new TexturedModel(model, texture);
		
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, -20), 0, 0, 0, 1);
		
		Camera camera = new Camera();
		while(!Display.isCloseRequested()){
			//game logic
			//update
			{
				entity.rotateBy(0, 1, 0);
				camera.updateMove();
			}
			//render
			{
			renderer.prepare(Color.black);
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			DisplayManager.updateDisplay();		
			}
		}

		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}

}
