package rpg_game_main;

import java.awt.Graphics;

import javax.swing.JPanel;

import rpg_game_input.Controls;

public class RPGPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Thread thread;
	public static boolean running;
	public Game game;
	
	public static int currentFPS = 0;
	public double deltaTime = 0;
	public final int TARGET_FPS = 60;

	public static  Controls controls;

	public RPGPanel(Controls c) {
		this.controls = c;
		running = false;
		start();
		init();
	}

	public void run() {
		long lastLoopTime = System.nanoTime();
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		long lastFpsTime = 0;
		currentFPS = 0;
		int fps = 0;

		while (running) {

			long now = System.nanoTime();
			long updateLength = now - lastLoopTime;
			lastLoopTime = now;
			double delta = updateLength / ((double) OPTIMAL_TIME);

			// update the frame counter
			lastFpsTime += updateLength;
			fps++;

			// update our FPS counter if a second has passed since
			// we last recorded
			if (lastFpsTime >= 1000000000) {
				//System.out.println("(FPS: " + fps + ")");
				lastFpsTime = 0;
				currentFPS = fps;
				fps = 0;
			}
			repaint();

		     try{
		    	 Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
		    	 }
		     catch(Exception e){
		    	 e.printStackTrace();
		     }
		     this.deltaTime = delta;
		}
		
		System.err.println("Loop in RPGPanel ended");
		if(game != null)
			game.attempDisconnect();
		System.err.println("Game Client Disconnected");

	}

	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// ///////////////////////////////////////////////////////////////
	public void init() {
		game = new Game();
		game.init();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.mouseX = 	RPGPanel.controls.mouseX;
		game.mouseY = 	RPGPanel.controls.mouseY;
		g.translate(-game.player.getX() + RPGMain.WIDTH / 2, -game.player.getY() + RPGMain.HEIGHT / 2);
		game.update(g, this);
		game.gui(g, this);
		controls.updatePlayer(game.player);
		controls.updateMouse(this.getLocationOnScreen());
	}
	
	
}
