package sprite;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;
import rpg_game_helpers.Loader;

public class Sprite {

	private ArrayList<PImage> up, down, left, right;
	private PImage currentPImage;
	private int uI, dI, lI, rI;
	private float currentImgCount, PImageChangeSpeed = 0.20f;
	private PApplet parent;
	
	public Sprite(PApplet p, float PImageChangeSpeed){
		this.parent = p;
		this.PImageChangeSpeed = PImageChangeSpeed;
		
		this.up = new ArrayList<PImage>();
		this.down = new ArrayList<PImage>();
		this.left = new ArrayList<PImage>();
		this.right = new ArrayList<PImage>();
	}
	
	public void initCurrentPImage(PImage img){
		currentPImage = img;
	}
	
	public void assignPImageUp(int index){
		this.currentPImage = up.get(index);
	}
	
	public void assignPImageDown(int index){
		this.currentPImage = down.get(index);

	}
	
	public void assignPImageLeft(int index){
		this.currentPImage = left.get(index);
		
	}
	
	public void assignPImageRight(int index){
		this.currentPImage = right.get(index);
	}
	
	public void addUp(String path){
		up.add(parent.loadImage(path));
	}
	
	public void addDown(String path){
		down.add(parent.loadImage(path));
	}
	
	public void addLeft(String path){
		left.add(parent.loadImage(path));
	}
	
	public void addRight(String path){
		right.add(parent.loadImage(path));
	}
	
	public void updatePImage(PImageDirection iDir) {
		switch (iDir) {
		case up:
			uI = this.updateImg(up, uI);
			break;
		case down:
			dI = this.updateImg(down, dI);
			break;
		case left:
			lI = this.updateImg(left, lI);
			break;
		case right:
			rI = this.updateImg(right, rI);
			break;
		}
		
		
	}
	
	private int updateImg(ArrayList<PImage> list, int counter){
		final float maxCount = 1;
		if(this.currentImgCount >= maxCount){
			counter++;
			if(counter >= list.size())
				counter = 0;
			this.currentPImage = list.get(counter);
			this.currentImgCount = 0;
		}
		this.currentImgCount+= PImageChangeSpeed;		
		return counter;
	}
	
	public PImage currentImg(){
		return this.currentPImage;
	}

	public ArrayList<PImage> getUp(){
		return this.up;
	}
	
	public ArrayList<PImage> getDown(){
		return this.down;
	}
	
	public ArrayList<PImage> getLeft(){
		return this.left;
	}
	
	public ArrayList<PImage> getRight(){
		return this.right;
	}

}
