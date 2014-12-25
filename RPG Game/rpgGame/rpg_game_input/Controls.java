package rpg_game_input;

import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import rpg_game_components.Player;
import rpg_game_helpers.ImageDirection;
import rpg_game_main.Game;
import rpg_game_main.RPGMain;

public class Controls implements KeyListener, MouseListener {

	public LinkedList<Integer> keyHeld = new LinkedList<Integer>();
	public int mouseX, mouseY, mouseWindowX, mouseWindowY;
	private boolean canUpdateMousePos = false;

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		keyHeld.add(key);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keyHeld.remove(new Integer(key));
	}

	//////////////////////////////////////////////////////////////////

	public void updatePlayer(Player player) {
		if (keyHeld.contains(KeyEvent.VK_W)){
			player.moveBy(0, -2);
			//player.updateImage(ImageDirection.up);
		}
		if (keyHeld.contains(KeyEvent.VK_S)){
			player.moveBy(0, 2);
			//player.updateImage(ImageDirection.down);
		}
		if (keyHeld.contains(KeyEvent.VK_A)){
			player.moveBy(-2, 0);
			//player.updateImage(ImageDirection.left);
		}
		if (keyHeld.contains(KeyEvent.VK_D)){
			player.moveBy(2, 0);
			//player.updateImage(ImageDirection.right);
		}
		
		if (keyHeld.contains(KeyEvent.VK_W)){
			player.updateImage(ImageDirection.up);
		}
		else if (keyHeld.contains(KeyEvent.VK_S)){
			player.updateImage(ImageDirection.down);
		}
		else if (keyHeld.contains(KeyEvent.VK_A)){
			player.updateImage(ImageDirection.left);
		}
		else if (keyHeld.contains(KeyEvent.VK_D)){
			player.updateImage(ImageDirection.right);
		}
		
	}
	
	public void updateMouse(Point pW){
		if(canUpdateMousePos){
			Point p = MouseInfo.getPointerInfo().getLocation();
			this.mouseX = (int) p.getX() - pW.x;
			this.mouseY = (int) p.getY() - pW.y;
			
			if(mouseX < 0)
				mouseX = 0;
			if(mouseY < 0)
				mouseY = 0;
			
			if(mouseX > RPGMain.WIDTH)
				mouseX = RPGMain.WIDTH;
			if(mouseY > RPGMain.HEIGHT)
				mouseY = RPGMain.HEIGHT;
		}		
	}

	// /////////////////////////MOUSE/////////////////////////////

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		if(Game.button.isIntersectingMouse(mouseX, mouseY)){
			Game.button.highlight(Color.yellow);
		}
	}

	public void mouseReleased(MouseEvent e) {
			Game.button.unHighlight();
	
	}

	public void mouseEntered(MouseEvent e) {
		canUpdateMousePos = true;
	}

	public void mouseExited(MouseEvent e) {
		canUpdateMousePos = false;
	}
	

}
