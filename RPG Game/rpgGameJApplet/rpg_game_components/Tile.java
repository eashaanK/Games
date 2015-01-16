package rpg_game_components;

public class Tile {

	public byte id = -1;
	
	public static final byte OCCUPIED = 0;
	public static final byte GRASS = 1;
	public static final byte DIRT = 2;
	public static final byte WATER = 3;
	public static final byte STONE = 4;


	
	public Tile(byte ID){
		this.id = ID;
	}
}
