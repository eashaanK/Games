package cb2Drpg_main;

import java.awt.Color;
import java.awt.Graphics;

import cb2Drpg_images.ImageLibrary;
import cb2Drpg_input.Input;
import cb2Drpg_refereces.Ref;

public class Game {

	public Screen screen;
	public Input input;
	
	public Game(Screen screen, Input input){
		this.screen = screen;
		this.input = input;
		init();
	}
	
	public void init(){
		
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		for(int x = 0; x < 20; x++){
			for(int y = 0; y < 20; y++){
				g.drawImage(ImageLibrary.GRASS, x * Ref.TILE_SIZE * Ref.PIXEL_SIZE, y * Ref.TILE_SIZE * Ref.PIXEL_SIZE, Ref.TILE_SIZE * Ref.PIXEL_SIZE, Ref.TILE_SIZE * Ref.PIXEL_SIZE, null);
			}
		}
		g.setColor(Color.black);
		g.drawString(screen.fps + "", 10, 20);
		
		g.drawString((input.mouseX + ", " + input.mouseY), input.mouseX, input.mouseY);

	}
}
