package shaders;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "ThisMatrixGameEngine/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "ThisMatrixGameEngine/shaders/fragmentShader.txt";

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
	
	

}
