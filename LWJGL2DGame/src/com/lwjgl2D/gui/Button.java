package com.lwjgl2D.gui;

public abstract class Button {

	private float x, y, w, h;
	private String text;
	
	public Button(float x, float y, float w, float h, String text){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.text  = text;
	}
	
	public abstract void update();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public abstract String toString();
}
