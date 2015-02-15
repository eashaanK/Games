package com.lwjgl2D.main;

import javax.swing.JOptionPane;

import com.lwjgl2D.entities.implementations.Player;
import com.lwjgl2D.input.Input;
import com.lwjgl2D.mulitplayer.GameClient;
import com.lwjgl2D.mulitplayer.GameServer;

public class Game {

	private GameServer server;
	private GameClient client;

	public void init(){
		Player player = new Player(40, 500, 50, 50, "Player");
		player.setInputKeys(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
		CoreEngine.add(player);
		
		if(JOptionPane.showConfirmDialog(null, "Do u want to start server") == 0)
		{
			server = new GameServer(8888, -1, "Testing Server");
			server.start();
		}
		client = new GameClient("localhost", 8888);
		client.start();
		client.sendString("ping");
		
	}
		
	public void update(float delta){

	
	}
	
	public void close(){
		System.out.println("Game closed. Good bye!");
	}
}
