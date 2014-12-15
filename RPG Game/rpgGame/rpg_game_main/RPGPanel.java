package rpg_game_main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class RPGPanel extends JPanel implements Runnable{

	public Thread thread;
	public static boolean running;
	
	public static Rectangle player;
	public static int playerWidth = 30, playerHeight = 30;	
	public RPGPanel(){
		running = false;
		start();
	}
	
	//this is run everytime in repaint()
	@Override
	public void paint(Graphics g){
		player = new Rectangle();
		g.fillRect(RPGMain.WIDTH /2, RPGMain.HEIGHT / 2, playerWidth, playerHeight);
	}
	
	public void run(){
		while(running){
			repaint();
		}
	}
	
	public void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
}
