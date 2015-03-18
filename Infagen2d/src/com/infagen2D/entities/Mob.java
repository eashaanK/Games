package com.infagen2D.entities;

import com.infagen2D.level.Level;
import com.infagen2D.level.Tile;


public abstract class Mob extends Entity {

	protected String name;
	protected final int LANDSPEED;
	protected final int WATERSPEED;
	protected int speed;
	protected int numSteps = 0;
	protected boolean isMoving;
	protected int movingDir = 1;
	protected int scale = 1;
	protected int isSwimmingState = -1;//0 for water, 1 for lava

	protected boolean renderName;


	public Mob(Level level, String name, int x, int y, int landSpeed, int waterSpeed, boolean renderName) {
		super(level);
		this.name = name;
		this.x = x;
		this.y = y;
		this.LANDSPEED = landSpeed;
		this.WATERSPEED = waterSpeed;
		speed = LANDSPEED;
		this.renderName = renderName;

	}

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			numSteps -= 1;
			return;
		}
		numSteps += 1;
		if (!hasCollided(xa, ya)) {
			if (ya < 0) {
				movingDir = 0;
			}
			if (ya > 0) {
				movingDir = 1;
			}
			if (xa < 0) {
				movingDir = 2;
			}
			if (xa > 0) {
				movingDir = 3;
			}
			x += xa * speed;
			y += ya * speed;
		}
	}

	public abstract boolean hasCollided(int xa, int ya);

	/**
	 * Last tile u were standing on
	 * @return
	 */
	protected boolean isSolidTile(int xa, int ya, int x, int y){
		if(level == null){return false;}
		Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
		Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
		if( !lastTile.equals(newTile) && newTile.isSolid()){
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * -1 for not swimming
	 * 0 for water
	 * 1 for lava
	 * @param isSwim
	 */
	public void setIsSwimming(int isSwim){
		this.isSwimmingState = isSwim;
		if(isSwim > -1){
			this.speed = this.WATERSPEED;
		}
		else{
			this.speed = this.LANDSPEED;
		}
	}
	
	public boolean isSwimmingAtAll(){
		return this.isSwimmingState >= 0;
	}
}