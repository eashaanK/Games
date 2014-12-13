package Components;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Sprite extends PApplet{

	private ArrayList<PImage> sprites;
	private final int MAX_COUNT, INC;
	private int currentCount, index;
	
	public Sprite(int max, int INC){
		this.MAX_COUNT = max;
		this.INC = INC;
	}
	
	public void addImage(PImage i){
		this.sprites.add(i);
	}
	
	public void update(){
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
	}
	
	public PImage nextImage(){
		return sprites.get(index);
	}
}
