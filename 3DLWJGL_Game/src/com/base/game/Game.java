package com.base.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import com.base.engine.Mesh;
import com.base.engine.ResourceLoader;
import com.base.engine.Shader;
import com.base.engine.Vertex;
import com.base.input.Input;

public class Game {
	
	Mesh mesh;
	Shader shader;
	
	public Game(){
		mesh = new Mesh();
		Vertex[] data = new Vertex[]{new Vertex(new Vector3f(-1, -1, 0)),
												  new Vertex(new Vector3f(0, 1, 0)),
												  new Vertex(new Vector3f(1, -1, 0)),};
		
		mesh.addVertices(data);

	
		
		shader = new Shader();
		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vp"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fp"));
		shader.compileShader();

	}
	
	public void input(){
		if(Input.getKeyDown(Keyboard.KEY_UP))
			System.out.println("LOL");
	}
	
	public void update(){
		
	}
	
	public void render(){
		shader.bind();
		mesh.draw();
		shader.unBind();
	}
}
