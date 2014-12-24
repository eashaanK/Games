package rpg_game_main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import rpg_game_components.Floor;
import rpg_game_components.Player;
import rpg_game_input.Controls;

public class RPGPanel extends JPanel implements Runnable {

	public Thread thread;
	public static boolean running;
	public static Player player;
	public Floor floor;
	public Image background;
	
	public int currentFPS = 0;
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

			
		}
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// ///////////////////////////////////////////////////////////////
	public void init() {
		player = new Player("Test 1", 100, 200, 20, 20);
		floor = new Floor(RPGMain.WIDTH / 2 - 800 / 2,
				RPGMain.HEIGHT / 2 - 50 / 2, 800, 50);
	
		/*URL src = getClass().getResource("/Users/eashaan/eclipse-and-more/repositories/Games/RPG Game/rpgGame/rpg_game_images/Grass.png");
		try {
			this.background = ImageIO.read(src);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.background = new ImageIcon("rpg_game_images/Grass.png").getImage();
	}

	/**
	 * FIX THE UPDATE. IT SHOULDN'T BE CALLED THESE MANY TIMES
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.background, 0, 0, this);
		controls.update();
		// floor.render(g);
		// floor.update();
		player.update();
		player.render(g);
		
		g.setColor(Color.black);
		g.drawString(currentFPS + "", 10, 20);
		
	}
}
