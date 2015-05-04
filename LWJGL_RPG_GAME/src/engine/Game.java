package engine;

import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;


public class Game {

	private Loader loader;
	
	float[] vertices = {
		//Left bottom triangle
		-0.5f, 0.5f, 0f,
		-0.5f, -0.5f, 0f,
		0.5f, -0.5f, 0f,
		//Right Top Triangle
		0.5f, -0.5f, 0f,
		0.5f, 0.5f, 0f,
		-0.5f, 0.5f, 0f,
	};
	
	RawModel model;
	
	public Game(Loader loader) {
		this.loader = loader;
		
		model = loader.loadToVAO(vertices);
		
	}

	public void input() {

	}

	public void update() {
	}

	public void render(Renderer renderer) {
		renderer.render(model);
	}

	public void cleanUp() {
		
	}

}
