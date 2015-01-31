package cb2Drpg_refereces;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ref {

	//nput
	public static List<Integer> keysHeld = new CopyOnWriteArrayList<Integer>();
	public static List<Integer> mouseButtonsHeld = new CopyOnWriteArrayList<Integer>();
	
	public static boolean isKeyDown(char key){
		return (Ref.keysHeld.contains(new Integer(key)));
	}
	
	public static boolean isMouseButtonDown(char button){
		return (Ref.keysHeld.contains(new Integer(button)));
	}
	
	public static int mouseX, mouseY;

	//Screen
	public static final int PIXEL_SIZE = 4;
	public static final int TILE_SIZE = 16;

	//Chunks
	public static final int TILES_AMOUNT_X = 1; //tiles per chunk
	public static final int TILES_AMOUNT_Y = 1;

	public static final int CHUNK_AMT_X = 5; //chunks around u
	public static final int CHUNK_AMT_Y = 5;
	
	//Game
	public static final int SEED = 1131221234;

}
