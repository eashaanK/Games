package com.infagen.shaders;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/com/infagen/shaders/vertex_program.vp";
	private static final String FRAGMENT_FILE = "src/com/infagen/shaders/fragment_program.fp";


	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
	}

}
