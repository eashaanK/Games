package cb2Drpg_refereces;

import java.util.ArrayList;
import java.util.List;

public class Ref {

	//nput
	public static List<Integer> keysHeld = new ArrayList<Integer>();
	public static List<Integer> mouseButtonsHeld = new ArrayList<Integer>();
	
	public static boolean isKeyDown(char key){
		return (Ref.keysHeld.contains(new Integer(key)));
	}
	
	public static boolean isMouseButtonDown(char button){
		return (Ref.keysHeld.contains(new Integer(button)));
	}
	
	public static int mouseX, mouseY;

	//Screen
	public static final int PIXEL_SIZE = 3;
	public static final int TILE_SIZE = 16;

	//Chunks
	public static final int TILES_PER_CHUNK_X = 16;
	public static final int TILES_PER_CHUNK_Y = 16;

	public static final int CHUNK_AMT_X = 5; //chunks around u
	public static final int CHUNK_AMT_Y = 5;

}
