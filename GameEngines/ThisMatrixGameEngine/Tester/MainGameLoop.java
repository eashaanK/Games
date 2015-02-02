package Tester;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.Window;
import terrains.Terrain;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.ToolBox;
import entities.Bush;
import entities.Entity;
import entities.Light;
import entities.LightModel;
import entities.Player;
import entities.Tree;
import guis.GuiRenderer;
import guis.GuiTexture;

/**
 *  Press f1 to switch between 1st Person and 2nd Person (Located in Player Class)
 *  In 3rd person mode, left mouse button to circle player, right for pitch, A and D keys to turn
 * 
 * @author eashaan
 * 
 */
public class MainGameLoop {
	
	public static float SCALE = 0.25f;
	
	private static Terrain terrain;
	private static Loader loader;
	private static Player player;
	private static ToolBox toolBox;
	
	private static List<GuiTexture> guis = new ArrayList<GuiTexture>();
	private static List<Light> lights = new ArrayList<Light>();
	public static List<Entity> entities = new ArrayList<Entity>();


	public static void main(String[] args) {

		DisplayManager.createDisplay();
		loader = new Loader();
		toolBox = new ToolBox(loader);
		init(loader);

		
		MasterRenderer mRenderer = new MasterRenderer();
		GuiRenderer guiRenderer = new GuiRenderer(loader);
		while (!Display.isCloseRequested()) {
			update();
			//3d stuff
			mRenderer.processTerrain(terrain);
			mRenderer.processEntity(player);
			for(int i = entities.size() - 1; i >= 0; i--){
				//if(!entity.isAlive()){
				//entity.remove(i);
				//continue;
				//}
				mRenderer.processEntity(entities.get(i));
			}
			mRenderer.render(lights, player.getCurrentCamera());
			
			//GUI stuff
			guiRenderer.render(guis);
			DisplayManager.updateDisplay();

		}
		guiRenderer.cleamUp();
		mRenderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}
	
	private static void init(Loader loader){
			
		setupTerrain();
		
		Light sun = new Light(new Vector3f(0, 3000, -200), new Vector3f(1, 1, 1));
		LightModel lampPost = new LightModel(new Vector3f(0, 4, 0), new Vector3f(2, 0, 0), new Vector3f(1, 0.01f, 0.02f), new Entity(toolBox.getLampTexturedModel(), new Vector3f(0, 0, 0), 0, 0, 0, SCALE));
		lights.add(sun);
		lights.add(lampPost);
	

		//entities.add(new Entity(toolBox.getLampTexturedModel(), new Vector3f(0, 0, 0), 0, 0, 0, SCALE));
	
		player = new Player(toolBox.createTexturedModel(loader, "person", "playerTexture", false, false), new Vector3f(200, 0, -1), 0, 0, 0, SCALE, 1.5f);
		//in game mode
		
		guis.add(new GuiTexture(loader.loadTexture("socuwan"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f), 0, 0, 0.1f));
		guis.add(new GuiTexture(loader.loadTexture("socuwan"), new Vector2f(0.3f, 0.5f), new Vector2f(0.25f, 0.25f), 0, 0, 0.1f));

		
		lockMouse();
	}
	
	private static void update(){
		//Pause menu
		if(toolBox.getKeyStatus(Keyboard.KEY_ESCAPE) == 1)
		{
			if(Mouse.isGrabbed())
				freeMouse();
			else
				lockMouse();
		}
		
		if(Mouse.isGrabbed()) //unpaused
			player.move(terrain);		
	
	}
	
	
	///////////////////////////////////////////////////////////////////HELPER METHODS////////////////////////////////////////

	private static void setupTerrain(){
		TerrainTexture backG = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rText = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gText = new TerrainTexture(loader.loadTexture("grassFlowers"));
		TerrainTexture bText = new TerrainTexture(loader.loadTexture("path"));
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		TerrainTexturePack textPack = new TerrainTexturePack(backG, rText, gText, bText);

		terrain = new Terrain(0, -1, loader, textPack, blendMap, "heightmap", (float)(Math.random() * 15 + 10));
		
		
		float x, y, z;
		
		for(int i = 0; i < 100; i++)
			terrain.addTree(new Tree(toolBox.createTexturedModel(loader, "pine", "pine", false, true, 10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, SCALE * 2));
		
		
	//	for(int i = 0; i < 100; i++) //GrassModel comes in groups
		//	terrain.addGrass(new Grass(toolBox.createTexturedModel(loader, "grassModel", "grassTexture", true, true, 10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, SCALE)));
		
		for(int i = 0; i < 100; i++){ //GrassModel comes in groups
			x = (float)(Math.random() * Terrain.SIZE);
			y = (float)(Math.random() * Terrain.SIZE);
			z = -(float)(Math.random() * Terrain.SIZE);

			Bush bush = new Bush(toolBox.getFernTexturedModel(), (int)(Math.random() * 4), new Vector3f(x, y, z), 0, 0, 0, SCALE);
			terrain.addBush(bush);
		}
	//	for(int i = 0; i < 50; i++) //GrassModel comes in groups
		//	terrain.addFlower(new Flower(ToolBox.createTexturedModel(loader, "grassModel", "flower", true, true,10, 1), new Vector3f((float)(Math.random() * Terrain.SIZE), 0, (float)(Math.random() * -Terrain.SIZE)), 0, 0, 0, SCALE)));
		

	}
	
	private static void lockMouse(){
		Mouse.setCursorPosition(Window.GetWidth()/2, Window.GetHeight()/2);
		Mouse.setGrabbed(true);
	}
	
	private static void freeMouse(){
		Mouse.setGrabbed(false);
	}
}
