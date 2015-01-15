package rpg_game_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import rpg_game_components.Level;
import rpg_game_components.MultiplayerPlayer;
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
	public static Client client = null;
	public static ArrayList<String> userNamesOnline;
	public static Queue<MultiplayerPlayer> onlinePlayers;
	private String host;

	public void init() {
		String tempName = JOptionPane.showInputDialog("Enter your name: ");
	//	host = JOptionPane.showInputDialog("Enter host IP address");
		host = "localhost";
		player = new Player(tempName, 800,
				950, 40, 40, Player.BOY);
		mapPack = new MapPack();
		
		currentLevel = mapPack.getLodgeMap();
		client = null;
		
		userNamesOnline = new ArrayList<String>();

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
			currentLevel.render(g, obs, false);
			currentLevel.checkCollisions(player);
			
			

			/////////////////////////////
			player.update();
			player.render(g, obs);
			break;
		case MultiGame:
			if(!this.isConnectedAsClient){				
				client = new Client(host, 8888);
				Thread tC = new Thread(client);
				tC.start();
				this.isConnectedAsClient = true;
				this.onlinePlayers = new LinkedList<MultiplayerPlayer>();
				try {
					Thread.sleep(1000); //wait for client to start
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				client.joinRequest(player.getName());
				
				client.sendMessage("THIS IS A TEST MESSAGE FROM CLIENT");
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					this.isConnectedAsClient = false;

					e.printStackTrace();
				}
				
			}
			if(this.isConnectedAsClient)
			{
				multiplayerGame(g, obs);
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
			//black rectangle with list of player
			DrawHelp.fillFixedRect(g, new Color(0, 0, 0, 200), RPGMain.WIDTH - 200, 40, 180, RPGMain.HEIGHT - 150, player.getX(), player.getY());
			for(int i = 0; i < userNamesOnline.size(); i++){
				DrawHelp.drawFixedText(g, userNamesOnline.get(i), Color.white, RPGMain.WIDTH - 200, 60 + 15 * i, player.getX(), player.getY(), 15);
			}
			break;
			
		case Pause:
			DrawHelp.fillFixedRect(g, new Color(0, 0, 0, 220), 0, 0, RPGMain.WIDTH, RPGMain.HEIGHT, player.getX(), player.getY());
			DrawHelp.drawFixedText(g, "Paused", Color.red, RPGMain.WIDTH /2 - 50 , RPGMain.HEIGHT / 2, player.getX(), player.getY(), 50);
			break;
		}

		DrawHelp.drawFixedText(g, "(" + mouseX + ", " + mouseY + ")",
				Color.blue, mouseX, mouseY, player.getX(), player.getY());
	}
	
	public static void updateUserOnline(String[] names){
		userNamesOnline.clear();
		for(int i = 0; i < names.length; i++){
			userNamesOnline.add(names[i]);
		}
	}
	
	private void multiplayerGame(Graphics g, ImageObserver obs){
		// moveable screen
		currentLevel.render(g, obs, false);
		currentLevel.checkCollisions(player);
		
		/////////////////////////////
	/*	for(MultiplayerPlayer p: onlinePlayers){
			p.update();
			p.render(g, obs);
		}*/
		MultiplayerPlayer p = onlinePlayers.poll();
		if(p != null){
			p.update();
			p.render(g, obs);
		}
		
		//System.out.println(onlinePlayers.size());
		
		player.update();
		player.render(g, obs);
	}
	
	public void attempDisconnect(){
		if(client != null){
			client.disconnect();
			this.onlinePlayers.clear();
			System.out.println("List Cleared");
		}
	
	}
	
	public static void stopClient(){
		if(client != null){
			client.fullStop();
			isConnectedAsClient = false;
			onlinePlayers.clear();
			System.out.println("List Cleared");

		}
	}

}
