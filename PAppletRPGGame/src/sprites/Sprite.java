package sprites;

import java.util.ArrayList;

import processing.core.PImage;

public class Sprite{

	private ArrayList<PImage> sprites = new ArrayList<PImage>();
	public float MAX_COUNT, INC;
	private float currentCount;
	private int index;
	
	public Sprite(float max, float f){
		this.MAX_COUNT = max;
		this.INC = f;
	}
	
	public void addImage(PImage i){
		this.sprites.add(i);
	}
	
	public float update(){
		if(currentCount >= MAX_COUNT){
			currentCount = 0;
			index++;
			if(index >= sprites.size())
				index = 0;
		}
		if(currentCount < MAX_COUNT)
		{
			currentCount += INC;
		}
		return currentCount;
	}
	
	public PImage nextImage(){
		return sprites.get(index);
	}
}
