package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player
{
	
	private Rectangle bounds;
	private String name;
	private float health = 100;
	
	public Player(String name, int x, int y, int w, int h){
		this.bounds = new Rectangle(x, y, w, h);
		this.name = name;
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		if(this.isAlive())
		{
			g.setColor(Color.red);
			g.fillRect(this.bounds.x, this.bounds.y, bounds.width, this.bounds.height);
		}
		else{
			System.out.println("Player died");
		}
	}
	
	public int getX(){
		return this.bounds.x;
	}
	
	public int getY(){
		return this.bounds.y;
	}
	
	public int getWidth(){
		return this.bounds.width;
	}
	
	public int getHeight(){
		return this.bounds.height;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isColliding(Rectangle rect){
		return bounds.intersects(rect);
	}
	
	public boolean isColliding(int x, int y, int width, int height){
		Rectangle r = new Rectangle(x, y, width, height);
		return bounds.intersects(r);
	}
	
	public boolean isAlive(){
		return health > 0;
	}
	
	public Rectangle getBounds(){
		return this.bounds;
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
	
	public void setX(int x){
		bounds.x = x;
	}
	
	public void setY(int y){
		bounds.y = y;
	}
	public void setXWidth(int w){
		bounds.width = w;
	}
	public void setHeight(int h){
		bounds.height = h;
	}
	
	public void setBounds(Rectangle r){
		this.bounds = r;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void moveBy(int xI, int yI){
		this.bounds.x += xI;
		this.bounds.y += yI;
	}
	

}
