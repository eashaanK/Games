package com.infagen.core;

import com.infagen.renderEngines.Time;

public class Game {
	
	public Game(){
		init();
	}
	
	private void init(){
		System.out.println("Game Started");
	}

	public void update() {
		System.out.println("Game updated " + Time.getDelta());

	}

	public void close() {
		System.out.println("Game Closed");
	}

}
