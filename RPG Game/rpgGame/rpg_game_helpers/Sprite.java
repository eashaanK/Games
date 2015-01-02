package rpg_game_helpers;

import java.awt.Image;
import java.util.ArrayList;

public class Sprite {

	private ArrayList<Image> up, down, left, right;
	private Image currentImage;
	private int uI, dI, lI, rI;
	private float currentImgCount, imageChangeSpeed = 0.20f;
	
	public Sprite(float imageChangeSpeed){
		this.imageChangeSpeed = imageChangeSpeed;
		
		this.up = new ArrayList<Image>();
		this.down = new ArrayList<Image>();
		this.left = new ArrayList<Image>();
		this.right = new ArrayList<Image>();
	}
	
	public void initCurrentImage(Image img){
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
		up.add(Loader.loadImage(path));
	}
	
	public void addDown(String path){
		down.add(Loader.loadImage(path));
	}
	
	public void addLeft(String path){
		left.add(Loader.loadImage(path));
	}
	
	public void addRight(String path){
		right.add(Loader.loadImage(path));
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
	
	private int updateImg(ArrayList<Image> list, int counter){
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
		return this.currentImage;
	}

	public ArrayList<Image> getUp(){
		return this.up;
	}
	
	public ArrayList<Image> getDown(){
		return this.down;
	}
	
	public ArrayList<Image> getLeft(){
		return this.left;
	}
	
	public ArrayList<Image> getRight(){
		return this.right;
	}

}
