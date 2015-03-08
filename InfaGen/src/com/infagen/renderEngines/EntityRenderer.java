package com.infagen.renderEngines;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import com.infagen.gameObject.GameObject;
import com.infagen.helpers.Maths;
import com.infagen.model.RawModel;
import com.infagen.model.TexturedModel;
import com.infagen.shaders.StaticShader;
import com.infagen.texture.ModelTexture;

public class EntityRenderer {
	
	private StaticShader shader;

	
	public EntityRenderer(StaticShader shader, Matrix4f projectionMatrix){
		 this.shader = shader;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	
	
	public void render(Map<TexturedModel, List<GameObject>> gameObjects){
		for(TexturedModel model: gameObjects.keySet()){
			this.prepareTexturedModel(model);
			List<GameObject> batch = gameObjects.get(model);
			for(GameObject g : batch){
				this.prepareInstance(g);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			
			this.unbindTexturedModel();
		}
	}
	
	private void prepareTexturedModel(TexturedModel model){
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		
		GL20.glEnableVertexAttribArray(0); //inable the shit u do in Static Shader "bind attributes"
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);

		ModelTexture texture = model.getTexture();
		shader.loadShineVariable(texture.getShineDamper(), texture.getReflectivity());
		
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}
	
	private void unbindTexturedModel(){
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);

		
		GL30.glBindVertexArray(0);
	}
	
	private void prepareInstance(GameObject gameObject){
		Matrix4f transformationMatrix = Maths.createTransformationMatrix(gameObject.getTransform());
		shader.loadTransformationMatrix(transformationMatrix);
	}
	
	
	
	
}
