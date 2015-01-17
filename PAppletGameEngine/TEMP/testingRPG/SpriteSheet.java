package testingRPG;

import processing.core.PApplet;
import processing.core.PImage;

public class SpriteSheet {

	public PImage spriteSheet;
	public byte currR = 0, currC = 0; //change the currR to switch direction
	private float max = 1f, inc = 0.25f, current = 0;
	private PApplet parent;
	int[][] spriteSheetPixels;
	String spriteString;
	private float width, height;
	
	private void init(String path){
		spriteSheet = parent.loadImage(path);
		spriteSheet.loadPixels();
		spriteSheetPixels = new int[120][160];
		int[][] D1 = new int[40][40];

		this.changeTo2D(spriteSheet.pixels, spriteSheetPixels);
		// spriteSheetPixels contains the correct values. Next, assign the
		// correct the image values to arrays

		for (int r = 0; r < spriteSheetPixels.length; r++) {
			for (int c = 0; c < spriteSheetPixels[r].length; c++) {
				if (r < 40 && c < 40) {// still in D1
					D1[r][c] = spriteSheetPixels[r][c];
				}
			}
		}
		
		setSpriteString(spriteSheet.pixels);
		
	}

	public SpriteSheet(PApplet parent, String path, float w, float h, float speed) {
		this.width = w;
		this.height = h;
		this.inc = speed; 
		this.parent = parent;
		init(path);	
	}
	
	
	public SpriteSheet(PApplet parent, float w, float h, String path) {
		this(parent, path, w, h, 0.25f);
	}

	// Changes into a 2D representation of pixels[]
	private void changeTo2D(int[] original, int[][] pixels) {
		int count = 0;
		for (int r = 0; r < pixels.length; r++) {
			for (int c = 0; c < pixels[r].length; c++) {
				pixels[r][c] = original[count];
				count++;
			}
		}
	}
	
	/**
	 * return {...,...,...}pixels/currR/currC
	 * @return
	 */
	public String getCurrentRC(){
		return this.spriteString + "/" + currR + "/" + currC;
	}
	
	public PImage currentImage(){
		PImage image = new PImage((int)width, (int)height);

		int iR = (int)(currC * width);
		int iC = (int)(currR * height);
		
	
		System.out.println("Row: " + iR + " Col: " + iC);


		int index = 0;
		for(int r = iR; r < iR + height; r++){
			for(int c = iC; c < iC + width; c++){
				image.pixels[index] = this.spriteSheetPixels[r][c];
				index++;
			}
		}
		
		
		return image;
	}
	
	/**
	 * Width and height of image to be displayed on the Screen
	 */
	public void draw(float x, float y){
		parent.image(this.spriteSheet, x, y, width,  height, (int)(currC * width), (int)(currR * height), (int)(width - 1f + (currC * width)), (int)(height - 1f + currR * height));
		//System.out.println("X: " + (int)(currC * width) + " Y: " + (int)(currR * height));
	}
	
	/**
	 * Call this only when to run the animation
	 */
	public void update(){
		current += inc;
		if(current > max){
			current = 0;
			currC++;
			if(currC >= 3)
				currC=0;
		}
	}
	
	private void setSpriteString(int[] pixels) {
		this.spriteString = "{";
		for(int i = 0; i < pixels.length - 1; i++){
			spriteString += pixels[i] + ", ";
		}
		
		spriteString += pixels[pixels.length - 1] + "}";
	}
	
	public void setCurrC(byte c){
		this.currC = c;
	}
	
	public void setCurrR(byte r){
		this.currR= r;
	}

	public void setSpriteSheet(String path){
		init(path);
		setSpriteString(this.spriteSheet.pixels);
	}
	
	public void chaneToDown(){
		this.currR = 0;
	}
	
	public void chaneToLeft(){
		this.currR = 1;
	}
	
	public void chaneToRight(){
		this.currR = 2;
	}
	
	public void chaneToUp(){
		this.currR = 3;
	}
	
	/**
	 * Set speed of changing images from 0 - 1
	 */
	public void changeSpeed(float i){
		this.inc = i;
	}
	
}
