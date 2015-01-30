package Main;

import processing.core.PApplet;


/**
 * chunk
 * @author IPA
 *
 */
public class Terrain {

	public static final float SIZE = 50f;
	private float chunkX;
	private float chunkY;
	
	private float r, g, b; 
	
	public Terrain(float chunkX, float chunkY) {
		this.chunkX = chunkX;
		this.chunkY = chunkY;
		r = (float)(Math.random() * 255);
		g = (float)(Math.random() * 255);
		b = (float)(Math.random() * 255);

	}
	
	public void render(PApplet p){
		p.stroke(10);
		p.fill(r, g, b);
		p.rect(chunkX, chunkY, SIZE, SIZE);
	}
	
	public void populate(){

	}
	
	public float getTerrainX() {
		return chunkX;
	}

	public float getTerrainY() {
		return chunkY;
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Object))
			return false;
		Terrain other = (Terrain)o;
		return (this.chunkX == other.chunkX) && (chunkY == other.chunkY);
	}

}
