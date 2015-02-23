package shaders;

public class StaticShader extends ShaderProgram{

	private static final String VERTEX_FILE = "src/basicShaders/vertexShader.vp";
	private static final String FRAGMENT_FILE = "src/basicShaders/fragmentShader.fp";

	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position"); //0 cause we are storing our positions there
	}

}
