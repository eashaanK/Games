package com.ek.MyGameClasses;

import com.ek.core.Game;
import com.ek.core.GameObject;
import com.ek.core.Vector3f;

public class Chunk {

	public int gridX, gridY; //grid positions
	
	public static final int SIZE = 10;
	
	public GameObject plane;

	public Chunk(int gridX, int gridY) {
		this.gridX = gridX;
		this.gridY = gridY;
		plane = Helper.getPlane();
		
		plane.GetTransform().SetScale(new Vector3f(0.62f, 0.62f, 0.62f));

	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Chunk))
			return false;
		Chunk c = (Chunk)o;
		
		return (c.gridX == gridX) && (c.gridY == gridY);
	}
	
	public void addSelf(Game g){
		GameObject temp = plane;
		temp.GetTransform().SetPos(new Vector3f(gridX * SIZE, 0, gridY * SIZE));
		g.AddObject(plane);
	}
	
	public String getHashMapPointer(){
		return "(" + gridX + ", " + gridY + ")";
	}
}
