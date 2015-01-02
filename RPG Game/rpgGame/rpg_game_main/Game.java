package rpg_game_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import rpg_game_components.Level;
import rpg_game_components.Player;
import rpg_game_helpers.DrawHelp;
import rpg_game_helpers.Loader;
import rpg_game_input.Button;

public class Game {
	public static Player player;
	public static Button button;
	public static Level currentLevel;
	private static int mouseX, mouseY;
	
	public void init(){
		player = new Player("Dad", RPGMain.WIDTH / 2, RPGMain.HEIGHT/2, 40, 40);
		currentLevel = new Level("Testing Level", Loader.loadImage("rpgGame/rpg_game_images/Level1.png"));
		currentLevel.addImageBoundary(0, 0, RPGMain.WIDTH, 30, null); //top
		currentLevel.addImageBoundary(0, 0, 50, RPGMain.HEIGHT, null); //left
		currentLevel.addImageBoundary(0, RPGMain.HEIGHT - 34, RPGMain.WIDTH, 34, null); //bottom
		currentLevel.addImageBoundary(RPGMain.WIDTH - 40, 0, 40, RPGMain.HEIGHT, null); //right

		initGUI();
	}
	
	public void initGUI(){
		button = new Button(100, 100, 200, 20, "LOL", Color.red, Color.black, 179, 118);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void update(Graphics g, ImageObserver obs){
		mouseX = 	RPGPanel.controls.mouseX;
		mouseY = 	RPGPanel.controls.mouseY;

		//moveable screen
		g.translate(-player.getX() + RPGMain.WIDTH / 2, -player.getY() + RPGMain.HEIGHT / 2);
		currentLevel.render(g, obs, true);
		currentLevel.checkCollisions(player);
		//
		player.update();
		player.render(g, obs);
				
	}

	public void gui(Graphics g, ImageObserver obs){
		DrawHelp.drawFixedText(g, RPGPanel.currentFPS + "", Color.black, 10, 20, player.getX(), player.getY());
		button.render(g, obs);
	
		
		DrawHelp.drawFixedText(g, "(" + mouseX + ", " + mouseY + ")", Color.blue, mouseX, mouseY, player.getX(), player.getY());

	}
	
}
