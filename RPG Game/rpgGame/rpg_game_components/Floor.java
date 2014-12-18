package rpg_game_components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Floor extends Component
{
	private Rectangle bounds;
	
	public Floor(int x, int y, int w, int h){
		super(x, y, w, h);
		this.bounds = new Rectangle(x, y, w, h);
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.green);
		g.fillRect(this.bounds.x, this.bounds.y, bounds.width, this.bounds.height);
	}
	
	public String toString(){
		return "Floor: " + this.getBounds().toString();
	}
	
	
	
}
