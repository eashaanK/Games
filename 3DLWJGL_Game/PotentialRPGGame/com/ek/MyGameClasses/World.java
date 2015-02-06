package com.ek.MyGameClasses;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import com.ek.core.Game;
import com.ek.core.Transform;

public class World {

//	public HashMap<String, Chunk> allChunks = new HashMap<String, Chunk>();
	public List<Chunk> allChunks = new ArrayList<Chunk>();

	
	public static final float distanceFromPlayer = 5f;
	
	public World(Game g){
		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				Chunk chunk = new Chunk(x, y);
				//allChunks.put(chunk.getHashMapPointer(), chunk);
				allChunks.add(chunk);
				chunk.addSelf(g);
			}
		}
	}
	
	public void update(Transform t){
		unload(t);
	}
	
	private void unload(Transform t){

		for(int i = allChunks.size() - 1; i >=0 ; i--){
			float cZ = allChunks.get(i).plane.GetTransform().GetPos().GetZ(); 
			float cX = allChunks.get(i).plane.GetTransform().GetPos().GetX(); 

			Point2D cP = new Point();
			cP.setLocation(cX, cZ);
			float dis = (float) cP.distance(t.GetPos().GetX(), t.GetPos().GetZ());
			
			System.out.println(dis);
		}
		
		//System.out.println(Math.floor(t.GetPos().GetX() / Chunk.SIZE));
	}
}
