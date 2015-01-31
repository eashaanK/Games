package cb2Drpg_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import cb2Drpg_gameObjects.Player;
import cb2Drpg_images.ImageWriter;
import cb2Drpg_input.Input;
import cb2Drpg_map.Chunk;
import cb2Drpg_map.Map;
import cb2Drpg_noise.SimplexNoise;
import cb2Drpg_noise.SimplexNoiseOctave;
import cb2Drpg_refereces.Ref;

public class Game {

	public Screen screen;
	public Input input;
	private Map map;
	
	public Game(Screen screen, Input input){
		this.screen = screen;
		this.input = input;
		init();
	}
	
	public void init(){
		Player player = new Player(0, 0, Ref.TILE_SIZE, Ref.TILE_SIZE);
		map = new Map(player);
		
	
	}
	
	public void update(){
		map.checkChunks();
		updateInput();
	}
	
	public void render(Graphics g){
		
		renderBackground(g);
		
		renderMain(g);
		
		renderForeGround(g);
	}
	
	private void renderBackground(Graphics g){
		for(Chunk chunk : map.loadedChunks){
			int posX = chunk.getChunkX() * Ref.TILES_AMOUNT_X*Ref.TILE_SIZE * Ref.PIXEL_SIZE - map.player.getX() * Ref.TILE_SIZE*Ref.PIXEL_SIZE - Ref.TILE_SIZE*Ref.PIXEL_SIZE /2+ Main.WIDTH / 2;
			int posY = chunk.getChunkY() * Ref.TILES_AMOUNT_Y*Ref.TILE_SIZE * Ref.PIXEL_SIZE - map.player.getY() * Ref.TILE_SIZE*Ref.PIXEL_SIZE - Ref.TILE_SIZE*Ref.PIXEL_SIZE /2+ Main.HEIGHT / 2;
			for(int x =0;  x < chunk.tiles.length; x++){
				for(int y =0;  y < chunk.tiles[0].length; y++){
					int tX = x*Ref.TILE_SIZE*Ref.PIXEL_SIZE + posX;
					int tY = y*Ref.TILE_SIZE*Ref.PIXEL_SIZE + posY;
					int size = Ref.TILE_SIZE * Ref.PIXEL_SIZE;
					g.drawImage(chunk.tiles[x][y].texture, tX, tY, size, size, null);
					//g.drawString(chunk.id + " ", tX + 10, tY + 10);
				}
			}
			g.drawLine(posX, 0, posX, Main.HEIGHT);
			g.drawLine(0, posY, Main.WIDTH, posY);

		}
	}
	
	private void renderMain(Graphics g){
		g.setColor(Color.black);
		g.drawRect(Main.WIDTH/2, Main.HEIGHT/2, Ref.PIXEL_SIZE *Ref.TILE_SIZE, Ref.PIXEL_SIZE*Ref.TILE_SIZE);
	}
	
	private void renderForeGround(Graphics g){
		g.setColor(Color.black);
		g.drawString(screen.fps + "", 10, 20);
		
		g.drawString((Ref.mouseX + ", " + Ref.mouseY), Ref.mouseX, Ref.mouseY);

		g.drawString("Player X: " + map.player.getX(), 10, 100);
		g.drawString("Player Y: " + map.player.getY(), 10, 200);
	}
	
	private void updateInput(){
		for(int key: Ref.keysHeld){
			if(key == KeyEvent.VK_W){
				map.player.moveBy(0, -1);
			}
			if(key == KeyEvent.VK_S){
				map.player.moveBy(0, 1);
			}
			if(key == KeyEvent.VK_A){
				map.player.moveBy(-1, 0);
			}
			if(key == KeyEvent.VK_D){
				map.player.moveBy(1, 0);
			}
			
			System.out.println(map.player.getX() + " " + map.player.getY());
		}
	}
}
