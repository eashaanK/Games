package tM_toolbox;

import org.lwjgl.input.Keyboard;

import tM_models.RawModel;
import tM_models.TexturedModel;
import tM_renderEngine.Loader;
import tM_renderEngine.ModelData;
import tM_renderEngine.OBJFileLoader;
import tM_textures.ModelTexture;

public class ToolBox {

	private TexturedModel fernModelWithTextureAtlas;
	private TexturedModel lampModelWithTextureAtlas;

	
	public ToolBox(Loader loader){
		fernModelWithTextureAtlas = initAModelWithTexturedAtlas(loader, "fern", "fern", 2);
		lampModelWithTextureAtlas = initAModelWithTexturedAtlas(loader, "lamp", "lamp", 1);

	}
	
	////Creates a Textured Model to be used in an ENtity's parameter
	private TexturedModel initAModelWithTexturedAtlas(Loader loader, String textureAtlasName, String modelName, int numRows){
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture(textureAtlasName), numRows);
		ModelData data = OBJFileLoader.loadOBJ(modelName);
		RawModel rawModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		TexturedModel model = new TexturedModel(rawModel, fernTextureAtlas);
		return model;
	}
	
	public TexturedModel getFernTexturedModel(){
		return fernModelWithTextureAtlas;
	}
	
	public TexturedModel getLampTexturedModel(){
		return lampModelWithTextureAtlas;

	}
	
	////WITHOUT A TEXTURE ATLAS
	public TexturedModel createTexturedModel(Loader loader, String objPath, String texturePath, boolean hasTransparency, boolean useFakeLighting, float shineDamper, float reflectivity){

		ModelData data = OBJFileLoader.loadOBJ(objPath);
		RawModel rawModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		TexturedModel model = new TexturedModel(rawModel, new ModelTexture(
				loader.loadTexture(texturePath)));
		model.getTexture().setHasTransparency(hasTransparency);
		model.getTexture().setUseFakeLighting(useFakeLighting);
		ModelTexture texture = model.getTexture();
		texture.setShineDamper(shineDamper);
		texture.setReflectivity(reflectivity);
		
		return model;
	}
	
	public TexturedModel createTexturedModel(Loader loader, String objPath, String texturePath, boolean hasTransparency, boolean useFakeLighting){
		return createTexturedModel(loader, objPath, texturePath, hasTransparency, useFakeLighting, 10, 1);
	}
	
	
	
	/**
	 * 0 if key was pressed
	 * 1 is key was released
	 * -1 is key was not touched
	 * @param key
	 * @return
	 */
	public byte getKeyStatus(int key){
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == key) {
		        	return 0; //pressed
		        }
		    }
		    else {
		        if (Keyboard.getEventKey() == key) {
		        	return 1; //released
		        }
		    }
		}
		return -1;
	}
}
