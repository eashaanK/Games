package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Player extends Component
{
	
	private String name;
	private float health = 100;
	
	public Player(String name, int x, int y, int w, int h){
		super(x, y, w, h);
		this.name = name;
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g, ImageObserver obs){
		if(this.isAlive())
		{
			g.setColor(Color.red);
			g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		}
		else{
			System.out.println("Player died");
		}
	}
	
	
	public String getName(){
		return this.name;
	}
	
	public boolean isAlive(){
		return health > 0;
	}
	
	
	public void setHealth(float health){
		this.health = health;
	}
	
	/**
	 * Performs this operation: this.health -= amt;
	 * @param amt
	 */
	public void reduceHealth(float amt){
		this.health -= amt;
	}
	
	public void kill(){
		this.setHealth(0);
	}
	
	public void setName(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return this.getName() + ": " + this.getBounds().toString();
	}
	
	

}
