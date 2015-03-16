package com.infagen2D.entities;

import com.infagen2D.graphics.Screen;
import com.infagen2D.level.Level;

public abstract class Entity {

	public int x;
	public int y;
	protected Level level;
	
	public Entity(Level level) {
		init(level);
	}
	
	public final void init(Level level) {
		this.level = level;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen);
}