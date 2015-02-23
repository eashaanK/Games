package renderEngine;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Renderer {

	
	public void prepare(){
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(1, 0, 0, 0);

	}
	
	public void render(RawModel model){
		GL30.glBindVertexArray(model.getVaoID());
		GL20.glEnableVertexAttribArray(0); //we put our data in list 0 for all the positions
		GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0); //renders with indices.
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
	}
}
