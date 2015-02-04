package com.base.engine;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

public class Shader {

	private int program;

	public Shader() {
		this.program = GL20.glCreateProgram();

		if (program == 0) {
			System.err
					.println("Shader creation falied. Could not find valid memory loc in contructor");
			System.exit(1);
		}
		
	}
	
	/**
	 * Starts using the shaders
	 */
	public void bind(){
		GL20.glUseProgram(program);
	}
	
	public void unBind(){
		GL20.glUseProgram(0);
	}

	public void addVertexShader(String text) {
		addProgram(text, GL20.GL_VERTEX_SHADER);
		//System.out.println("Added vertex Shader: " + text);
	}

	public void addGeometryShader(String text) {
		addProgram(text, GL32.GL_GEOMETRY_SHADER);
		//System.out.println("Added geometry Shader: " + text);
	}

	public void addFragmentShader(String text) {
		addProgram(text, GL20.GL_FRAGMENT_SHADER);
		//System.out.println("Added Fragment Shader: " + text);
	}
	
	/**
	 * Takes all shaders into working shader program
	 * Checks two times if the shader has actually been compiled correctly
	 */
	public void compileShader(){
	
		GL20.glBindAttribLocation(program, 0, "position");
		
		GL20.glLinkProgram(program);
	
		if(GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == 0){
			System.err.println(GL20.glGetProgramInfoLog(program, 1024) + " Shader class 1");
			System.exit(1);
		}
		

		
		GL20.glValidateProgram(program);
		
		if(GL20.glGetProgrami(program, GL20.GL_VALIDATE_STATUS) == 0){
			System.err.println(GL20.glGetProgramInfoLog(program, 1024) + " Shader class 2");
			System.exit(1);
		}
		
		System.out.println("All of it works");
	}

	public void addProgram(String text, int type) {
		int shader = GL20.glCreateShader(type);
		
		if (shader == 0) {
			System.err.println("Shader creation falied. Could not find valid memory loc in when addingf shader in addProgram");
			System.exit(1);
		}
		
		GL20.glShaderSource(shader, text);
		GL20.glCompileShader(shader);
		
		if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == 0){
			System.err.println(GL20.glGetShaderInfoLog(shader, 1024));
			System.exit(1);
		}
		
		GL20.glAttachShader(program, shader);
	}

}
