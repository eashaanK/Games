package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Floor extends Component
{
	private Image background;

	
	public Floor(int w, int h){
		super(0, 0, w, h);
		this.background = new ImageIcon("rpgGame/rpg_game_images/Grass.png").getImage();
		this.background.flush();
	}
	
	public void update(){
	}
	
	public void render(Graphics g, ImageObserver obs){
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), obs);
	}
	
	public String toString(){
		return "Floor: " + this.getBounds().toString();
	}
	
	
	
}
