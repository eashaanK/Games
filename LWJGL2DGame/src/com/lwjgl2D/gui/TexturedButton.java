package com.lwjgl2D.gui;

import org.newdawn.slick.opengl.Texture;

import com.lwjgl2D.graphics.Draw;
import com.lwjgl2D.input.Input;
import com.nishu.utils.Vector2f;

public class TexturedButton extends Button{

	private Texture textureIdle;
	private Texture texturePressed;
	
	private enum ButtonState{
		IDLE, PRESSED
	}
	private ButtonState buttonState;

	/**
	 * DO NOT INCLUDE.png in texture name
	 * @param x
	 * @param y
	 * @param text
	 * @param textureName
	 */
	public TexturedButton(float x, float y, float w, float h, String text, String textureIdle, String texturePressed) {
		super(x, y, w, h, text);
		this.textureIdle = Draw.loadTexture(textureIdle, "guiRes");
		this.texturePressed = Draw.loadTexture(texturePressed, "guiRes");
		buttonState = ButtonState.IDLE;
	}

	@Override
	public void update() {
		
		
		if(this.isPressed()){
			this.buttonState = ButtonState.PRESSED;
		}
		
		else{
			this.buttonState = ButtonState.IDLE;
		}
		
		switch(buttonState){
		case IDLE:
			Draw.drawTexture(textureIdle, this.getX(), this.getY(), this.getW(), this.getH());
			break;
		case PRESSED:
			Draw.drawTexture(texturePressed, this.getX(), this.getY(), this.getW(), this.getH());
			break;
		}
		
	}

	public boolean isHovering(){
		Vector2f mousePos = Input.GetMousePositionOnScreen();
		return (mousePos.getX() >= this.getX() && mousePos.getX() <= this.getX() + this.getW() && mousePos.getY() >= this.getY() && mousePos.getY() <= this.getY() + this.getH());
	}
	
	public boolean isPressed(){
		return (Input.GetMouseDown(0) && isHovering());
	}

	public Texture getTextureIdle() {
		return textureIdle;
	}

	public void setTextureIdle(Texture textureIdle) {
		this.textureIdle = textureIdle;
	}

	public Texture getTexturePressed() {
		return texturePressed;
	}

	public void setTexturePressed(Texture texturePressed) {
		this.texturePressed = texturePressed;
	}

	public ButtonState getButtonState() {
		return buttonState;
	}

	public void setButtonState(ButtonState buttonState) {
		this.buttonState = buttonState;
	}

	@Override
	public String toString() {
		return "Button: " + this.getX() + ", " +  this.getY() + ", " + this.getText() + ", ";
	}

}
