package cb2Drpg_images;

import java.util.ArrayList;
import java.util.List;

public class Material {

	public static final Material GRASS = new Material("grass");
	
	private List<String> resourceIds;
	
	public Material(String resourceID){
		resourceIds = new ArrayList<String>();
		resourceIds.add(resourceID);
	}
	
	public String getResourceId(int i){
		return this.resourceIds.get(i);
	}
	
	public String getRandomResourceId(){
		return this.resourceIds.get((int)(Math.random() * resourceIds.size()));
	}
}
