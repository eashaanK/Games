package rpg_game_input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import rpg_game_components.Player;
import rpg_game_helpers.ImageDirection;
import rpg_game_main.RPGPanel;

public class Controls implements KeyListener, MouseListener {

	LinkedList<Integer> keyHeld = new LinkedList<Integer>();

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

	public void update(Player player) {
		if (keyHeld.contains(KeyEvent.VK_W)){
			player.moveBy(0, -2);
			player.updateImage(ImageDirection.up);
		}
		if (keyHeld.contains(KeyEvent.VK_S)){
			player.moveBy(0, 2);
			player.updateImage(ImageDirection.down);
		}
		if (keyHeld.contains(KeyEvent.VK_A)){
			player.moveBy(-2, 0);
			player.updateImage(ImageDirection.left);
		}
		if (keyHeld.contains(KeyEvent.VK_D)){
			player.moveBy(2, 0);
			player.updateImage(ImageDirection.right);
		}
		// System.out.println("Controls: " + keyHeld);

	}

	// /////////////////////////MOUSE/////////////////////////////

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
