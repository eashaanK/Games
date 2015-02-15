package com.lwjgl2D.entities.interfaces;

import java.awt.Rectangle;

import com.lwjgl2D.main.DisplayManager;

public abstract class AbstractMoveableGameObject extends AbstractGameObject implements MoveableGameObject{

	protected float dx, dy, mass;
	protected boolean canMove;
	
	public AbstractMoveableGameObject(float x, float y, float w, float h,
			String name) {
		super(x, y, w, h, name);
		dx = 0;
		dy = 0;
		canMove = true;
	}

	@Override
	public float getDX() {
		return dx;
	}

	@Override
	public float getDY() {
		return dy;
	}

	@Override
	public void setDX(float dx) {
		this.dx = dx;
	}

	@Override
	public void setDY(float dy) {
		this.dy = dy;
	}

	@Override
	public void setMass(float mass) {
		this.mass = mass;
	}

	@Override
	public float getMass() {
		return mass;
	}

	@Override
	public boolean canMove() {
		return canMove;
	}

	@Override
	public void setCanMove(boolean b) {
		this.canMove = b;
	}

	@Override
	public abstract void draw();

	@Override
	public void update(float delta){
		if(!this.canMove())
		{
			dx = 0;
			dy = 0;
		}
		
		x += dx * delta;
		y += dy * delta;

	}



}
