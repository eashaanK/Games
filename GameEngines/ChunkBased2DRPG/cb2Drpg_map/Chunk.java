package cb2Drpg_map;

import cb2Drpg_images.Material;
import cb2Drpg_noise.SimplexNoise;
import cb2Drpg_refereces.Ref;

/**
 * == terrain tiles == hieght
 * 
 * @author eashaan
 * 
 */
public class Chunk {

	private int chunkX;
	private int chunkY;

	public int id = (int) (Math.random() * 10);

	public Tile[][] tiles;

	public Chunk(int chunkX, int chunkY) {
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		// System.out.println("ChunkX" + chunkX);
	}

	public void populate() {
		tiles = new Tile[Ref.TILES_AMOUNT_X][Ref.TILES_AMOUNT_Y];
		/*
		 * for(int i = 0; i < tiles.length; i++){ for(int j = 0; j <
		 * tiles[0].length;j++){ tiles[i][j] = new Tile(Material.GRASS); } }
		 */
		SimplexNoise simplexNoise = new SimplexNoise(7, 0.1);

		double xStart = this.chunkX * Ref.TILES_AMOUNT_X;
		double xEnd = xStart + Ref.TILES_AMOUNT_X;
		double yStart = this.chunkY * Ref.TILES_AMOUNT_Y;
		double yEnd = yStart + Ref.TILES_AMOUNT_Y;

		int xResolution = Ref.TILES_AMOUNT_X;
		int yResolution = Ref.TILES_AMOUNT_Y;

		double[][] data = new double[xResolution][yResolution];

		for (int i = 0; i < xResolution; i++) {
			for (int j = 0; j < yResolution; j++) {
				int x = (int) (xStart + (i * (xEnd - xStart) / xResolution));
				int y = (int) (yStart + (j * (yEnd - yStart) / yResolution));
				
				double noise = (1 + simplexNoise.getNoise(x, y)) / 2;

				Material material;
				if (noise < 0.465F)
					material = Material.WATER_DEEP;
				else if (noise < 0.475F)
					material = Material.WATER;
				else if (noise < 0.485F)
					material = Material.SAND;
				else {
					if (noise < 0.5051)
						material = Material.DIRT;
					else
						material = Material.GRASS;

				}
				tiles[i][j] = new Tile(material);

				data[i][j] = noise;
			}
		}

		// ImageWriter.greyWriteImage(data);
	}

	public int getChunkX() {
		return chunkX;
	}

	public int getChunkY() {
		return chunkY;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Object))
			return false;
		Chunk other = (Chunk) o;
		return (this.chunkX == other.chunkX) && (chunkY == other.chunkY);
	}

}
