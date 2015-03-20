package com.infagen2D.entities;

import com.infagen2D.components.InputHandler;
import com.infagen2D.components.Tools;
import com.infagen2D.components.Tools.Tool;
import com.infagen2D.graphics.Colors;
import com.infagen2D.graphics.FunFont;
import com.infagen2D.graphics.Screen;
import com.infagen2D.level.Level;

public class Player extends Mob {

	private InputHandler input;
	private int color1 = -1, color2 = 111, color3 = 145, color4 = 543;
	private int colour = Colors.get(-1, 111, 145, 543);
	protected int tickCount = 0;
	protected Tool tool = Tool.HAND;
	protected int currentToolXTile, currentToolYTile;
	protected boolean isSwipe = false;
	protected float damageToCivilian = 0;
	protected final float MAX_DAMAGE_TO_CIVILIAN = 10;


	public Player(Level level, String name, int x, int y, InputHandler input) {
		super(level,name , x, y, 1, 1, true);
		this.input = input;
		this.hitbox = 12;
	}

	public void tick() {
		super.updateDamageCounter();
		int xa = 0;
		int ya = 0;

		if (input.up.isPressed()) {
			ya -= 1;
		}
		if (input.down.isPressed()) {
			ya += 1;
		}
		if (input.left.isPressed()) {
			xa -= 1;
		}
		if (input.right.isPressed()) {
			xa += 1;
		}
		if(input.num1.isPressed()){
			this.tool = Tool.HAND;
		}
		if(input.num2.isPressed()){
			this.tool = Tool.SWORD;
		}
		
		this.isSwipe = input.space.isPressed();
		if(isSwipe && this.tool != Tool.HAND){
			switch(this.movingDir){
			case 0: //up
				if(this.up != null)
					this.up.takeDamage(this.damageToCivilian);
				break;
			case 1: //down
				if(this.down != null)
					this.down.takeDamage(damageToCivilian);
				break;
			case 2: //left
				if(this.left != null)
					this.left.takeDamage(damageToCivilian);
				break;
			case 3: //right
				if(this.right != null)
					this.right.takeDamage(damageToCivilian);
				break;
				
			}
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
			this.takeDamage(this.damageFromLava);
			this.setIsSwimming(1);
		}
		
		if(this.isHurt()){
			this.colour = Colors.get(-1, 500, 500, 543);
		}
		else{
		//	this.colour = Colors.get(-1, 111, 145, 543);
			this.colour = Colors.get(color1, color2, color3, color4);

		}
		this.tickCount++;
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
		this.checkForTools(flipBottom, flipTop, xOffset, yOffset, screen);
		this.checkForSwipe(flipBottom, flipTop, xOffset, yOffset, screen);
		//player name
		if(name != null && this.renderName){
			FunFont.render(name, screen, xOffset  - ( (name.length() - 1)/2 * 8), yOffset- 10, Colors.get(-1, -1, -1, 555), 1);
		}
		//player health
		
		//FunFont.render((int)this.health +  "", screen, xOffset  - 50, yOffset - 50, Colors.get(-1, -1, -1, 555), 1);
		//if(this.left != null)
		//	System.out.println("Left entity: " + this.left + " Right entity: " + this.right);

	}

	private void checkForSwipe(int flipBottom, int flipTop, int xOffset, int yOffset, Screen screen){
		if(this.isSwipe){
			//System.out.println("Attack!");
		}
	}

	private void checkForTools(int flipBottom, int flipTop, int xOffset, int yOffset, Screen screen){
		switch(tool){
		case HAND:
			damageToCivilian = 0;
			return;
		case SWORD:
			damageToCivilian = MAX_DAMAGE_TO_CIVILIAN;
			this.currentToolXTile = Tools.SWORD_X_TILE;
			this.currentToolYTile = Tools.SWORD_Y_TILE;

			break;
		}
		
		int swordColor = Colors.get(-1, 222, 444, 210);
		
		
		int flipH = flipBottom;
		int flipV = flipTop;

		if( this.movingDir == 3){
			xOffset+=5;
			flipV *= 1;
		}
		screen.render(xOffset + ( flipV), yOffset + 5, currentToolXTile + currentToolYTile * 32, swordColor, flipH, scale); // upper body part 1
	}
	/**
	 * Change ur colliders here
	 */
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
	
}