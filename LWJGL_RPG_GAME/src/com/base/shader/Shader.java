package com.base.shader;

import static org.lwjgl.opengl.GL20.*;

import static org.lwjgl.opengl.GL32.GL_GEOMETRY_SHADER;

public class Shader {

	private int program;

	public Shader() {
		program = glCreateProgram();

		if (program == 0) {
			System.err.println("Shader> Could not create a valid shader");
			System.exit(1);
		}
	}
	
	public void bind(){
		glUseProgram(program);
	}

	public void addVertexShader(String text) {
		addProgram(text, GL_VERTEX_SHADER);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL_FRAGMENT_SHADER);

	}

	public void addGeometryShader(String text) {
		addProgram(text, GL_GEOMETRY_SHADER);

	}
	
	public void compileShader(){
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS) == 0){
			System.err.println("Linking Fail: " + glGetShaderInfoLog(program, 1024));
		}
		glValidateProgram(program);
		if(glGetProgrami(program, GL_VALIDATE_STATUS) == 0){
			System.err.println("Validating Fail: " + glGetShaderInfoLog(program, 1024));
		}
	}
	
	private void addProgram(String text, int type){
		int shader = glCreateShader(type);
		
		if(shader == 0){
			System.err.println("Shader> Could not create a valid shader when adding shader");
			System.exit(1);
		}
		
		glShaderSource(shader, text);
		glCompileShader(shader);
		if(glGetShaderi(shader, GL_COMPILE_STATUS) == 0){
			System.err.println("Compiling fail: " + glGetShaderInfoLog(shader, 1024));
		}
		
		glAttachShader(program, shader);
	}
}
