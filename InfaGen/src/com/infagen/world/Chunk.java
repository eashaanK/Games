package com.infagen.world;

import com.infagen.gameObject.Terrain;
import com.infagen.loaders.Loader;
import com.infagen.renderEngines.MasterRenderer;
import com.infagen.texture.ModelTexture;

public class Chunk {

	private Terrain terrain;
	
	private final int row, col;
	
	
	public Chunk(int r, int y, Loader loader){
		this.row = r;
		this.col = y;
		terrain = new Terrain(r, y, loader, new ModelTexture(loader.loadTexture("grass")));
		terrain.getTranform().rotateBy(0, 270, 0);
	}
	
	public String getKey(){
		return row + ", " + col;
	}
	
	public void render(MasterRenderer renderer){
		renderer.processTerrain(terrain);
	}
	
	
	/**
	 * Updates the chunk based on its contents
	 */
	private void updateChunk(){
		
	}
	/**
	 * Send the calculated mesh info to the 
	 * mesh and collision components
	 */
	private void renderMesh(){
		
	}
}
