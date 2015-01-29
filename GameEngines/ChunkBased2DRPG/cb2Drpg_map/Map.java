package cb2Drpg_map;

import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cb2Drpg_gameObjects.Player;
import cb2Drpg_refereces.Ref;

public class Map {
	List<Chunk> loadedChunks = new CopyOnWriteArrayList<Chunk>();
	public Player player;
	
	
	public Map(Player player){
		this.player = player;
		this.checkChunks();
	}
	
	public void render(Graphics g){
		for(Chunk chunk: loadedChunks){
			//g.drawLine(100+chunk.getChunkX() * 5, 100+chunk.getChunkY() * 5, 100+chunk.getChunkX() * 5, 100+chunk.getChunkY() * 5);
			for(int x = 0; x < chunk.tiles.length; x++){
				for(int y= 0; y < chunk.tiles.length; y++){
					//g.drawImage(chunk.tiles[x][y].texture, x * Ref.TILE_SIZE*Ref.PIXEL_SIZE + chunk.getChunkX() * Ref.TILES_PER_CHUNK_X * Ref.TILE_SIZE * Ref.PIXEL_SIZE, y * Ref.TILE_SIZE*Ref.PIXEL_SIZE + chunk.getChunkY() * Ref.TILES_PER_CHUNK_Y* Ref.TILE_SIZE * Ref.PIXEL_SIZE, 16, 16,null);
					int xPos = Ref.TILE_SIZE  * Ref.PIXEL_SIZE + chunk.getChunkX() ;
					int yPos = Ref.TILE_SIZE * Ref.PIXEL_SIZE + chunk.getChunkY();
					g.drawImage(chunk.tiles[x][y].texture, xPos, yPos,  Ref.TILE_SIZE, Ref.TILE_SIZE, null);
				}
			}
		}
	}
	/**
	 * Figure out chunks to load and unload
	 * smarter to unload chunks to minimize elements in array
	 */
	public void checkChunks(){
		int playeChunkX = player.getX()/Ref.TILES_PER_CHUNK_X;
		int playeChunkY = player.getY()/Ref.TILES_PER_CHUNK_Y;
		
		//unload chunks
		this.unloadChunks(playeChunkX, playeChunkY);
		
		//load chunks
		this.loadChunks(playeChunkX, playeChunkY);
	}
	
	private void unloadChunks(int pCX, int pCY){
		for(Chunk chunk: loadedChunks){
			//too chunk is too far out of bounds
			if(chunk.getChunkX() > pCX + (Ref.CHUNK_AMT_X - 1)/2 || chunk.getChunkX() < pCX -(Ref.CHUNK_AMT_X - 1)/2 || chunk.getChunkY() > pCY + (Ref.CHUNK_AMT_Y - 1)/2 || chunk.getChunkY() < pCY - (Ref.CHUNK_AMT_Y - 1)/2){
				System.out.println("unload chunk");
				loadedChunks.remove(chunk);
			}
		}
	}
	
	private void loadChunks(int pCX, int pCY){
		for(int x = pCX - (Ref.CHUNK_AMT_X - 1)/2; x  <= pCX + (Ref.CHUNK_AMT_X - 1)/2; x++){
			for(int y = pCY - (Ref.CHUNK_AMT_Y - 1)/2; y  <= pCY + (Ref.CHUNK_AMT_Y - 1)/2; y++){
				if(!loadedChunks.contains(new Chunk(x, y))){ 
					System.out.println("Load Chunk");
					Chunk chunk = new Chunk(x, y); 
					// TODO: make chunk all pretty with gameObjects
					chunk.populate();
					loadedChunks.add(chunk);
				}
			}
		}
	}
}
