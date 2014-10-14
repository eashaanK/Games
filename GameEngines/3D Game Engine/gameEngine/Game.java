package gameEngine;

import org.lwjgl.input.Keyboard;

public class Game {

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	private Camera camera;

	public Game() {
		mesh = new Mesh(); //ResourceLoader.loadMesh("box.obj");
		shader = new Shader();
		camera = new Camera();

		Vertex[] vertices = new Vertex[] { new Vertex(new Vector3f(-1, -1, 0)),
									   new Vertex(new Vector3f(0, 1, 0)),
									   new Vertex(new Vector3f(1, -1, 0)), 
									   new Vertex(new Vector3f(0, -1, 1)),};
		
		
		int[] indices = new int[]{0, 1, 3,
								  3, 1, 2,
								  2, 1, 0,
								  0, 2, 3,};
		
		mesh.addVetrices(vertices, indices);
		
		transform.setProjection(70, Window.getWidth(), Window.getHeight(), 0.01f, 1000f);
		Transform.setCamera(camera);
		transform = new Transform();

		shader.addVertexShader(ResourceLoader.loadShader("basicVertex.vp"));
		shader.addFragmentShader(ResourceLoader.loadShader("basicFragment.fp"));
		shader.compileShader();
		
		shader.addUniform("transform");

		
	}

	public void input() {
		camera.input();
	}

	float temp = 0.0f;
	
	public void update() {
		temp += Time.getDelta();
		shader.bind();
		
		float sinTemp = (float)Math.sin(temp);
		
		
		transform.setTranslation(sinTemp, 0, 5);
		transform.setRotation(0, sinTemp * 180, 0);
		System.out.println(camera.getForward());
		//transform.setScale(0.7f * sinTemp, 0.7f * sinTemp, 0.7f * sinTemp);
		
	}

	public void render() {
		shader.bind();
		shader.setUniform("transform", transform.getProjectedTransformation());
		mesh.draw();
	}

}
