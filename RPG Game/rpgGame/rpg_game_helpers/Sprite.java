package rpg_game_helpers;

import java.awt.Image;
import java.util.ArrayList;

public class Sprite {

	private ArrayList<MyImage> up, down, left, right;
	private MyImage currentImage;
	private int uI, dI, lI, rI;
	private float currentImgCount, imageChangeSpeed = 0.20f;
	
	public Sprite(float imageChangeSpeed){
		this.imageChangeSpeed = imageChangeSpeed;
		
		this.up = new ArrayList<MyImage>();
		this.down = new ArrayList<MyImage>();
		this.left = new ArrayList<MyImage>();
		this.right = new ArrayList<MyImage>();
	}
	
	public void initCurrentImage(MyImage img){
		currentImage = img;
	}
	
	public void assignImageUp(int index){
		this.currentImage = up.get(index);
	}
	
	public void assignImageDown(int index){
		this.currentImage = down.get(index);

	}
	
	public void assignImageLeft(int index){
		this.currentImage = left.get(index);
		
	}
	
	public void assignImageRight(int index){
		this.currentImage = right.get(index);
	}
	
	public void addUp(String path){
		up.add(new MyImage(Loader.loadImage(path), path));
	}
	
	public void addDown(String path){
		down.add(new MyImage(Loader.loadImage(path), path));
	}
	
	public void addLeft(String path){
		left.add(new MyImage(Loader.loadImage(path), path));
	}
	
	public void addRight(String path){
		right.add(new MyImage(Loader.loadImage(path), path));
	}
	
	public void updateImage(ImageDirection iDir) {
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
	
	private int updateImg(ArrayList<MyImage> list, int counter){
		final float maxCount = 1;
		if(this.currentImgCount >= maxCount){
			counter++;
			if(counter >= list.size())
				counter = 0;
			this.currentImage = list.get(counter);
			this.currentImgCount = 0;
		}
		this.currentImgCount+= imageChangeSpeed;		
		return counter;
	}
	
	public Image currentImg(){
		return this.currentImage.image;
	}
	
	public String currentPath(){
		return this.currentImage.path;
	}

	public ArrayList<MyImage> getUp(){
		return this.up;
	}
	
	public ArrayList<MyImage> getDown(){
		return this.down;
	}
	
	public ArrayList<MyImage> getLeft(){
		return this.left;
	}
	
	public ArrayList<MyImage> getRight(){
		return this.right;
	}

}
