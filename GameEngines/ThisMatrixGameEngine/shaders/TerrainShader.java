package shaders;

import org.lwjgl.util.vector.Matrix4f;

import toolbox.Maths;
import entities.Camera;
import entities.Light;

public class TerrainShader extends ShaderProgram{
	private static final String VERTEX_FILE = "ThisMatrixGameEngine/shaders/TerrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "ThisMatrixGameEngine/shaders/TerrainFragmentShader.txt";

	private int location_transformationMatrix; 
	private int location_projectionMatrix; 
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColor;
	
	private int location_shineDamper;
	private int location_reflectivity;


	public TerrainShader() {
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
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix"); //get the location of the ur uniform variables
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");

		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColor = super.getUniformLocation("lightColor");

		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
	}
	
	public void loadShineVariable(float damper, float reflectivity){
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);

	}
	
	public void loadLight(Light light){
		super.loadVector(location_lightPosition, light.getPos());
		super.loadVector(location_lightColor, light.getColor());
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