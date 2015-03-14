package com.infagen2D.level;

import com.infagen2D.graphics.Screen;

public class BasicTile extends Tile{

	protected int tileID;
	protected int tileColor;
	
	/**
	 * @param id special unique id
	 * @param x position in spriteSheet
	 * @param y position in spriteSheet
	 * @param tileColor Color
	 */
	public BasicTile(int id, int x, int y, int tileColor) {
		super(id, false, false);
		this.tileID = x + y;
		this.tileColor = tileColor;
	}

	@Override
	public void render(Screen screen, Level level, int x, int y) {
		screen.render(x, y, tileID, tileColor, false, false);
	}

}
