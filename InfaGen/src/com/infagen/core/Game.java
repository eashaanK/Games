package com.infagen.core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import com.infagen.gameObject.Camera;
import com.infagen.gameObject.GameObject;
import com.infagen.input.Input;
import com.infagen.loaders.Loader;
import com.infagen.model.RawModel;
import com.infagen.model.TexturedModel;
import com.infagen.renderEngines.Renderer;
import com.infagen.shaders.StaticShader;
import com.infagen.texture.ModelTexture;

/**
 * TUT 6
 * @author eashaan
 *
 */
public class Game {
	
	
	
	private Loader loader;
	
	private  GameObject gameObject;
	
	Camera camera;

	
	public Game(Loader loader){
		this.loader = loader;
		init();
	}
	
	private void init(){
		
		
		RawModel model = loader.loadToVao(vertices, textureCoords, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("crate"));
		TexturedModel betterModel = new TexturedModel(model, texture);
		
		gameObject = new GameObject("LOL", betterModel);
		gameObject.getTransform().setPosition(new Vector3f(0, 0, -1));
		
		camera = new Camera();
	}

	public void update(Renderer renderer, StaticShader shader) {
		shader.loadViewMatrix(camera);
		
		renderer.render(gameObject, shader);
		
		float speed =  0.01f;
		
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
	
	float[] vertices = {			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,0.5f,-0.5f,		
			
			-0.5f,0.5f,0.5f,	
			-0.5f,-0.5f,0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			0.5f,0.5f,-0.5f,	
			0.5f,-0.5f,-0.5f,	
			0.5f,-0.5f,0.5f,	
			0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,-0.5f,	
			-0.5f,-0.5f,-0.5f,	
			-0.5f,-0.5f,0.5f,	
			-0.5f,0.5f,0.5f,
			
			-0.5f,0.5f,0.5f,
			-0.5f,0.5f,-0.5f,
			0.5f,0.5f,-0.5f,
			0.5f,0.5f,0.5f,
			
			-0.5f,-0.5f,0.5f,
			-0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,-0.5f,
			0.5f,-0.5f,0.5f
			
	};
	
	float[] textureCoords = {
			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,			
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0,
			0,0,
			0,1,
			1,1,
			1,0

			
	};
	
	int[] indices = {
			0,1,3,	
			3,1,2,	
			4,5,7,
			7,5,6,
			8,9,11,
			11,9,10,
			12,13,15,
			15,13,14,	
			16,17,19,
			19,17,18,
			20,21,23,
			23,21,22

	};

}
