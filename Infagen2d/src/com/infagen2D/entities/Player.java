package com.infagen2D.entities;

import com.infagen2D.components.InputHandler;
import com.infagen2D.graphics.Colors;
import com.infagen2D.graphics.Screen;
import com.infagen2D.level.Level;

public class Player extends Mob{
	
	private InputHandler input;

	public Player(int x, int y, String name, Level level, InputHandler input) {
		super(x, y, name, level, 1);
		this.input = input;
		this.color = Colors.get(-1, 111, 145, 543);

	}

	@Override
	public boolean hasCollided(int xA, int yA) {
		return false;
	}

	@Override
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
		
		if(xa != 0 || ya != 0){
			this.move(xa, ya);
			this.isMoving = true;
		}
		else{
			this.isMoving = false;
		}
	}

	@Override
	public void render(Screen screen) {
		int xTile =0;
		int yTile = 28;
		
		int modifier = 8 * transform.getScale(); //cause he is 8 * 8
		int xOffset = transform.getX() - modifier/2;
		int yOffset = transform.getY() /2 - 4;
		
		screen.render(xOffset , yOffset, xTile + yTile * 32, color); //upper body
		screen.render(xOffset + modifier, yOffset, (xTile + 1)+ yTile * 32, color);
		
		screen.render(xOffset, yOffset + modifier, xTile + (yTile + 1) * 32, color);///lower body
		screen.render(xOffset  + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1)* 32, color);

	}
	
	public String toString(){
		return super.toString();
	}

}
