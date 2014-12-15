package rpg_game_main;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

import rpg_game_components.Player;

public class RPGPanel extends JPanel implements Runnable
{

	public Thread thread;
	public static boolean running;
	public Player player;

	public RPGPanel() {
		running = false;
		start();
		init();
	}

	public void run() {
		while (running) {
			repaint();	
		}
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/////////////////////////////////////////////////////////////////
	public void init(){
		player = new Player("Test 1", 100, 100, 50, 50);
	}

	/**
	 * FIX THE UPDATE. IT SHOULDN'T BE CALLED THESE MANY TIMES
	 */
	@Override
	public void paint(Graphics g) {
		player.render(g);
		player.update();
	}

}
