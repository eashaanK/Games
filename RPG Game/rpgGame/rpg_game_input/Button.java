package rpg_game_input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import rpg_game_helpers.DrawHelp;

public class Button {
	
	private Rectangle bounds;
	private String text;
	private Color buttonColor, textColor;
	private Image buttonImage;
	public boolean shadow = true;

	public Button(int x, int y, int w, int h, String text, Color button, Color textC){
		this.bounds = new Rectangle(x, y, w, h);
		this.text = text;
		this.buttonColor = button;
		this.buttonImage = null;
		this.textColor = textC;
	}
	
	public Button(int x, int y, int w, int h, String text, Image buttonImage, Color textC){
		this.bounds = new Rectangle(x, y, w, h);
		this.text = text;
		this.buttonColor = null;
		this.buttonImage = buttonImage;
		this.textColor = textC;
	}
	
	public void render(Graphics g, ImageObserver obs){
		//draw shadow
		if(shadow){
			g.fillRect(bounds.x + 10, bounds.y + 10, bounds.width, bounds.height);
		}
		
		if(this.buttonImage == null)
			g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		else
			g.drawImage(this.buttonImage, bounds.x, bounds.y, bounds.width, bounds.height, obs);
		
		DrawHelp.drawText(g, bounds.width / 2, text, bounds.x, bounds.y);
		//draw button
	}
}
