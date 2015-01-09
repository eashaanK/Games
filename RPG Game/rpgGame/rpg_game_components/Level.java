package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import rpg_game_main.RPGMain;

public class Level {

	private String name;
	private Image image;
	private ArrayList<Boundary> imageBoundaries;
	private Rectangle bounds;

	public Level(String name, Image image, int x, int y, int width, int height) {
		this.name = name;
		this.image = image;
		this.imageBoundaries = new ArrayList<Boundary>();
		this.bounds = new Rectangle(x, y, width, height);
	}

	public void addImageBoundary(int x, int y, int w, int h, Image i) {
		this.imageBoundaries.add(new Boundary(x, y, w, h, i));
	}

	public void render(Graphics g, ImageObserver obs, boolean renderBound) {
		g.drawImage(this.image, bounds.x, bounds.y, this.getWidth(), this.getHeight(), obs);
		// draws the individual boundaries
		for (Boundary i : imageBoundaries) {
			if (i.getImage() != null)
				g.drawImage(i.getImage(), i.getX(), i.getY(), i.getWidth(),
						i.getHeight(), obs);
			if (renderBound) {
				g.setColor(Color.red);
				g.drawRect(i.getX(), i.getY(), i.getWidth(), i.getHeight());
			}
		}
	}

	public void checkCollisions(Player player) {
		boolean collidingAnything = false;
		for (int i = 0; i < this.imageBoundaries.size(); i++) {
			Rectangle imageRect = new Rectangle(imageBoundaries.get(i).getX(),
					imageBoundaries.get(i).getY(), imageBoundaries.get(i)
							.getWidth(), imageBoundaries.get(i).getHeight());

			if (player.isColliding(imageRect)) {

				collidingAnything = true;
				
				Rectangle playerBound = player.getBounds();
				
				String temp = this.checkLeftRight(playerBound, imageRect);
				//System.out.println(temp);
				if(temp==null)
				{
					//center
				}
				else if(temp.equals("Left"))
				{
					player.blockLeft();
					//System.err.println("Colliding " + i + " from " + temp + " not freeing it up ");

				}
				else if(temp.equals("Right"))
				{
					player.blockRight();
					//System.err.println("Colliding " + i + " from " + temp + " not freeing it up ");

				}
				
				
				else
				{
					//System.out.println("Not colliding " + i);
					player.freeLeft();
					player.freeRight();
				}
				

				////////////////////////////////////////////////////////////////
				temp = this.checkUpDown(playerBound, imageRect);
				if(temp==null)
				{
					
				}
				else if(temp.equals("Up"))
				{
					player.blockUp();
					//System.err.println("Colliding " + i + " from " + temp + " not freeing it up ");

				}
				else if(temp.equals("Down"))
				{
					player.blockDown();
					//System.err.println("Colliding " + i + " from " + temp + " not freeing it up ");

				}
				
				
				else
				{
					//System.out.println("Not colliding " + i);
					player.freeDown();
					player.freeUp();
				}
			}
			
			if(!collidingAnything){
				//System.out.println("Not colliding " + i);
				player.freeDown();
				player.freeLeft();
				player.freeRight();
				player.freeUp();
			}
				
			
		}
	}
	
	private String checkUpDown(Rectangle playerBound, Rectangle imageRect){
		Rectangle inter = playerBound.intersection(imageRect);
		if (inter.width >= inter.height) {
			if (playerBound.y <= imageRect.y) {
				return "Down";
			} else if (playerBound.y >= imageRect.y) {
				return "Up";
			}
		}
		return null;
	}
	
	private String checkLeftRight(Rectangle playerBound, Rectangle imageRect){
		Rectangle inter = playerBound.intersection(imageRect);
		if (inter.width <= inter.height) {
			if (playerBound.x >= imageRect.x) {
				return "Left";
			} else if (playerBound.x <= imageRect.x) {
				return "Right";
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}
	
	public int getWidth(){
		return this.bounds.width;
	}
	
	public int getHeight(){
		return this.bounds.height;
	}
	
	public void setWidth(int w){
		this.bounds.width = w;
	}
	
	public void setHeight(int h){
		this.bounds.height = h;
	}

}
