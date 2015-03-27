package com.base.engine;

import org.lwjgl.util.vector.Vector3f;

import com.base.input.Input;
import com.base.rendering.Mesh;
import com.base.rendering.Vertex;
import com.base.shader.Shader;

public class Game {

	private Mesh mesh;
	private Shader shader;
	
	public Game(){
		shader = new Shader();
		mesh = new Mesh();
		
		Vertex[] data = new Vertex[] {new Vertex(new Vector3f(-1, -1, 0)),
												   new Vertex(new Vector3f(0, 1, 0)),
												   new Vertex(new Vector3f(1, -1, 0)),};
		
		mesh.addVertices(data);
		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vp"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fp"));
		shader.compileShader();

	}
	
	public void input(){
		if(Input.GetMouseUp(0))
			System.out.println("LOL: " + Input.GetMousePosition());

	}
	
	public void update(float delta){
	}
	
	public void render(){
		
		shader.bind();
		mesh.draw();
	}
}
