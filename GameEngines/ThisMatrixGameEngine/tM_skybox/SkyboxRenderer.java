package tM_skybox;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import tM_entities.Camera;
import tM_entities.Light;
import tM_models.RawModel;
import tM_renderEngine.DisplayManager;
import tM_renderEngine.Loader;

public class SkyboxRenderer {

private static final float SIZE = 500f;
	
	private static final float[] VERTICES = {        
	    -SIZE,  SIZE, -SIZE,
	    -SIZE, -SIZE, -SIZE,
	    SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	    -SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE
	};
	
	private static String[] TEXTURE_FILES = {"right", "left", "top", "bottom", "back", "front"};
	private static String[] NIGHT_TEXTURE_FILES = {"nightRight", "nightLeft", "nightTop", "nightBottom", "nightBack", "nightFront"};

	
	private RawModel cube;
	
	private int texture;
	private int nightTexture;

	private float time = 0;
	
	private SkyboxShader shader;
	
	private Light sun;
	
	
	public SkyboxRenderer(Loader loader, Matrix4f projectionMatrix, Light sun){
	
		this.sun = sun;
		
		cube = loader.loadToVAO(VERTICES, 3);
		texture = loader.loadCubeMap(TEXTURE_FILES);
		nightTexture = loader.loadCubeMap(NIGHT_TEXTURE_FILES);
		shader = new SkyboxShader();
		shader.start();
		shader.connectTextureUnits();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}
	
	public void render(Camera camera){
		shader.start();
		shader.loadViewMatrix(camera);
		shader.loadFogColor(currentFogColor.x, currentFogColor.y, currentFogColor.z);
		GL30.glBindVertexArray(cube.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		bindTextures();
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, cube.getVertexCount());
		GL20.glDisableVertexAttribArray(0);
		GL30.glBindVertexArray(0);
		shader.stop();
	}
	
	private final float day = 24000;
	private float midNight = 6000;
	private float dawn = 12000;
	private float noon = 18000;
	private float evening = day;
	
	private final Vector3f dayFogColor = new Vector3f(0.8f, 0.8f, 0.8f);
	private final Vector3f nightFogColor = new Vector3f(0, 0, 0);
	
	public Vector3f currentFogColor = nightFogColor;



	
	private void bindTextures(){
		//time += DisplayManager.getFrameTimeSeconds() * 1000;
		time %= day;
		int texture1;
		int texture2;
		float blendFactor;		
		if(time >= 0 && time < midNight){ //midNight
			texture1 = nightTexture;
			texture2 = nightTexture;
			blendFactor = (time - 0)/(midNight - 0);
			
			currentFogColor = this.nightFogColor;

			
		}else if(time >= midNight && time < dawn){ //Dawn
			texture1 = nightTexture;
			texture2 = texture;
			blendFactor = (time - midNight)/(dawn - midNight);
		}else if(time >= dawn && time < noon){ //NOON
			texture1 = texture;
			texture2 = texture;
			blendFactor = (time - dawn)/(noon - dawn);
			
			currentFogColor = this.dayFogColor;
			
		}else{ //Evening
			texture1 = texture;
			texture2 = nightTexture;
			blendFactor = (time - noon)/(evening - noon);
		}
		
		//set color here
		//System.out.println(blendFactor);
		

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture1);
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture2);
		shader.loadBlendFactor(blendFactor);
	}
	
}
