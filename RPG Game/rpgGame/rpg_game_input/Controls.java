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
import rpg_game_main.GameState;
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
		int speed =3;
		if (keyHeld.contains(KeyEvent.VK_W)){
			player.moveUp(speed);
			//player.updateImage(ImageDirection.up);
		}
		if (keyHeld.contains(KeyEvent.VK_S)){
			player.moveDown(speed);
			//player.updateImage(ImageDirection.down);
		}
		if (keyHeld.contains(KeyEvent.VK_A)){
			player.moveLeft(speed);
			//player.updateImage(ImageDirection.left);
		}
		if (keyHeld.contains(KeyEvent.VK_D)){
			player.moveRight(speed);
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
		this.highlightButton(Game.singlePlayerButton);

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
		if(Game.singlePlayerButton.isIntersectingMouse(mouseX, mouseY)){
			//Game.button.highlight(Color.yellow);
			Game.gm = GameState.Game;
		}
	}
	
	private void highlightButton(Button button){
		if(button.isIntersectingMouse(mouseX, mouseY))
			button.highlight(Color.yellow);
		else
			button.unHighlight();
		
	}

	public void mouseReleased(MouseEvent e) {
	
	}

	public void mouseEntered(MouseEvent e) {
		canUpdateMousePos = true;
	}

	public void mouseExited(MouseEvent e) {
		canUpdateMousePos = false;
	}
	

}
