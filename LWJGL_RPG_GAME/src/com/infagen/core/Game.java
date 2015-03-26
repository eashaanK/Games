package com.infagen.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import com.infagen.gameObject.Camera;
import com.infagen.gameObject.GameObject;
import com.infagen.gameObject.Light;
import com.infagen.input.Input;
import com.infagen.loaders.Loader;
import com.infagen.loaders.OBJLoader;
import com.infagen.model.RawModel;
import com.infagen.model.TexturedModel;
import com.infagen.renderEngines.MasterRenderer;
import com.infagen.renderEngines.Time;
import com.infagen.texture.ModelTexture;
import com.infagen.world.World;

/**
 * TUT 13
 * @author eashaan
 *
 */
public class Game {
	
	
	
	private Loader loader;
	
	private  GameObject gameObject;
	
	Camera camera;
	
	Light light;
	
	//Terrain terrain;
	World world;

	
	public Game(Loader loader){
		this.loader = loader;
		init();
	}
	
	private void init(){
		
		
		RawModel model = OBJLoader.loadObjModel("stall", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));
		texture.setShineDamper(10);
		texture.setReflectivity(0.1f);
		TexturedModel betterModel = new TexturedModel(model, texture);
		
		gameObject = new GameObject("LOL", betterModel);
		gameObject.getTransform().setPosition(new Vector3f(0, 0, -50));
		
		camera = new Camera();
		
		light = new Light(1, 1, 1);
		
		
		world = new World(loader);
		
		//terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
	}

	public void update(MasterRenderer renderer) {
		renderer.render(light, camera, 133f/255f, 206f/255f, 235f/255f);
		renderer.processEntity(gameObject);
		
		gameObject.getTransform().rotateBy(0, 10 * Time.getDelta(), 0);
		
		//renderer.processTerrain(terrain);
		world.render(renderer);
		
		moveCamera(10f);
		
	}

	private void moveCamera(float speed) {
		if(Input.GetKeyDown(Keyboard.KEY_W)){
			camera.getTransform().moveBy(0, 0, -speed);
		}
		if(Input.GetKeyDown(Keyboard.KEY_S)){
			camera.getTransform().moveBy(0, 0, speed);
		}
		
		if(Input.GetKeyDown(Keyboard.KEY_A)){
			camera.getTransform().moveBy(-speed, 0, 0);
		}
		
		if(Input.GetKeyDown(Keyboard.KEY_D)){
			camera.getTransform().moveBy(speed, 0, 0);
		}
		
		if(Input.GetKeyDown(Keyboard.KEY_SPACE)){
			camera.getTransform().moveBy(0, speed, 0);
		}
		
		if(Input.GetKeyDown(Keyboard.KEY_LSHIFT)){
		
			camera.getTransform().moveBy(0, -speed, 0);
		}		
		
		
		
	}

	public void close() {
	}
	
	
}
