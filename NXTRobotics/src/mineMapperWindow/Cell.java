package mineMapperWindow;

import java.awt.Rectangle;

public class Cell {
	
	public static final float CELLSIZE = 10f;

	private boolean isActive;
	
	private float row, col;
	
	private float r = 1, g = 1, b = 1;
	
	public Cell(float r, float c){
		isActive = false;
		this.row = r;
		this.col = c;
	}
	
	public boolean getState(){
		return isActive;
	}
	
	public void activate(){
		this.isActive = true;
	}
	
	public void updateColor(float delta){
		g -= delta * 0.08;
		b -= delta * 0.08;
	}
	
	public void render(){
		if(isActive)
			Draw.color(r, g, b, 1);
		else
			Draw.color(0, 0, 0, 1);
		Draw.drawRect(row * CELLSIZE, col * CELLSIZE, CELLSIZE, CELLSIZE, 0);
	}
	
	public boolean IsIntersectingRobot(Robot r){
		Rectangle self = new Rectangle((int)(row * CELLSIZE), (int)( col * CELLSIZE), (int)CELLSIZE, (int)CELLSIZE);
		return self.intersects(r.getCollider());
	}
	
}
