package tM_shaders;

import java.util.List;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import tM_entities.Camera;
import tM_entities.Light;
import tM_toolbox.Maths;

public class TerrainShader extends ShaderProgram{
	
	public static final int MAX_LIGHTS = 4;
	
	private static final String VERTEX_FILE = "ThisMatrixGameEngine/tM_shaders/TerrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "ThisMatrixGameEngine/tM_shaders/TerrainFragmentShader.txt";

	private int location_transformationMatrix; 
	private int location_projectionMatrix; 
	private int location_viewMatrix;
	private int[] location_lightPosition;
	private int[] location_lightColor;
	private int location_attentuation[];

	private int location_shineDamper;
	private int location_reflectivity;
	
	private int location_backgroundTexture;
	private int location_rTexture;
	private int location_gTexture;
	private int location_bTexture;
	private int location_blendMap;

	private int location_skyColor;


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

		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		
		location_backgroundTexture = super.getUniformLocation("backgroundTexture");
		location_rTexture = super.getUniformLocation("rTexture");
		location_gTexture = super.getUniformLocation("gTexture");
		location_bTexture = super.getUniformLocation("bTexture");
		location_blendMap = super.getUniformLocation("blendMap");

		location_skyColor =  super.getUniformLocation("skyColor");
		
		location_lightPosition = new int[MAX_LIGHTS];
		location_lightColor = new int[MAX_LIGHTS];
		location_attentuation = new int[MAX_LIGHTS];
		for(int i = 0; i < MAX_LIGHTS; i++){
			location_lightPosition[i] = super.getUniformLocation("lightPosition[" + i + "]");
			location_lightColor[i] = super.getUniformLocation("lightColor[" + i + "]");
			location_attentuation[i] = super.getUniformLocation("attentuation[" + i + "]");
		}

	}
	
	public void loadSkyColor(float r, float g, float b){
		super.loadVector(location_skyColor, new Vector3f(r, g, b));
	}
	
	public void connectTextureUnits(){
		super.loadInt(location_backgroundTexture, 0);
		super.loadInt(location_rTexture, 1);
		super.loadInt(location_gTexture, 2);
		super.loadInt(location_bTexture, 3);
		super.loadInt(location_blendMap, 4);

	}
	
	public void loadShineVariable(float damper, float reflectivity){
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);

	}
	
	public void loadLights(List<Light> lights){
		for(int i = 0; i < MAX_LIGHTS; i++){
			if(i < lights.size()){
				super.loadVector(location_lightPosition[i], lights.get(i).getPos());
				super.loadVector(location_lightColor[i], lights.get(i).getColor());
				super.loadVector(location_attentuation[i], lights.get(i).getAttentuation());
			}
			else{
				super.loadVector(location_lightPosition[i], new Vector3f(0, 0, 0));
				super.loadVector(location_lightColor[i], new Vector3f(0, 0, 0));
				super.loadVector(location_attentuation[i], new Vector3f(1, 0, 0));
			}
		}
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
