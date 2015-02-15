package com.lwjgl2D.entities.implementations;

import com.lwjgl2D.entities.interfaces.AbstractMoveableGameObject;
import com.lwjgl2D.graphics.Draw;
import com.lwjgl2D.input.Input;
import com.lwjgl2D.main.DisplayManager;

import static org.lwjgl.opengl.GL11.*;


public class Player extends AbstractMoveableGameObject{
	
	public int upKey, downKey, leftKey, rightKey;
	
	public Player(float x, float y, float w, float h, String name) {
		super(x, y, w, h, name);
	}

	@Override
	public void draw() {
		glColor3f(0, 1, 0);
		Draw.rectRTS(x, y, width, height);
	}
	
	@Override
	public void update(float delta){
		input();
		super.update(delta);
	}
	
	private void input(){
		if(Input.GetKeyDown(upKey))
			this.dy = -0.1f;
	
		if(Input.GetKeyDown(downKey))
			this.dy = 0.1f;
	
		if( !(Input.GetKeyDown(upKey) || Input.GetKeyDown(downKey)))
			dy = 0;
		
		
		if(Input.GetKeyDown(leftKey))
			this.dx = -0.1f;
	
		if(Input.GetKeyDown(rightKey))
			this.dx = 0.1f;
	
		if( !(Input.GetKeyDown(leftKey) || Input.GetKeyDown(rightKey)))
			dx = 0;
		
				
	}
	
	public void setInputKeys(int up, int down, int left, int right){
		this.setUpKey(up);
		this.setDownKey(down);
		this.setLeftKey(left);
		this.setRightKey(right);

	}

	public int getUpKey() {
		return upKey;
	}

	public void setUpKey(int upKey) {
		this.upKey = upKey;
	}

	public int getDownKey() {
		return downKey;
	}

	public void setDownKey(int downKey) {
		this.downKey = downKey;
	}

	public int getLeftKey() {
		return leftKey;
	}

	public void setLeftKey(int leftKey) {
		this.leftKey = leftKey;
	}

	public int getRightKey() {
		return rightKey;
	}

	public void setRightKey(int rightKey) {
		this.rightKey = rightKey;
	}

	
}
