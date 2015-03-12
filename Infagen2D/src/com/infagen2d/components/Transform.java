package com.infagen2d.components;

public class Transform
{

	private int x, y, width, height;
	private boolean freezeX = false, freezeY = false;
	
	public Transform(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isFreezeX() {
		return freezeX;
	}

	public boolean isFreezeY() {
		return freezeY;
	}

	public void setX(int x) {
		if(!this.freezeX)
			this.x = x;
	}

	public void setY(int y) {
		if(!this.freezeY)
			this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setFreezeX(boolean freezeX) {
		this.freezeX = freezeX;
	}

	public void setFreezeY(boolean freezeY) {
		this.freezeY = freezeY;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ") w: " + width + " h: " + height;
	}
	
}
