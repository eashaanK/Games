package cb2Drpg_gameObjects;

public class Player {

	private int x, y, width, height;
	
	public Player(int x, int y, int width, int height){
		this.x=x;
		this.y = y;
		this.width = width;
		this.height = height;
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
	
	public void moveBy(int dx, int dy){
		this.x += dx;
		this.y += dy;
	}
}
