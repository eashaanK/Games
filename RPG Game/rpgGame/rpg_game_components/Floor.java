package rpg_game_components;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import rpg_game_helpers.Loader;

public class Floor extends Component {
	private Image grass, dirt, stone, water;
	private int tileSize = 40;
	
	private Tile[][] tiles;

	public Floor(int w, int h) {
		super(0, 0, w, h);
		this.grass = Loader.loadImage("rpgGame/rpg_game_images/Grass.png");
		this.dirt = Loader.loadImage("rpgGame/rpg_game_images/Dirt.png");
		this.stone = Loader.loadImage("rpgGame/rpg_game_images/Stone.png");
		this.water = Loader.loadImage("rpgGame/rpg_game_images/Water.png");

		tiles = new Tile[this.getWidth() / tileSize][this.getHeight() / tileSize];
		initFloor();
	}
	
	public void initFloor(){
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				tiles[x][y] = new Tile((byte)(Math.random() * 3 + 1));
			}
		}
	}

	public void update() {
	}

	public void render(Graphics g, ImageObserver obs) {
		for (int x = 0; x < tiles.length; x++) {
			for (int y = 0; y < tiles[x].length; y++) {
				switch(tiles[x][y].id){
				case Tile.OCCUPIED:
					//do nothing
					break;
				case Tile.GRASS:
					g.drawImage(grass, x * tileSize, y * tileSize, tileSize , tileSize , obs);
					break;
				case Tile.DIRT:
					g.drawImage(dirt, x * tileSize, y * tileSize, tileSize , tileSize , obs);
					break;
				case Tile.STONE:
					g.drawImage(stone, x * tileSize, y * tileSize, tileSize , tileSize , obs);
					break;
				case Tile.WATER:
					g.drawImage(water, x * tileSize, y * tileSize, tileSize , tileSize , obs);
					break;
				}
			}
		}
	}

	public String toString() {
		return "Floor: " + this.getBounds().toString();
	}

}
