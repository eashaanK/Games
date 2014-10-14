package gameEngine;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;


public class Mesh {

	private int vbo, vao; //pointer
	private int ibo;
	private int size; //how mush data in pointer
	
	public Mesh(){
		vbo = glGenBuffers();
		vao = glGenVertexArrays(); //new
		ibo = glGenBuffers();
		size = 0;
	}
	
	public void addVetrices(Vertex[] vertices, int[] indices){
		glBindVertexArray(vao);
		
		size = indices.length; //how big one vertex is

		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(vertices), GL_STATIC_DRAW);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);
		
	}
	
	public void draw(){
		
		glEnableVertexAttribArray(0);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);
		
		glDisableVertexAttribArray(0);
	}
	
	public String toString(){
		return "Mesh";
	}
}
