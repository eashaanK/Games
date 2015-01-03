package rpg_game_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import rpg_game_components.Level;
import rpg_game_components.Player;
import rpg_game_helpers.DrawHelp;
import rpg_game_helpers.Loader;
import rpg_game_input.Button;
import ek_server_basic.Client;

public class Game {
	public static Player player;
	public static Button singlePlayerButton, multiPlayerButton;
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
		singlePlayerButton = new Button(RPGMain.WIDTH / 2 - 220 / 2,
				RPGMain.HEIGHT / 2 - 135, 220, 50, "Single Player",
				Color.DARK_GRAY, Color.blue, RPGMain.WIDTH / 2 - 220 / 2 + 20,
				RPGMain.HEIGHT / 2 - 100, 30);
		multiPlayerButton = new Button(RPGMain.WIDTH / 2 - 220 / 2,
				RPGMain.HEIGHT / 2 - 45, 220, 50, "Multiplayer",
				Color.DARK_GRAY, Color.blue, RPGMain.WIDTH / 2 - 220 / 2 + 20,
				RPGMain.HEIGHT / 2 - 10, 30);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void update(Graphics g, ImageObserver obs) {

		switch (gm) {
		case MainMenu:
			break;
		case Game:
			// moveable screen
			currentLevel.render(g, obs, true);
			currentLevel.checkCollisions(player);
			/////////////////////////////
			player.update();
			player.render(g, obs);
			break;
		case MultiGame:
			Client client = new Client("loaclhost", 8888);
			Thread tC = new Thread(client);
			tC.start();
			break;
		case Pause:
			currentLevel.render(g, obs, true);
			player.render(g, obs);
			break;
		}
	}

	public void gui(Graphics g, ImageObserver obs) {
		DrawHelp.drawFixedText(g, RPGPanel.currentFPS + "", Color.black,
				RPGMain.WIDTH - 40, 20, player.getX(), player.getY());
		DrawHelp.drawFixedText(g, this.gm + "", Color.black,
				RPGMain.WIDTH / 2 - 40, 20, player.getX(), player.getY());

		switch (gm) {
		case MainMenu:
			singlePlayerButton.render(g, obs);
			multiPlayerButton.render(g, obs);
			break;
		case Game:

			break;
		case MultiGame:
			break;
			
		case Pause:
			DrawHelp.fillFixedRect(g, new Color(0, 0, 0, 220), 0, 0, RPGMain.WIDTH, RPGMain.HEIGHT, player.getX(), player.getY());
			DrawHelp.drawFixedText(g, "Paused", Color.red, RPGMain.WIDTH /2 - 50 , RPGMain.HEIGHT / 2, player.getX(), player.getY(), 50);
			break;
		}

		DrawHelp.drawFixedText(g, "(" + mouseX + ", " + mouseY + ")",
				Color.blue, mouseX, mouseY, player.getX(), player.getY());

	}

}
