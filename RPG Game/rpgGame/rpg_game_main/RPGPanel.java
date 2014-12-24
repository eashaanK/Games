package rpg_game_main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import rpg_game_components.Floor;
import rpg_game_components.Player;
import rpg_game_input.Controls;

public class RPGPanel extends JPanel implements Runnable {

	public Thread thread;
	public static boolean running;
	public static Player player;
	public Floor floor;
	
	public int currentFPS = 0;
	public double deltaTime = 0;
	public final int TARGET_FPS = 60;

	private Controls controls;

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
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// ///////////////////////////////////////////////////////////////
	public void init() {
		player = new Player("Test 1", RPGMain.WIDTH / 2, RPGMain.HEIGHT/2, 40, 40);
		floor = new Floor(RPGMain.WIDTH, RPGMain.HEIGHT);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//moveable screen
		g.translate(-player.getX() + RPGMain.WIDTH / 2, -player.getY() + RPGMain.HEIGHT / 2);
		floor.render(g, this);
		floor.update();
		//
		controls.update();
		player.update();
		player.render(g, this);
			
		this.drawFixedText(g, currentFPS + "", Color.black, 10, 20);
		
	}
	
	private void drawFixedText(Graphics g, String text, Color c, int x, int y){
		g.setColor(c);
		g.drawString(text, player.getX() - (RPGMain.WIDTH / 2 - x ), player.getY() - (RPGMain.HEIGHT / 2 - y ));
	}
}
