package Tester;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import rendering.Window;
import terrains.Terrain;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.ToolBox;
import entities.Bush;
import entities.Camera;
import entities.Flower;
import entities.Grass;
import entities.Light;
import entities.Player;
import entities.ThirdPersonCamera;
import entities.Tree;

/**
 *  Press f1 to switch between 1st Person and 2nd Person (Located in Player Class)
 *  In 3rd person mode, left mouse button to circle player, right for pitch, A and D keys to turn
 * 
 * https://www.youtube.com/watch?v=ZyzXBYVvjsg&list=PLRIWtICgwaX0u7Rf9zkZhLoLuZVfUksDP&index=15
 * 16
 * @author eashaan
 * 
 */
public class MainGameLoop {
	
	private static Terrain terrain;
	private static Light light;
	private static Loader loader;
	private static Player player;


	public static void main(String[] args) {

		DisplayManager.createDisplay();
		loader = new Loader();
		init(loader);

		
		MasterRenderer mRenderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			update();
			//terrains
			mRenderer.processTerrain(terrain);
			//entities
			mRenderer.processEntity(player);
			//camera and light
			mRenderer.render(light, player.getCurrentCamera());
			//display
			DisplayManager.updateDisplay();

		}
		mRenderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}
	
	private static void init(Loader loader){
			
		setupTerrain();
		
		light = new Light(new Vector3f(2000, 3000, 2000), new Vector3f(1, 1, 1));
	
		player = new Player(ToolBox.createTexturedModel(loader, "person", "playerTexture", false, false), new Vector3f(0, 0, 0), 0, 0, 0, 0.15f, 1.5f);
		//in game mode
		lockMouse();
	}
	
	private static void update(){
		player.move(terrain);
		
		//Pause menu
		if(ToolBox.getKeyStatus(Keyboard.KEY_ESCAPE) == 1)
		{
			if(Mouse.isGrabbed())
				freeMouse();
			else
				lockMouse();

		}
		
	
	}

	private static void setupTerrain(){
		TerrainTexture backG = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rText = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gText = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bText = new TerrainTexture(loader.loadTexture("path"));
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		TerrainTexturePack textPack = new TerrainTexturePack(backG, rText, gText, bText);

		terrain = new Terrain(0, -1, loader, textPack, blendMap, "heightmap", 10);
		
		for(int i = 0; i < 100; i++){
			//if((int)(Math.random() * 10) % 2==0)
			terrain.addTree(new Tree(ToolBox.createTexturedModel(loader, "tree", "tree", false, false, 10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, 1.3f));
			//else
				//new Tree(ToolBox.createTexturedModel(loader, "lowPolyTree", "lowPolyTree", false, false, 10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, 1);
		}
	//	for(int i = 0; i < 100; i++) //GrassModel comes in groups
		//	terrain.addGrass(new Grass(ToolBox.createTexturedModel(loader, "grassModel", "grassTexture", true, true, 10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, (float)(Math.random() * 0.5f + 0.2f)));
		for(int i = 0; i < 120; i++) //GrassModel comes in groups
			terrain.addBush(new Bush(ToolBox.createTexturedModel(loader, "fern", "fern", true, true,10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, (float)(Math.random() * 0.2f)));
	//	for(int i = 0; i < 50; i++) //GrassModel comes in groups
		//	terrain.addFlower(new Flower(ToolBox.createTexturedModel(loader, "grassModel", "flower", true, true,10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, (float)(Math.random() * 0.5f)));
		

	}
	
	private static void lockMouse(){
		Mouse.setCursorPosition(Window.GetWidth()/2, Window.GetHeight()/2);
		Mouse.setGrabbed(true);
	}
	
	private static void freeMouse(){
		Mouse.setGrabbed(false);
	}
}
