package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Un2Dg_graphics.SpriteSheet;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 160, HEIGHT = WIDTH/12*9;
	public static final int SCALE = 6;
	public static final String NAME = "UNNAMED GAME";
	
	public boolean running = false;
	public int tickCount = 0;
	
	private JFrame frame;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private SpriteSheet spriteSheet= new SpriteSheet("src/UnNamed2DGame_res/SpriteSheet.png");
	
	public Game(){
		this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	
	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}
	
	public synchronized void stop(){
		running = false;
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000d / 60d; //how many nanoseconds in one tick
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while(delta >= 1){
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(shouldRender){
				frames++;
				render();
				
			}
			
			
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				this.frame.setTitle(getName() + " Frames: " + frames + " ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick(){
		tickCount++;
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = i + tickCount;
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy(); //allows u sto organize the data
		if(bs == null)
		{
			this.createBufferStrategy(3); //triple bufferering bigger the number, more processing power but better image
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		

		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args){
		new Game().start();
	}

	
}
