package com.infagen.renderEngines;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.infagen.gameObject.GameObject;
import com.infagen.model.TexturedModel;
import com.infagen.shaders.StaticShader;

public class MasterRenderer {

	private StaticShader shader = new StaticShader();
	private Renderer renderer = new Renderer(shader);
	
	private Map<TexturedModel, List<GameObject>> gameObjects = new HashMap<TexturedModel, List<GameObject>>();
	
	public void render(Light light, Camera camera){
		renderer.prepare();
		shader.start();
		shader.loadLight(light);
		shader.loadViewMatrix(camera);
		
		
		shader.stop();
		gameObjects.clear();
	}
	
	public void cleapUP(){
		shader.cleanUp();
	}
}
