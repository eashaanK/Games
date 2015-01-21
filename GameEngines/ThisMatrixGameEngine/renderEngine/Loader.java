package renderEngine;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {

	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();

	/**
	 * Loads 3D model by storing the vertices of its triangles in a VAO with its own index
	 */
	public RawModel loadToVao(float [] positions){
		int vaoID = createVAO();
		this.storeDataInAttribList(0, positions);
		unbindVAO();
		return new RawModel(vaoID, positions.length/3);//dive cause each vertex has 3 points (x, ym, z
	}
	
	//first step
	private int createVAO(){
		int vaoID = GL30.glGenVertexArrays();
		this.vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
		
	}
	//second step
	private void storeDataInAttribList(int attribNumber, float [] data ){
		int vboID = GL15.glGenBuffers();
		this.vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = this.storeDataInFLoatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attribNumber, 3, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	//third step
	private void unbindVAO(){
		GL30.glBindVertexArray(0);
	}
	
	private FloatBuffer storeDataInFLoatBuffer(float[] data){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	public void cleanUp(){
		for(int vao: vaos){
			GL30.glDeleteVertexArrays(vao);
		}
		
		for(int vbo: vbos){
			GL15.glDeleteBuffers(vbo);
		}
	}
}
