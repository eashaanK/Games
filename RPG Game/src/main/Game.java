package main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import Un2Dg_graphics.MyColor;
import Un2Dg_graphics.MyFont;
import Un2Dg_graphics.Screen;
import Un2Dg_graphics.SpriteSheet;
import Un2Dg_input.InputHandler;

/**
 * White is transparent
 * 
 * @author eashaan
 * 
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 160, HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 6;
	public static final String NAME = "UNNAMED GAME";

	public boolean running = false;
	public int tickCount = 0;

	private JFrame frame;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer())
			.getData();
	private int[] colors = new int[6 * 6 * 6]; // only 6 shades of each colr
												// allowed

	private Screen screen;

	private InputHandler input;

	public Game() {
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

	public void init() {
		int index = 0;
		for (int r = 0; r < 6; r++) {
			for (int g = 0; g < 6; g++) {
				for (int b = 0; b < 6; b++) {
					int rr = (r * 255 / 5);
					int gg = (g * 255 / 5);
					int bb = (b * 255 / 5);

					colors[index++] = rr << 16 | gg << 8 | bb; // 2 ^ 8 bits for
																// each
				}
			}
		}
		screen = new Screen(WIDTH, HEIGHT, new SpriteSheet(
				"src/UnNamed2DGame_res/SpriteSheet.png"));
		input = new InputHandler(this);
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000d / 60d; // how many nanoseconds in one
												// tick
		int frames = 0;
		int ticks = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (delta >= 1) {
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

			if (shouldRender) {
				frames++;
				render();

			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				this.frame.setTitle(getName() + " Frames: " + frames
						+ " ticks: " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void tick() {
		tickCount++;
		if (input.up.isPressed()) {
			screen.yOffset--;
		}
		if (input.down.isPressed()) {
			screen.yOffset++;
		}
		if (input.left.isPressed()) {
			screen.xOffset--;
		}
		if (input.right.isPressed()) {
			screen.xOffset++;
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy(); // allows u sto organize
														// the data
		if (bs == null) {
			this.createBufferStrategy(3); // triple bufferering bigger the
											// number, more processing power but
											// better image
			return;
		}

		for (int y = 0; y < 32; y++) {
			for (int x = 0; x < 32; x++) {
				screen.render(x << 3, y << 3, 0,MyColor.get(555, 505, 055, 550), true, false); // << mulitplaies by 8
			}
		}

		MyFont.renderFont("Hello", screen, 0, 0, MyColor.get(-1, -1, -1, 000));
		
		renderBoard();

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.drawString("LOL", 10, 10);
		g.dispose();
		bs.show();
	}
	
	private void renderBoard(){
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colourCode = screen.pixels[x + y * screen.width];
				if (colourCode < 255)
					pixels[x + y * WIDTH] = colors[colourCode];
			}
		}
	}

	public static void main(String[] args) {
		new Game().start();
	}

}
