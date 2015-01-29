package cb2Drpg_map;

import cb2Drpg_images.Material;
import cb2Drpg_refereces.Ref;

public class Chunk {

	private int chunkX;
	private int chunkY;
	
	Tile[][] tiles;
	
	public Chunk(int chunkX, int chunkY) {
		this.chunkX = chunkX;
		this.chunkY = chunkY;
	}

	public void populate(){
		tiles = new Tile[Ref.TILES_PER_CHUNK_X][Ref.TILES_PER_CHUNK_Y];
		//noise generation
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[x].length; y++){
				tiles[x][y] = new Tile(Material.GRASS);
			}
		}
	}
	
	public int getChunkX() {
		return chunkX;
	}

	public int getChunkY() {
		return chunkY;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Object))
			return false;
		Chunk other = (Chunk)o;
		return (this.chunkX == other.chunkX) && (chunkY == other.chunkY);
	}
	
	
}
