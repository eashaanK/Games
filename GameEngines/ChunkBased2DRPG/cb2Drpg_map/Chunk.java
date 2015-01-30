package cb2Drpg_map;

import cb2Drpg_images.ImageWriter;
import cb2Drpg_images.Material;
import cb2Drpg_noise.SimplexNoise;
import cb2Drpg_refereces.Ref;

public class Chunk {

	private int chunkX;
	private int chunkY;
	
	public Tile[][] tiles;
	
	public Chunk(int chunkX, int chunkY) {
		this.chunkX = chunkX;
		this.chunkY = chunkY;
	}

	public void populate(){
		tiles = new Tile[Ref.TILES_AMOUNT_X][Ref.TILES_AMOUNT_Y];
		SimplexNoise simplexNoise = new SimplexNoise(7, 0.1);
		
		double xStart = 0;
		double xEnd = xStart + Ref.TILES_AMOUNT_X;
		double yStart = 0;
		double yEnd = yStart + Ref.TILES_AMOUNT_Y;
		
		int xResolution = 200;
		int yResolution = 200;
		
		double[][] data = new double[xResolution][yResolution];
		
		for(int i = 0; i < xResolution; i++){
			for(int j = 0; j < yResolution;j++){
				int x =(int)(xStart + (i*(xEnd - xStart)/xResolution));
				int y =(int)(yStart + (j*(yEnd - yStart)/yResolution));
				
				double noise = (1 + simplexNoise.getNoise(x, y))/2; //Making sure value between 0 and 1
				tiles[i][j] = new Tile(Material.GRASS);
				data[i][j] = noise;
			}
		}
		ImageWriter.greyWriteImage(data);
		
		
		//ImageWriter.greyWriteImage(data);
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
