package toolbox;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;

import renderEngine.Loader;
import renderEngine.ModelData;
import renderEngine.OBJFileLoader;
import textures.ModelTexture;

public class ToolBox {

	private TexturedModel fernModelWithTextureAtlas;
	
	public ToolBox(Loader loader){
		fernModelWithTextureAtlas = initAModelWithTexturedAtlas(loader, "fern", "fern");
	}
	
	////Creates a Textured Model to be used in an ENtity's parameter
	private TexturedModel initAModelWithTexturedAtlas(Loader loader, String textureAtlasName, String modelName){
		ModelTexture fernTextureAtlas = new ModelTexture(loader.loadTexture(textureAtlasName), 2);
		ModelData data = OBJFileLoader.loadOBJ(modelName);
		RawModel rawModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		TexturedModel model = new TexturedModel(rawModel, fernTextureAtlas);
		return model;
	}
	
	public TexturedModel getFernTexturedModel(){
		return fernModelWithTextureAtlas;

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
