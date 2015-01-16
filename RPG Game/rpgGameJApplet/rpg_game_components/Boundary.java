package rpg_game_components;

import java.awt.Image;

public class Boundary {

	private int x, y, width, height;
	private Image image;
	
	public Boundary(int x, int y, int w, int h, Image g){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.image = g;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
