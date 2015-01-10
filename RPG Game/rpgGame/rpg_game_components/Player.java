package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import rpg_game_helpers.DrawHelp;
import rpg_game_helpers.ImageDirection;
import rpg_game_helpers.Sprite;

public class Player extends Component {

	private String name;
	private float health = 100;
	private Sprite sprite;
	private int leftMult = 1, rightMult = 1, upMult = 1, downMult = 1;
	
	public Player(String name, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.name = name;
		sprite = new Sprite(0.2f);
		// up
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
		sprite.initCurrentImage(sprite.getDown().get(0));
	}

	public void updateImage(ImageDirection iDir) {
		sprite.updateImage(iDir);
	}

	public void update() {
		System.out.println(sprite.currentPath());
	}

	public void render(Graphics g, ImageObserver obs) {
		if (this.isAlive()) {
			// g.setColor(Color.red);
			// g.fillRect(this.getX(), this.getY(),
			// this.getWidth(),this.getHeight());
			g.drawImage(sprite.currentImg(), this.getX(), this.getY(),
					this.getWidth(), this.getHeight(), obs);
			g.setColor(Color.black);
			DrawHelp.drawText(g, 15, this.toString(),
					this.getX() - name.length() * 3 / 2, this.getY() - 10);
			DrawHelp.drawText(g, 15, ".",
					this.getX(), this.getY());
		} else {
			System.err.println("Player died");
		}
	}

	public String getName() {
		return this.name;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	@Override
	public void moveBy(int x, int y) {
		throw new IllegalStateException(
				"Cant call this method on player. Use move methods");
	}

	public void moveUp(int amt) {
		super.moveBy(0, -amt * this.upMult);
	}

	public void moveDown(int amt) {
		super.moveBy(0, amt * this.downMult);
	}

	public void moveLeft(int amt) {
		super.moveBy(-amt * this.leftMult, 0);

	}

	public void moveRight(int amt) {
		super.moveBy(amt * this.rightMult, 0);
	}


	/**
	 * Performs this operation: this.health -= amt;
	 * 
	 * @param amt
	 */
	public void reduceHealth(float amt) {
		this.health -= amt;
	}

	public void kill() {
		this.setHealth(0);
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sprite getSprite() {
		return this.sprite;
	}
	
	public void blockLeft(){
		this.leftMult = 0;
	}
	
	public void blockRight(){
		this.rightMult = 0;
	}
	
	public void blockUp(){
		this.upMult = 0;
	}
	
	public void blockDown(){
		this.downMult = 0;
	}
/////////////////////
	public void freeLeft(){
		this.leftMult = 1;
	}
	
	public void freeRight(){
		this.rightMult = 1;
	}
	
	public void freeUp(){
		this.upMult = 1;
	}
	
	public void freeDown(){
		this.downMult = 1;
	}


	@Override
	public String toString() {
		return this.getName() + " (" + this.getBounds().x + ", "
				+ this.getBounds().y + ") w: " + this.getWidth() + " h: "
				+ this.getHeight();
	}

}
