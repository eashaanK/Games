package com.infagen.core;

import com.infagen.loaders.Loader;
import com.infagen.model.RawModel;
import com.infagen.renderEngines.Renderer;

public class Game {
	
	private Loader loader;
	
	private RawModel model;
	
	public Game(Loader loader){
		this.loader = loader;
		init();
	}
	
	private void init(){
		float[] vertices = {
				-0.5f, 0.5f, 0f, 
				-0.5f, -0.5f, 0f, 
				0.5f, -0.5f, 0f, 
				
				0.5f, -0.5f, 0f, 
				0.5f, 0.5f, 0f, 
				-0.5f, 0.5f, 0f,
		};
		
		model = loader.loadToVao(vertices);
	}

	public void update(Renderer renderer) {
		renderer.render(model);
	}

	public void close() {
	}

}
