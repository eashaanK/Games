package com.lwjgl2D.main;

import com.lwjgl2D.entities.implementations.Player;
import com.lwjgl2D.input.Input;

public class Game {

	
	public void init(){
		Player player = new Player(40, 500, 50, 50, "Player");
		player.setInputKeys(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);
		CoreEngine.add(player);
		
		player = new Player(400, 500, 50, 50, "Player");
		player.setInputKeys(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT);
		CoreEngine.add(player);
	}
		
	public void update(float delta){
	
	
	}
	
	public void close(){
		System.out.println("Game closed. Good bye!");
	}
}
