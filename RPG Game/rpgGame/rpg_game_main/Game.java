package rpg_game_main;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import rpg_game_components.Floor;
import rpg_game_components.Player;
import rpg_game_helpers.DrawHelp;

public class Game {
	public static Player player;
	public Floor floor;
	public Button button;
	
	public void init(){
		player = new Player("Dad", RPGMain.WIDTH / 2, RPGMain.HEIGHT/2, 40, 40);
		floor = new Floor(RPGMain.WIDTH, RPGMain.HEIGHT);
	}
	
	public void render(Graphics g, ImageObserver obs){
		//moveable screen
		g.translate(-player.getX() + RPGMain.WIDTH / 2, -player.getY() + RPGMain.HEIGHT / 2);
		floor.render(g, obs);
		floor.update();
		//
		player.update();
		player.render(g, obs);
				
	}

	public void gui(Graphics g, ImageObserver obs){
		DrawHelp.drawFixedText(g, RPGPanel.currentFPS + "", Color.black, 10, 20, player.getX(), player.getY());

	}
	
}
