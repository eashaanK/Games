package com.infagen2D.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.infagen2D.entities.Entity;
import com.infagen2D.entities.Player;
import com.infagen2D.graphics.Screen;
import com.infagen2D.noise.SimplexNoise;

public class Level {

	private byte[] tiles;
	public int width;
	public int height;
	public List<Entity> entities = new ArrayList<Entity>();
	private String imagePath;
	private BufferedImage image;
	private final double RENDER_DISTANCE = 120;
	private final double UPDATE_DISTANCE = RENDER_DISTANCE * 2;


	public Level(String imagePath) {
		
		if(imagePath != null){
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		}
		else{
			this.width = 1000;
			this.height = 1000;
			tiles = new byte[width * height];
			this.generateLevel();
		}
	}
	
	private void loadLevelFromFile(){
		try{
			this.image = ImageIO.read(Level.class.getResource(this.imagePath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new byte[width * height];
			this.loadTiles();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private void loadTiles(){
		int[] tileColors = this.image.getRGB(0, 0, width, height, null, 0, width);
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tileCheck: for(Tile t : Tile.tiles){
					if( t != null && t.getLevelColor() == tileColors[x + y * width]){
						this.tiles[x + y * width] = t.getId();
						break tileCheck;
					}
				}
			}
		}
	}
	
	private void saveLevelToFile(){
		try {
			ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void alterTile(int x, int y, Tile newTile){
		this.tiles[x + y * width] = newTile.getId();
		image.setRGB(x, y, newTile.getLevelColor());
	}

	public void generateLevel() {
		
		//addNoiseGenHere
		SimplexNoise noiseFunction = new SimplexNoise(7, 0.1);
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double noise = noiseFunction.getNoise(x, y);
				noise = Math.abs(noise) * 10;
				//System.out.println("Noise: " + noise);
				if (  noise < 0.35) {
					tiles[x + y * width] = Tile.WATER.getId();
				}
				else if(noise < 0.46){
					tiles[x + y * width] = Tile.SAND.getId();
				}
				else if(noise < 0.85){
					tiles[x + y * width] = Tile.GRASS.getId();
				}
				else if(noise < 0.92){
					tiles[x + y * width] = Tile.STONE.getId();

				}
				else{
					tiles[x + y * width] = Tile.LAVA.getId();
				}
			}
		}
	}

	public void tick() {
		Player p = (Player)entities.get(0);
		for(int i = entities.size() - 1; i >= 0; i--){
			if(entities.get(i).getHealth() <= 0){
				entities.remove(i);
			}
			else{
				if(this.getDistance(p.x, p.y, entities.get(i).x, entities.get(i).y) <= this.UPDATE_DISTANCE)
					entities.get(i).tick();
			}
		}
		
		for(Tile t : Tile.tiles){
			if(t == null)
				break;
			t.tick();
		}
		
	}

	public void renderTiles(Screen screen, int xOffset, int yOffset) {
		if (xOffset < 0)
			xOffset = 0;
		if (xOffset > ((width << 3) - screen.width))
			xOffset = ((width << 3) - screen.width);
		if (yOffset < 0)
			yOffset = 0;
		if (yOffset > ((height << 3) - screen.height))
			yOffset = ((height << 3) - screen.height);

		screen.setOffset(xOffset, yOffset);

		for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
				getTile(x, y).render(screen, this, x << 3, y << 3);
			}
		}
	}
	
	private double getDistance(int x1, int y1, int x2, int y2){
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	
	public void renderEntities(Screen screen){
		Player p = (Player)entities.get(0);
		for(Entity e: entities){
			if(this.getDistance(p.x, p.y, e.x, e.y) <= RENDER_DISTANCE)
				e.render(screen);
			
		}
	}

    public Tile getTile(int x, int y) {
        if (0 > x || x >= width || 0 > y || y >= height)
            return Tile.VOID;
        return Tile.tiles[tiles[x + y * width]];
    }

	public void addEntity(Entity entity) {
		if(entity == null){
			System.err.println("You tried to enter a null entity in Level");
			return;
		}
		this.entities.add(entity);
	}
}