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
	public static Button singlePlayerButton;
	public static Level currentLevel;
	public static int mouseX, mouseY;
	public static GameState gm = GameState.MainMenu;

	public void init() {
		player = new Player("Test Person", RPGMain.WIDTH / 2,
				RPGMain.HEIGHT / 2, 40, 40);
		currentLevel = new Level("Testing Level",
				Loader.loadImage("rpgGame/rpg_game_images/Level1.png"));
		currentLevel.addImageBoundary(0, 0, RPGMain.WIDTH, 30, null); // top
		currentLevel.addImageBoundary(0, 0, 50, RPGMain.HEIGHT, null); // left
		currentLevel.addImageBoundary(0, RPGMain.HEIGHT - 34, RPGMain.WIDTH,
				34, null); // bottom
		currentLevel.addImageBoundary(RPGMain.WIDTH - 40, 0, 40,
				RPGMain.HEIGHT, null); // right

		currentLevel.addImageBoundary(100, 100, 100, 100, null);

		initGUI();
	}

	public void initGUI() {
		singlePlayerButton = new Button(RPGMain.WIDTH / 2 - 220/2, RPGMain.HEIGHT / 2 - 135, 220, 50, "Single Player", Color.DARK_GRAY, Color.blue,
				RPGMain.WIDTH / 2 - 220/2 + 20, RPGMain.HEIGHT / 2 - 100, 30);
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void update(Graphics g, ImageObserver obs) {

		if (gm == GameState.MainMenu) {

		}

		else if (gm == GameState.Game) {
			// moveable screen
			currentLevel.render(g, obs, true);
			currentLevel.checkCollisions(player);
			//
			player.update();
			player.render(g, obs);
		}

	}

	public void gui(Graphics g, ImageObserver obs) {
		DrawHelp.drawFixedText(g, RPGPanel.currentFPS + "", Color.black, 10,
				20, player.getX(), player.getY());
		if (gm == GameState.MainMenu) {
			singlePlayerButton.render(g, obs);
		}
		
		else if (gm == GameState.Game) {
			
		}

		DrawHelp.drawFixedText(g, "(" + mouseX + ", " + mouseY + ")",
				Color.blue, mouseX, mouseY, player.getX(), player.getY());

	}

}
