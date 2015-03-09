package infagen_Main;

import infagen_entity.Player;
import processing.core.PApplet;

public class Game {

	private PApplet p;
	private Player player;
	
	public Game(PApplet parent){
		this.p = parent;
		player = new Player("Player", p.width/2, p.height/2, 100, 100);
		Core.add(player);
	}
	
	public void update(float delta){
		
	}
}
