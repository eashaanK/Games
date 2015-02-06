package com.ek.MyGameClasses;

import java.util.HashMap;

import com.ek.core.Game;
import com.ek.core.Transform;

public class World {

	public HashMap<String, Chunk> allChunks = new HashMap<String, Chunk>();
	
	public static final float distanceFromPlayer = 5f;
	
	public World(Game g){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				Chunk chunk = new Chunk(x, y);
				allChunks.put(chunk.getHashMapPointer(), chunk);
				chunk.addSelf(g);
			}
		}
	}
	
	public void update(Transform t){
		unload(t);
	}
	
	private void unload(Transform t){
		/*for(int i = allChunks.size() - 1; i >=0 ; i--){
			
		}*/
		
		System.out.println(Math.floor(t.GetPos().GetX() / Chunk.SIZE));
	}
}
