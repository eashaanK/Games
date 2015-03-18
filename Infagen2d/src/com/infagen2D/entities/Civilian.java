package com.infagen2D.entities;

import java.awt.Point;

import com.infagen2D.components.Ref;
import com.infagen2D.graphics.Colors;
import com.infagen2D.graphics.FunFont;
import com.infagen2D.graphics.Screen;
import com.infagen2D.level.Level;
import com.infagen2D.level.Tile;

/**
 * Moves, stops, Moves, stops...
 * generate maxValue for how long it can walk. Then keep decrementing from it. allow player to move
 * if the maxWalkValue <= 0, stop moving player.
 * Now assign a negative value and increment from it. if it is >= 0, assign it a + value and repeat
 * @author eashaan
 *
 */
public class Civilian extends Mob
{
	private int colour = Colors.get(-1, 111, (int)(Math.random() * 556), 543);
	protected int tickCount = 0;
	protected final int MAX_AI_WALK_IDLE_TIME = 300;
	protected float currentWalkCount = (float)(Math.random() * MAX_AI_WALK_IDLE_TIME);
	protected final float WALK_DEC = 5f, IDLE_INC = 5f; //lower walk, more time for walking, viceversa

	public Civilian(Level level, String name, int x, int y, boolean renderName) {
		super(level,name , x, y, 1, 1, renderName);
	}

	public void tick() {
		int xa = 0;
		int ya = 0;

		if(this.currentWalkCount >= 0){ //allowed to move
			this.currentWalkCount-=this.WALK_DEC;
			Point p = randomMovement();
			xa += p.x;
			ya += p.y;
			
			if(this.currentWalkCount <= 0) //make it - num to make it stop
				this.currentWalkCount = -Ref.getRandom(0, MAX_AI_WALK_IDLE_TIME);
		}
		
		else{//not allowed to move
			this.currentWalkCount += this.IDLE_INC;
			
			if(this.currentWalkCount >= 0) //make it + num to make it walk
				this.currentWalkCount = Ref.getRandom(0, MAX_AI_WALK_IDLE_TIME);
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}
		
		if( (level.getTile(this.x >> 3, this.y >> 3)).getId() == 3){ //ID of water tile (in Tile class)
			this.setIsSwimming(0);
		}
		
		if(this.isSwimmingAtAll() && level.getTile(this.x >> 3, this.y >> 3).getId() != 3){
			this.setIsSwimming(-1);
		}
		
		//is in lava
		if( (level.getTile(this.x >> 3, this.y >> 3)).getId() == 5){ //ID of lava tile (in Tile class)
			this.takeDamage(1);
			this.setIsSwimming(1);
			//System.out.println("NIGGA GET OUT THE LAVA!");
			//System.out.println(this.health);

		}
		
		this.tickCount++;
	}
	
	public Point randomMovement(){
		/*
		int xVel = 0, yVel = 0;
		int menu = Ref.getRandom(0, 7);
		switch(menu){ 
		case 0://north
			xVel = 0;
			yVel = -1;
			break;
		case 1://south
			xVel = 0;
			yVel = 1;
			break;
		case 2://west
			xVel = -1;
			yVel = 0;
			break;
		case 3://east
			xVel = 1;
			yVel = 0;
			break;
			default://south east
				xVel = 1;
				yVel = 1;
				break;
		}
		return new Point(xVel, yVel);*/
		int xV = 1, yV = 1;
		if(this.leftTileID() == Tile.STONE.getId() && this.rightTileID() == Tile.STONE.getId()) //if stone on both sides
		{
			xV = 0;
		}
		else if(this.leftTileID() == Tile.STONE.getId() || this.rightTileID() == Tile.STONE.getId()){
			xV = this.rightTileID() == Tile.STONE.getId() ? 1 : -1; //if left is stone, 1, otherwise go right
		}
		else{
			//System.out.println("Picking random x");
			xV = Ref.getRandom(0, 2);

		}
		if(x <= 0)
			xV = 1;
		else if(x >= level.width)
			xV = -1;
		
		/*System.out.println("\t" + this.upTileID());
		System.out.println(this.leftTileID() + "\t\t\t" + this.rightTileID());
		System.out.println("\t" + this.downTileID());*/
		

		return new Point(xV, yV);
	}

	public void render(Screen screen) {
		int xTile = 0;
		int yTile = 28;
		
		int walkingSpeed = 3;
		int flipTop = (numSteps >> walkingSpeed) & 1;
		int flipBottom = (numSteps >> walkingSpeed) & 1;
		
		if(this.movingDir == 1){//down
			xTile += 2;
		}
		else if(this.movingDir > 1){
			xTile += 4 + ((numSteps >> walkingSpeed) & 1) * 2;
			flipTop = (movingDir - 1)%2;
		}

		int modifier = 8 * scale;
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier / 2 - 4;

		if(this.isSwimmingAtAll()){
			int waterColor = 0;
			yOffset += 4;
			if(this.isSwimmingState == 0) {//water
				if(this.tickCount % 60 < 15){
					waterColor = Colors.get(-1, -1, 225, -1);
				}
				else if(15 <= tickCount % 60 && tickCount %60 < 30){
					yOffset -= 1;
					waterColor = Colors.get(-1, 225, 115, -1);
				}
				else if(30 <= tickCount %60 && tickCount %60 < 45){
					waterColor = Colors.get(-1, 115, -1, 225);
				}
				else{
					yOffset -= 1;
					waterColor = Colors.get(-1, 225, 115, -1);
				}
			}
			else if(this.isSwimmingState == 1){ //lava
				if(this.tickCount % 60 < 15){
					waterColor = Colors.get(-1, -1, 300, -1);
				}
				else if(15 <= tickCount % 60 && tickCount %60 < 30){
					yOffset -= 1;
					waterColor = Colors.get(-1, 400, 300, -1);
				}
				else if(30 <= tickCount %60 && tickCount %60 < 45){
					waterColor = Colors.get(-1, 440, -1, 550);
				}
				else{
					yOffset -= 1;
					waterColor = Colors.get(-1, 500, 300, -1);
				}
			}
			screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColor, 0x00, 1);
			screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColor, 0x01, 1);

		}
		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale); // upper body part 1
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop, scale); // upper body part 2
		
		if( ! this.isSwimmingAtAll()){
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour, flipBottom, scale); // lower body part 1
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale); // lower body part 2
		}
		
		if(name != null && this.renderName){
			FunFont.render(name, screen, xOffset  - ( (name.length() - 1)/2 * 8), yOffset- 10, Colors.get(-1, -1, -1, 555), 1);
		}
	}

	/**
	 * Change ur colliders here
	 */
	@Override
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		//top
		for(int x = xMin; x < xMax; x++){
			if(isSolidTile(xa, ya, x, yMin)){
				return true;
			}
		}
		//bottom
		for(int x = xMin; x < xMax; x++){
			if(isSolidTile(xa, ya, x, yMax)){
				return true;
			}
		}
		//left
		for(int y = yMin; y < yMax; y++){
			if(isSolidTile(xa, ya, xMin, y)){
				return true;
			}
		}
		//right
		for(int y = yMin; y < yMax; y++){
			if(isSolidTile(xa, ya, xMax, y)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void attackEntity(Entity e) {
		e.takeDamage(25);
	}
}
