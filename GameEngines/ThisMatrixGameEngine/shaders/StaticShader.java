package shaders;

import org.lwjgl.util.vector.Matrix4f;

import toolbox.Maths;
import entities.Camera;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "ThisMatrixGameEngine/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "ThisMatrixGameEngine/shaders/fragmentShader.txt";

	private int location_transformationMatrix; 
	private int location_projectionMatrix; 
	private int location_viewMatrix;
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	/**
	 * The Attributes MUST MATCH the input variables
	 * in the vertex shader since this method passes these to
	 * the vertex shader
	 */
	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");

	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix"); //get the location of the ur uniform variables
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");

	}
	
	public void loadTransformationMatrix(Matrix4f matrix){
		super.loadMatrix(location_transformationMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f projection){
		super.loadMatrix(location_projectionMatrix, projection);
	}
	
	public void loadViewMatrix(Camera camera){
		Matrix4f viewMatrix  = Maths.createViewMatrix(camera);
		super.loadMatrix(location_viewMatrix, viewMatrix);
	}

}
