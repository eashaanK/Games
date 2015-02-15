package com.lwjgl2D.main;

import com.lwjgl2D.entities.implementations.Player;

public class Game {

	
	public void init(){
		Player player = new Player(100, 100, 50, 50, "Player");
		player.setDX(0.5f);
		CoreEngine.add(player);
	}
		
	public void update(float delta){
	
		
	
	}
	
	public void close(){
		System.out.println("Game closed. Good bye!");
	}
}
