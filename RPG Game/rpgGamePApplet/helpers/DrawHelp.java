package helpers;

import java.awt.Color;
import java.awt.Point;

import main.Game;
import processing.core.PApplet;
import processing.core.PVector;


public class DrawHelp {

	public static void drawFixedText(PApplet g, String text, Color c, float x, float y, float anchorX, float anchorY){
		drawFixedText(g, text, c, x, y, anchorX, anchorY, 20);
	}
	
	public static void drawFixedText(PApplet g, String text, Color c, float x, float y, float anchorX, float anchorY, float size){
		g.fill(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
		PVector pos = getFixedPoint(x, y, anchorX, anchorY);
		g.textSize(size);
		g.text(text, pos.x, pos.y);
	}
	
	public static void drawFixedRect(PApplet g, Color c, float x, float y, float width, float height, float anchorX, float anchorY){
		g.fill(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
		PVector temp = getFixedPoint(x, y, anchorX, anchorY);
		g.rect(temp.x, temp.y, width, height);
	}
	
	public static PVector getFixedPoint(float x, float y, float anchorX, float anchorY){
		return new PVector(anchorX - (Game.WIDTH / 2 - x ), anchorY - (Game.HEIGHT / 2 - y ));
	}
}
