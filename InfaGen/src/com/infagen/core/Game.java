package com.infagen.core;

import com.infagen.loaders.Loader;
import com.infagen.model.RawModel;
import com.infagen.model.TexturedModel;
import com.infagen.renderEngines.Renderer;
import com.infagen.texture.ModelTexture;

/**
 * TUT 6
 * @author eashaan
 *
 */
public class Game {
	
	private Loader loader;
	
	private  TexturedModel betterModel;

	
	public Game(Loader loader){
		this.loader = loader;
		init();
	}
	
	private void init(){
		float[] vertices = {
				-0.5f, 0.5f, 0f, 
				-0.5f, -0.5f, 0f, 
				0.5f, -0.5f, 0f, 
				0.5f, 0.5f, 0f, 
		};
		
		int[] indices = {
				0, 1, 3, 
				3, 1, 2,
		};
		
		RawModel model = loader.loadToVao(vertices, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("crate"));
		
		betterModel = new TexturedModel(model, texture);
	}

	public void update(Renderer renderer) {
		renderer.render(betterModel);
	}

	public void close() {
	}

}
