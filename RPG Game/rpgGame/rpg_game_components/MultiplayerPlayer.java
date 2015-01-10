package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Queue;

import rpg_game_helpers.DrawHelp;
import rpg_game_helpers.Loader;

public class MultiplayerPlayer extends Component{

	private final String name;
	private Image currentImage;
	public MultiplayerPlayer(int x, int y, int w, int h, String name) {
		super(x, y, w, h);
		this.name = name;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void setImage(Image image){
		this.currentImage = image;
	}

	@Override
	public void render(Graphics g, ImageObserver obs) {
		// TODO Auto-generated method stub
		/*g.drawImage(currentImage, this.getX(), this.getY(),
				this.getWidth(), this.getHeight(), obs);
		g.setColor(Color.black);
		DrawHelp.drawText(g, 15, this.toString(),
				this.getX() - name.length() * 3 / 2, this.getY() - 10);
		DrawHelp.drawText(g, 15, ".",
				this.getX(), this.getY());*/
		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
		
	}
	
	public String getName(){
		return name;
	}

	@Override
	public String toString() {
		return name + " (" + getX() + ", " + getY() + ")";
	}
	
	@Override
	public boolean equals(Object o){
		MultiplayerPlayer temp = (MultiplayerPlayer)o;
		return this.name.equals(temp.getName());
	}

}
