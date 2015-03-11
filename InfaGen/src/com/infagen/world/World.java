package com.infagen.world;

import java.util.Set;
import java.util.TreeMap;

import com.infagen.helpers.Ref;
import com.infagen.loaders.Loader;
import com.infagen.noise.SimplexNoise;
import com.infagen.renderEngines.MasterRenderer;

public class World {

	private static int largestFeature = (int)(Math.random() * 100);
	private static double persistence = Math.random() * 0.3;	
	public static  SimplexNoise testingNoise = new SimplexNoise(largestFeature, persistence, Ref.SEED);

	public TreeMap<String, Chunk> chunks = new TreeMap<String, Chunk>();
	
	public World(Loader loader){
	System.out.println("largestFeature: " + largestFeature + "\tpersistence: " + persistence + "\tseed: " + Ref.SEED);

		for(int x = 0; x < 10; x++){
			for(int y = 0; y < 10; y++){
				Chunk chunk = new Chunk(x, -y,loader);
				chunks.put(chunk.getKey(), chunk);
			}
		}
	}
	
	public void render(MasterRenderer renderer){
		Set<String> k = chunks.keySet();
		Object[] keySet = k.toArray();
		for(int i = 0; i < keySet.length; i++){
			String key = (String)keySet[i];
			Chunk c = chunks.get(key);
			c.render(renderer);
		}
	}
}
