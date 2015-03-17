package com.infagen2D.entities;

import com.infagen2D.components.InputHandler;
import com.infagen2D.graphics.Colors;
import com.infagen2D.graphics.Screen;
import com.infagen2D.level.Level;

public class Player extends Mob {

	private InputHandler input;
	private int colour = Colors.get(-1, 111, 145, 543);

	public Player(Level level, int x, int y, InputHandler input) {
		super(level, "Player", x, y, 1);
		this.input = input;
	}

	public void tick() {
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

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}

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

		screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale); // upper body part 1
		screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop, scale); // upper body part 2
		
		screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour, flipBottom, scale); // lower body part 1
		screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale); // lower body part 2
	}

	public boolean hasCollided(int xa, int ya) {
		return false;
	}

}