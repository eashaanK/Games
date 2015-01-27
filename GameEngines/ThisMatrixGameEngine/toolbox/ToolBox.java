package toolbox;

import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.ModelData;
import renderEngine.OBJFileLoader;
import textures.ModelTexture;

public class ToolBox {

	public static TexturedModel createTexturedModel(Loader loader, String objPath, String texturePath, boolean hasTransparency, boolean useFakeLighting, float shineDamper, float reflectivity){

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
	
	public static TexturedModel createTexturedModel(Loader loader, String objPath, String texturePath, boolean hasTransparency, boolean useFakeLighting){

		ModelData data = OBJFileLoader.loadOBJ(objPath);
		RawModel rawModel = loader.loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices());
		TexturedModel model = new TexturedModel(rawModel, new ModelTexture(
				loader.loadTexture(texturePath)));
		model.getTexture().setHasTransparency(hasTransparency);
		model.getTexture().setUseFakeLighting(useFakeLighting);
		ModelTexture texture = model.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		return model;
	}
}
