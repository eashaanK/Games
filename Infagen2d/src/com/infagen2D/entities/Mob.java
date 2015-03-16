package com.infagen2D.entities;

import com.infagen2D.level.Level;


public abstract class Mob extends Entity{

	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int color;
	
	public Mob(int x, int y, String name, Level level, int speed) {
		super(x, y, 1, name, level);
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void move(int xA, int yA){
		if(xA != 0 && yA != 0){
			move(xA, 0);
			move(0, yA);
			numSteps--;
			return;
		}
		numSteps++;
		if(!hasCollided(xA, yA)){
			if(yA < 0)this.movingDir = 0;//North
			if(yA > 0)this.movingDir = 1;//SOUTH
			if(xA < 0)this.movingDir = 2;//WEST
			if(xA > 0)this.movingDir = 3;//EAST
			this.transform.moveBy(xA * speed, yA * speed);
		}
	}
	public abstract boolean hasCollided(int xA, int yA);
	
	public String toString(){
		return super.toString();
	}
}
