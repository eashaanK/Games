package rpg_game_components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import rpg_game_helpers.DrawHelp;
import rpg_game_helpers.ImageDirection;

public class Player extends Component {

	private String name;
	private float health = 100;
	private ArrayList<Image> up, down, left, right;
	private Image currentImage;
	private int uI, dI, lI, rI;
	private float currentImgCount, imageChangeSpeed = 0.30f;

	public Player(String name, int x, int y, int w, int h) {
		super(x, y, w, h);
		this.name = name;
		this.up = new ArrayList<Image>();
		this.down = new ArrayList<Image>();
		this.left = new ArrayList<Image>();
		this.right = new ArrayList<Image>();

		// up
		up.add(new ImageIcon("rpgGame/rpg_game_images/boy up 1.png").getImage());
		up.add(new ImageIcon("rpgGame/rpg_game_images/boy up 2.png").getImage());
		up.add(new ImageIcon("rpgGame/rpg_game_images/boy up 3.png").getImage());

		// down
		down.add(new ImageIcon("rpgGame/rpg_game_images/boy down 1.png").getImage());
		down.add(new ImageIcon("rpgGame/rpg_game_images/boy down 2.png").getImage());
		down.add(new ImageIcon("rpgGame/rpg_game_images/boy down 3.png").getImage());
		
		// left
		left.add(new ImageIcon("rpgGame/rpg_game_images/boy left 1.png").getImage());
		left.add(new ImageIcon("rpgGame/rpg_game_images/boy left 2.png").getImage());
		left.add(new ImageIcon("rpgGame/rpg_game_images/boy left 3.png").getImage());
		
		//right
		right.add(new ImageIcon("rpgGame/rpg_game_images/boy right 1.png").getImage());
		right.add(new ImageIcon("rpgGame/rpg_game_images/boy right 2.png").getImage());
		right.add(new ImageIcon("rpgGame/rpg_game_images/boy right 3.png").getImage());


		// start off as looking down
		currentImage = up.get(2);
	}
	
	private int updateImg(ArrayList<Image> list, int counter){
		final float maxCount = 1;
		if(this.currentImgCount >= maxCount){
			counter++;
			if(counter >= list.size())
				counter = 0;
			this.currentImage = list.get(counter);
			this.currentImgCount = 0;
		}
		this.currentImgCount+= imageChangeSpeed;		
		return counter;
	}

	public void updateImage(ImageDirection iDir) {
		switch (iDir) {
		case up:
			uI = this.updateImg(up, uI);
			break;
		case down:
			dI = this.updateImg(down, dI);
			break;
		case left:
			lI = this.updateImg(left, lI);
			break;
		case right:
			rI = this.updateImg(right, rI);
			break;
		}
	}

	public void update() {

	}

	public void render(Graphics g, ImageObserver obs) {
		if (this.isAlive()) {
		//	g.setColor(Color.red);
		//	g.fillRect(this.getX(), this.getY(), this.getWidth(),this.getHeight());
			g.drawImage(this.currentImage, this.getX(), this.getY(), this.getWidth(), this.getHeight(), obs);
			g.setColor(Color.black);
			DrawHelp.drawText(g, 15, name, this.getX() - name.length() * 3 / 2,  this.getY() - 10);
		} else {
			System.out.println("Player died");
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

	@Override
	public String toString() {
		return this.getName() + ": " + this.getBounds().toString();
	}

}
