package gameObject;

import processing.core.PApplet;
import processing.core.PImage;
import sprite.Sprite;

public class Player extends GameObject{

	public Player(PApplet p, float x, float y, float width, float height,
			String name) {
		super(p, x, y, width, height, name);
		Sprite sprite = this.getSprite();
		sprite.addUp("rpgGame/rpg_game_images/boy up 1.png");
		sprite.addUp("rpgGame/rpg_game_images/boy up 2.png");
		sprite.addUp("rpgGame/rpg_game_images/boy up 3.png");

		// down
		sprite.addDown("rpgGame/rpg_game_images/boy down 1.png");
		sprite.addDown("rpgGame/rpg_game_images/boy down 2.png");
		sprite.addDown("rpgGame/rpg_game_images/boy down 3.png");

		// left
		sprite.addLeft("rpgGame/rpg_game_images/boy left 1.png");
		sprite.addLeft("rpgGame/rpg_game_images/boy left 2.png");
		sprite.addLeft("rpgGame/rpg_game_images/boy left 3.png");

		// right
		sprite.addRight("rpgGame/rpg_game_images/boy right 1.png");
		sprite.addRight("rpgGame/rpg_game_images/boy right 2.png");
		sprite.addRight("rpgGame/rpg_game_images/boy right 3.png");
		sprite.initCurrentPImage(sprite.getDown().get(0));
	}

	
	
	
}
