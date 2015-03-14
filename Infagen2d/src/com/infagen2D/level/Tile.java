package com.infagen2D.level;

import com.infagen2D.graphics.Colors;
import com.infagen2D.graphics.Screen;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new BasicTile(0, 0, 0, Colors.get(000, -1, -1, -1));
	public static final Tile STONE = new BasicTile(1, 1, 0, Colors.get(-1, 333, -1, -1));
	public static final Tile GRASS = new BasicTile(2, 2, 0, Colors.get(-1, 131, 141, -1));

	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	
	public Tile(int id, boolean isSolid, boolean isEmitter){
		this.id = (byte)id;
		if(tiles[id] != null)throw new RuntimeException("Tile >> Deuplicate tile id ");
		this.solid = isSolid;
		this.emitter = isEmitter;
		tiles[id] = this;
		
	}
	
	public byte getID(){
		return id;
	}
	
	public boolean isSolid(){
		return this.solid;
	}
	
	public boolean isEmitter(){
		return this.emitter;
	}

	public abstract void render(Screen screen, Level level, int x, int y);
}
