package com.infagen2D.components;

public class Transform {

	private int x, y, scale;
	private boolean freezeX, freezeY;
	
	public Transform(int x, int y, int scale){
		this.x = x;
		this.y = y;
		this.scale = scale;
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

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
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
	
	public void moveBy(int x, int y){
		this.x += x;
		this.y += y;
	}
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ") scale: " + scale;
	}
	
	
}
