package com.lwjgl2D.entities.implementations;

import com.lwjgl2D.entities.interfaces.AbstractMoveableGameObject;
import com.lwjgl2D.graphics.Draw;
import static org.lwjgl.opengl.GL11.*;


public class Player extends AbstractMoveableGameObject{

	public Player(float x, float y, float w, float h, String name) {
		super(x, y, w, h, name);
	}

	@Override
	public void draw() {
		glColor3f(0, 1, 0);
		Draw.rectRTS(x, y, width, height);
	}

	
}
