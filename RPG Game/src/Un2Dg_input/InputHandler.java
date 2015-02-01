package Un2Dg_input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;

public class InputHandler implements KeyListener {

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	// public List<Key> keys = new ArrayList<Key>();

	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.toggleKey(e.getKeyCode(), true);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.toggleKey(e.getKeyCode(), false);

	}

	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_W) {
			up.toogle(isPressed);
		}
		if (keyCode == KeyEvent.VK_S) {
			down.toogle(isPressed);
		}
		;
		if (keyCode == KeyEvent.VK_A) {
			left.toogle(isPressed);
		}
		;
		if (keyCode == KeyEvent.VK_D) {
			right.toogle(isPressed);
		}
		;

	}

}
