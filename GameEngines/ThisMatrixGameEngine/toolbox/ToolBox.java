package toolbox;

import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class ToolBox {

	public static TexturedModel createTexturedModel(Loader loader, String objPath, String texturePath, boolean hasTransparency, boolean useFakeLighting, float shineDamper, float reflectivity){
		TexturedModel model = new TexturedModel(OBJLoader.loadObjModel(
				objPath, loader), new ModelTexture(
				loader.loadTexture(texturePath)));
		model.getTexture().setHasTransparency(hasTransparency);
		model.getTexture().setUseFakeLighting(useFakeLighting);
		ModelTexture texture = model.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		return model;
	}
}
