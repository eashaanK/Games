package rpg_game_input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import rpg_game_helpers.DrawHelp;
import rpg_game_main.Game;

public class Button {
	
	private Rectangle bounds;
	private Point textPos;
	private String text;
	private final Color defbC;
	private Color buttonColor, textColor;
	private Image buttonImage;
	public boolean shadow = true;

	public Button(int x, int y, int w, int h, String text, Color button, Color textC, int tx, int ty){
		this.bounds = new Rectangle(x, y, w, h);
		this.text = text;
		this.buttonColor = button;
		this.buttonImage = null;
		defbC = buttonColor;
		this.textColor = textC;
		this.textPos = new Point(tx, ty);
	}
	
	public Button(int x, int y, int w, int h, String text, Image buttonImage, Color textC, int tx, int ty){
		this.bounds = new Rectangle(x, y, w, h);
		this.text = text;
		this.buttonColor = null;
		defbC = buttonColor;
		this.buttonImage = buttonImage;
		this.textColor = textC;
		this.textPos = new Point(tx, ty);
	}
	
	public void render(Graphics g, ImageObserver obs){
		
		Point buPos = DrawHelp.getFixedPoint(bounds.x, bounds.y, Game.player.getX(), Game.player.getY());
		Point scTxtPos = DrawHelp.getFixedPoint(textPos.x, textPos.y, Game.player.getX(), Game.player.getY());
		
		//draw shadow
		if(shadow){
			g.setColor(new Color(0, 0, 0, 150));
			g.fillRect(buPos.x + 3, buPos.y + 3, bounds.width, bounds.height);
		}
		//draw button
		if(this.buttonImage == null){
			g.setColor(this.buttonColor);
			g.fillRect(buPos.x, buPos.y, bounds.width, bounds.height);
		}
		else
			g.drawImage(this.buttonImage, buPos.x, buPos.y, bounds.width, bounds.height, obs);
		
		g.setColor(this.textColor);
		DrawHelp.drawText(g, bounds.height, text, scTxtPos);
	}
	
	public void highlight(Color c){
		this.buttonColor = c;
	}
	
	public void unHighlight(){
		this.buttonColor = this.defbC;
	}
	
	public boolean isIntersectingMouse(int x, int y){
		return this.bounds.intersects(x, y, 1, 1);
	}
}
