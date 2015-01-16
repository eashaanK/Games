package sprite;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class FourSprite {
	private ArrayList<PImage> up, down, left, right;
	private PImage currentImage;
	private final int MAX_COUNT, INC;
	private int count, upIndex, downIndex, leftIndex, rightIndex,
			currentDirection;
	public static final byte UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	public FourSprite(int max, int INC, int initDirection) {
		this.MAX_COUNT = max;
		this.INC = INC;
		up = new ArrayList<PImage>();
		down = new ArrayList<PImage>();
		left = new ArrayList<PImage>();
		right = new ArrayList<PImage>();
		this.currentDirection = initDirection;
	}

	public void update() {

		switch (this.currentDirection) {
		case UP:
			this.update(upIndex, up);
			break;
		case DOWN:
			this.update(downIndex, down);
			break;
		case LEFT:
			this.update(leftIndex, left);
			break;
		case RIGHT:
			this.update(rightIndex, right);
			break;
		}

	}

	public void addUp(PImage i) {
		this.up.add(i);
	}

	public void addDown(PImage i) {
		this.down.add(i);

	}

	public void addLeft(PImage i) {
		this.left.add(i);

	}

	public void addRight(PImage i) {
		this.right.add(i);

	}
	
	public PImage nextImage(){
		ArrayList<PImage> temp = null;
		int tempI = -1;
		switch (this.currentDirection) {
		case UP:
			temp = up;
			tempI = upIndex;
			break;
		case DOWN:
			temp = down;
			tempI = downIndex;
			break;
		case LEFT:
			temp = left;
			tempI = leftIndex;
			break;
		case RIGHT:
			temp = right;
			tempI = rightIndex;
			break;
		}
		
		return temp.get(tempI);
	}

	// ************************************
	private int update(int index, ArrayList<PImage> sprites) {
		if (count >= MAX_COUNT) {
			count = 0;
			index++;
			if (index >= sprites.size())
				index = 0;
		}
		if (count < MAX_COUNT) {
			count += INC;
		}
		return index;
	}
}
