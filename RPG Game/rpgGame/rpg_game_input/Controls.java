package rpg_game_input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import rpg_game_main.RPGPanel;

public class Controls implements KeyListener, MouseListener {

	LinkedList<Integer> keyHeld = new LinkedList<Integer>();

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		keyHeld.add(key);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keyHeld.remove(new Integer(key));
	}

	//////////////////////////////////////////////////////////////////

	public void update() {
		if (keyHeld.contains(KeyEvent.VK_W))
			RPGPanel.player.moveBy(0, -1);
		if (keyHeld.contains(KeyEvent.VK_S))
			RPGPanel.player.moveBy(0, 1);
		if (keyHeld.contains(KeyEvent.VK_A))
			RPGPanel.player.moveBy(-1, 0);
		if (keyHeld.contains(KeyEvent.VK_D))
			RPGPanel.player.moveBy(1, 0);
		// System.out.println("Controls: " + keyHeld);

	}

	// /////////////////////////MOUSE/////////////////////////////

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
