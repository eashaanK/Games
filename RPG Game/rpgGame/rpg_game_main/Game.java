package rpg_game_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import rpg_game_components.Level;
import rpg_game_components.Player;
import rpg_game_helpers.DrawHelp;
import rpg_game_input.Button;
import rpg_game_map_packs.MapPack;
import ek_server_basic.Client;

public class Game {
	public static Player player;
	public static Button singlePlayerButton, multiPlayerButton;
	public static Level currentLevel;
	public static MapPack mapPack;
	public static int mouseX, mouseY;
	public static boolean isConnectedAsClient = false;
	public static GameState gm = GameState.MainMenu;
	private static Client client = null;

	public void init() {
		player = new Player("Test Person", RPGMain.WIDTH / 2,
				RPGMain.HEIGHT / 2, 40, 40);
		mapPack = new MapPack();
		
		currentLevel = mapPack.getLodgeMap();
		
		
		client = null;

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
			if(!this.isConnectedAsClient){
				Client client = new Client("localhost", 8888);
				Thread tC = new Thread(client);
				tC.start();
				this.isConnectedAsClient = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				client.joinRequest(player.getName());
				
				client.sendMessage("THIS IS A TEST MESSAGE FROM CLIENT");
			}
			if(this.isConnectedAsClient)
			{
				multiplayerGame();
			}
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
	
	private void multiplayerGame(){
	}
	
	public void attempDisconnect(){
		if(client != null)
			client.disconnect();
	}
	
	public static void stopClient(){
		client.fullStop();
	}

}
