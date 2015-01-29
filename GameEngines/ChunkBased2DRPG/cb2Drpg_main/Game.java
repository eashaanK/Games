package cb2Drpg_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import cb2Drpg_gameObjects.Player;
import cb2Drpg_input.Input;
import cb2Drpg_map.Map;
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
		Player player = new Player(Main.WIDTH/2, Main.HEIGHT/2, Ref.TILE_SIZE, Ref.TILE_SIZE);
		map = new Map(player);
	}
	
	public void update(){
		map.checkChunks();
		updateInput();
	}
	
	public void render(Graphics g){
		map.render(g);
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
