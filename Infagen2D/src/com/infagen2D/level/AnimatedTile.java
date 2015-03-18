package com.infagen2D.level;

import com.infagen2D.graphics.Screen;

public class AnimatedTile extends BasicTile{
	
	private int[][] animationCoords;
	private int currentAnimIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;

	public AnimatedTile(int id, int[][] animationCoords, int tileColour, int levelColor, int animSwitchDelay) {
		super(id, animationCoords[0][0], animationCoords[0][1], tileColour, levelColor);
		this.animationCoords = animationCoords;
		this.currentAnimIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = animSwitchDelay;
	}
	
	@Override
	public void tick(){
		if((System.currentTimeMillis() - lastIterationTime) >= (this.animationSwitchDelay)){
			this.lastIterationTime = System.currentTimeMillis();
			this.currentAnimIndex = (currentAnimIndex + 1) % this.animationCoords.length;
			this.tileId = (this.animationCoords[this.currentAnimIndex][0] + (this.animationCoords[this.currentAnimIndex][1] * 32));
		}
	}

}
