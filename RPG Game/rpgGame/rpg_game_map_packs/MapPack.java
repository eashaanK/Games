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
				Loader.loadImage("rpgGame/rpg_game_images/lodge_map.png"), 0, 0, 2000, 2000);
	
		//lodgeMap.addImageBoundary(384, 410, 11, 1180, null);

		//lodgeMap.addImageBoundary(0, 0, RPGMain.WIDTH, 30, null); // top
	//	lodgeMap.addImageBoundary(0, 0, 50, RPGMain.HEIGHT, null); // left
	//	lodgeMap.addImageBoundary(0, RPGMain.HEIGHT - 34, RPGMain.WIDTH,
	//			34, null); // bottom
	//	lodgeMap.addImageBoundary(RPGMain.WIDTH - 40, 0, 40,
	//			RPGMain.HEIGHT, null); // right
		
	}
	
	public Level getLodgeMap(){
		return this.lodgeMap;
	}
}
