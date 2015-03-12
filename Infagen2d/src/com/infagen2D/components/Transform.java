package com.infagen2D.components;

public class Transform {

	private int x, y, width, height;
	private boolean freezeX, freezeY;
	
	public Transform(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.freezeX = false;
		this.freezeY = false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		if(!this.freezeX)
			this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		if(!this.freezeY)
			this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isFreezeX() {
		return freezeX;
	}

	public void setFreezeX(boolean freezeX) {
		this.freezeX = freezeX;
	}

	public boolean isFreezeY() {
		return freezeY;
	}

	public void setFreezeY(boolean freezeY) {
		this.freezeY = freezeY;
	}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ") w: " + width + " h: " + height;
	}
	
	
}
