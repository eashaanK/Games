package cb2Drpg_main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cb2Drpg_images.ImageLibrary;
import cb2Drpg_input.Input;
import cb2Drpg_map.Map;
import cb2Drpg_refereces.Ref;

public class Screen extends JPanel implements Runnable{

	Thread thread;
	int fps = 0;
	int ups = 0;
	Input input;	
	Game game;
	
	public Screen(JFrame frame){
		input = new Input(frame);
		game = new Game(this, input);		
		//last thing to be called
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		boolean printFPS = false;
		
		int updatesPerSec = 50;
		int framesPerSecond = 100;
		
		long gameSkipTicks = 1000 / updatesPerSec;
		long frameSkipTicks = 1000 / framesPerSecond;
		long maxFrameSkip = 5;
		
		long nextGameTick = System.currentTimeMillis();
		long nextFrameTick = System.currentTimeMillis();

		long time = System.currentTimeMillis();
		
		int loops;
		
		int frames = 0;
		int updates = 0;
		
		while(true){
			loops = 0;
			while(System.currentTimeMillis() > nextGameTick && loops < maxFrameSkip){
				update();
				
				nextGameTick += gameSkipTicks;
				updates++;
				loops++;
			}
			
		
			if(System.currentTimeMillis() > nextFrameTick){
				nextFrameTick += frameSkipTicks;
				repaint();
				frames++;
				
			}
			
			
			if(time + 1000 <= System.currentTimeMillis()){
				time += 1000;
				fps = frames;
				ups = updates;
				updates = 0;
				frames = 0;
				
				if(printFPS)
					System.out.println("FPS: " + fps + " UPS: " + ups);
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		game.render(g);
	}
	
	private void update(){
		game.update();
	}

}
