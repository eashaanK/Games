package main;

import java.util.Set;
import java.util.TreeMap;

import processing.core.PApplet;
import ref.Ref;
import chunk.Chunk;

public class MainChunkRendering extends PApplet
{

	TreeMap<String, Chunk> chunks = new TreeMap<String, Chunk>();
	
	public void setup(){
		for(int r = 0; r < 10; r++){
			for(int c = 0; c < 10; c++){
				chunks.put(chunks.toString(), new Chunk(r * Ref.CHUNK_SIZE, c * Ref.CHUNK_SIZE));
			}
		}
	}
	
	public void draw(){
		//Set<String> keySet = chunks.keySet();
	}
}
