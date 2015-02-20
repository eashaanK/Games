package core;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import window.Window;

public class ShaderDemo {

	public static void main(String[] args){
		Window.createWindow();
		
		int shaderProgram = glCreateProgram(); //creates a pair of shaders
		int vertexShader = glCreateShader(GL_VERTEX_SHADER);
		int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		
		StringBuilder vertexShaderSource = new StringBuilder();
		StringBuilder fragmentShaderSource = new StringBuilder();

		//vertexShader
		try{
			BufferedReader reader = new BufferedReader(new FileReader("AdvancedRenderingAndAudio/shaders/VertexDemo.vp"));
			String line;
			while( (line = reader.readLine()) != null){
				vertexShaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e){e.printStackTrace(); Window.closeWindow();}
		
		System.out.println(vertexShaderSource);
		
		//fragMent shader
		try{
			BufferedReader reader = new BufferedReader(new FileReader("AdvancedRenderingAndAudio/shaders/FragmentDemo.fp"));
			String line;
			while( (line = reader.readLine()) != null){
				fragmentShaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e){e.printStackTrace(); Window.closeWindow();}
		
		System.out.println(fragmentShaderSource);
		
		//compile the shaders
		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		if(glGetShaderi(vertexShader, GL_COMPILE_STATUS) == GL11.GL_FALSE){
			System.err.println("Vertex Shader was not able to compile");
		}
		
		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if(glGetShaderi(fragmentShader, GL_COMPILE_STATUS) == GL11.GL_FALSE){
			System.err.println("Fragment Shader was not able to compile");
		}
		
		//attach the shaders
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		//Link the program
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
		
		while(!Display.isCloseRequested()){
			
			updateOPENGL();
			//tell opengl to use this program
			glUseProgram(shaderProgram);
			
			//draw shit
			glBegin(GL_TRIANGLES);
				glColor3f(1, 0, 0);
				glVertex2f(-0.5f, -0.5f);
				glColor3f(0, 1, 0);
				glVertex2f(0.5f, -0.5f);
				glColor3f(0, 0, 1);
				glVertex2f(0, 0.5f);
			glEnd();
			//disable the program
			glUseProgram(0);
			
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		//cleanup
		glDeleteProgram(shaderProgram);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
		
		
		Window.closeWindow();
	}

	private static void updateOPENGL() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	
}
