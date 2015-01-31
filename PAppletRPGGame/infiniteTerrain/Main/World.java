package Main;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import processing.core.PApplet;

/**
 * == Map
 * Only a T_AROUND_PLAYER * T_AROUND_PLAYER terrains r loaded at one time
 * @author IPA
 *
 */

public class World {

	float T_AROUND_PLAYER = 5;
	
	public List<Terrain> loadedTerrains = new CopyOnWriteArrayList<Terrain>();
	public Player player;
	
	public World(Player player){
		this.player = player;
		for(int r = 0; r < 10; r++){
			for(int c = 0; c < 10; c++){
				this.loadedTerrains.add(new Terrain(r * Terrain.SIZE, c * Terrain.SIZE));
			}
		}
		this.checkChunks();

	}
	
	/**
	 * Figure out chunks to load and unload
	 * smarter to unload chunks to minimize elements in array
	 */

	public void checkChunks(){
		//float playeChunkX = player.getX()/TILES_AMOUNT_X;
		//float playeChunkY = player.getY()/TILES_AMOUNT_Y;
		//System.out.println(playeChunkX + " " + playeChunkY);
		
		//unload chunks
		this.unloadChunks(player.getX(), player.getY());
		
		//load chunks
		//this.loadTerrains(player.getX(), player.getY());
	}
	
	private void unloadChunks(float pCX, float pCY){
		for(Terrain chunk: loadedTerrains){
			//too chunk is too far out of bounds
			boolean isTooFarRight = chunk.getTerrainX() > this.T_AROUND_PLAYER * Terrain.SIZE + pCX;
			boolean isTooFarLeft= chunk.getTerrainX() < this.T_AROUND_PLAYER * Terrain.SIZE +  pCX;

		//	boolean isTooFarUp= chunk.getTerrainY() < this.T_AROUND_PLAYER * pCY;
		//	boolean isTooFarDown = chunk.getTerrainY() > this.T_AROUND_PLAYER * pCY;


			if( isTooFarRight){
				System.out.println("unload chunk " + chunk.getTerrainX() + " " + chunk.getTerrainY());
				loadedTerrains.remove(chunk);
			}
		}
	}
	
	/*private void loadTerrains(float pCX, float pCY){
		for(float x = pCX - (CHUNK_AMT_X - 1)/2; x  <= pCX + (CHUNK_AMT_X - 1)/2; x++){
			for(float y = pCY - (CHUNK_AMT_Y - 1)/2; y  <= pCY + (CHUNK_AMT_Y - 1)/2; y++){
				if(!loadedTerrains.contains(new Terrain(x, y))){ 
					System.out.println("Load Terrain");
					Terrain Terrain = new Terrain(x, y); 
					// TODO: make Terrain all pretty with gameObjects
					Terrain.populate();
					loadedTerrains.add(Terrain);
				}
			}
		}
	}*/
	
	public void render(PApplet p){		
		renderBackground(p);
		
		renderForeground(p);
		
		if(p.keyPressed){
			if(p.key == 'w'){
				System.out.println("up");
			}
		}
	}
	
	public void update(){
		player.moveBy(0.5f, 0f);
		this.checkChunks();

	}

	private void renderForeground(PApplet p) {
		p.fill(255, 0, 0);
		p.rect(player.getX(), player.getY(), player.getWidth(), player.getHeight());		
	}

	private void renderBackground(PApplet p) {
		for(Terrain t: loadedTerrains){
			t.render(p);
		}
	}
}
