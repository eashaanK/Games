package cb2Drpg_map;

import java.awt.Image;

import cb2Drpg_images.Material;
import cb2Drpg_images.TextureManager;

public class Tile {

	public Material material;
	public Image texture;
	
	public Tile(Material material){
		this.material = material;
		this.texture = TextureManager.loadTexture(this.material.getRandomResourceId());
	}
}
