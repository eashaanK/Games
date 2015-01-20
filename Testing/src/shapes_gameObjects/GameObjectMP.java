package shapes_gameObjects;

import java.awt.Color;

import processing.core.PApplet;
import shapes_controls.Controls;

public class GameObjectMP extends GameObject{

	public boolean isConnected;
	
	public GameObjectMP(PApplet parent, float x, float y, float a, Color c,
			String name) {
		super(parent, x, y, a, c, name);
		
	}
	
	@Override
	public void update(Controls controls){
		throw new UnsupportedOperationException("U cannot run update method on a multiplayer gameobject: " + this.toString());
	}

	public void updateValues(float x, float y, float angle, Color color,
			String name) {
		this.x = x;
		this.y = y;
		this.a = angle;
		this.c = color;
		this.name = name;
	}

}
