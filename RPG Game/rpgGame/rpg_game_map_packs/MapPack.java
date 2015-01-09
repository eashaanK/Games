package rpg_game_map_packs;

import rpg_game_components.Level;
import rpg_game_helpers.Loader;
import rpg_game_main.RPGMain;

public class MapPack {

	public Level testLevel;
	public Level lodgeMap;

	
	public MapPack(){
		this.testLevel();
		this.lodgeMap();
	}
	
	private void testLevel(){
		testLevel = new Level("Test Level",
				Loader.loadImage("rpgGame/rpg_game_images/Level1.png"), 0, 0, 1000, 1000);
		testLevel.addImageBoundary(0, 0, RPGMain.WIDTH, 30, null); // top
		testLevel.addImageBoundary(0, 0, 50, RPGMain.HEIGHT, null); // left
		testLevel.addImageBoundary(0, RPGMain.HEIGHT - 34, RPGMain.WIDTH,
				34, null); // bottom
		testLevel.addImageBoundary(RPGMain.WIDTH - 40, 0, 40,
				RPGMain.HEIGHT, null); // right
		
	}
	
	public Level getTestLevel(){
		return this.testLevel;
	}
	
	private void lodgeMap(){
		lodgeMap = new Level("Test Level",
				Loader.loadImage("rpgGame/rpg_game_images/lodge_map.png"), 0, 0, 2000, 2100);
	

		//Horizontal
		lodgeMap.addImageBoundary(395, 780, 100, 15, null); //left most middle border
		
		lodgeMap.addImageBoundary(560, 780, 320, 15, null); //left middle border (next to left most middle border
		
		lodgeMap.addImageBoundary(890, 870, 100, 15, null); //middle horizontal top left
		
		lodgeMap.addImageBoundary(1065, 870, 90, 15, null); //middle horizontal top center

		lodgeMap.addImageBoundary(1230, 870, 260, 15, null); //middle horizontal top right

		lodgeMap.addImageBoundary(890, 960, 100, 11, null); //middle horizontal bottom left
		
		lodgeMap.addImageBoundary(1065, 960, 90, 11, null); //middle horizontal bottom center

		lodgeMap.addImageBoundary(1230, 960, 260, 11, null); //middle horizontal bottom right

		lodgeMap.addImageBoundary(400, 1660, 600, 3, null); //bottom
		
		lodgeMap.addImageBoundary(893, 1392, 580, 15, null); //bottom up

		lodgeMap.addImageBoundary(400, 435, 1095, 8, null); //top 1495 - 400

		
		//Vertical
		lodgeMap.addImageBoundary(385, 430, 4, 1240, null); //left most border

		lodgeMap.addImageBoundary(1500, 430, 4, 980, null); //left most border1240 - 580

		
	}
	
	public Level getLodgeMap(){
		return this.lodgeMap;
	}
}
