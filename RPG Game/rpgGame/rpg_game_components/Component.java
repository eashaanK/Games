package rpg_game_components;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public abstract class Component {
private Rectangle bounds;
	
	public Component(int x, int y, int w, int h){
		this.bounds = new Rectangle(x, y, w, h);
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g, ImageObserver obs);
	
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
	
	public boolean isColliding(Rectangle rect){
		return bounds.intersects(rect);
	}
	
	public boolean isColliding(int x, int y, int width, int height){
		Rectangle r = new Rectangle(x, y, width, height);
		return bounds.intersects(r);
	}
	
	public Rectangle getBounds(){
		return this.bounds;
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
	
	public void moveBy(int xI, int yI){
		this.bounds.x += xI;
		this.bounds.y += yI;
	}
	
	public abstract String toString();

}
