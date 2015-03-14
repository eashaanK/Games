package com.infagen2D.level;

import com.infagen2D.graphics.Screen;

public class Level {

	public String levelName = "Level";
	private byte[] tiles;
	public int width;
	public int height;
	
	public Level(String name,  int w, int h){
		this.levelName = name;
		this.width = w;
		this.height = h;
		tiles = new byte[width * height];
		
		this.generateLevel();
	}

	private void generateLevel() {
		System.out.println(levelName + " level generating...");
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(x * y % 10 < 5){
					tiles[ x+ y * width] = Tile.GRASS.getID();
				}
				else{
					tiles[ x+ y * width] = Tile.STONE.getID();
				}
				//screen.render(x << 3, y << 3 , 3, Colors.get(555, 505, 050, 550), false, true); //from darkest to lightest
			}
		}		
		System.out.println(levelName + " level done generating...");
	}
	
	public void tick(){
		
	}
	
	public void renderTile(Screen screen, int xOffset, int yOffset){
		if(xOffset < 0)xOffset =  0;
		if(xOffset > ((width << 3) - screen.width))xOffset = ((width << 3) - screen.width);
		
		if(yOffset < 0)yOffset =  0;
		if(yOffset > ((height << 3) - screen.height))yOffset = ((height << 3) - screen.height);
		
		screen.setOffset(xOffset, yOffset);
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
	}

	private Tile getTile(int x, int y) {
		if(x < 0 || x > width || y < 0 || y > height)return Tile.VOID;
		
		return Tile.tiles[tiles[x + y * width]];
	}
}
