package com.base.engine;

import com.base.input.Input;

public class MainComponent {

	public static final int WIDTH = 800, HEIGHT = 600;
	public static final String TITLE = "Testing 3D Game";
	public static final double FRAME_CAP = 5000.0;

	private boolean isRunning;
	private Game game;

	public MainComponent() {
		isRunning = false;
		game = new Game();
	}

	public void start() {

		if (isRunning)
			return;
		run();
	}

	public void stop() {
		if (!isRunning)
			return;
		isRunning = false;
	}

	private void run() {
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0L;

		final double frameTime = 1.0 / FRAME_CAP;

		long lastTime = Time.getTime();
		double unprocessedTime = 0;

		while (isRunning) {
			boolean render = false;
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;

			unprocessedTime += passedTime / (double) Time.SECOND;
			frameCounter += passedTime;

			while (unprocessedTime > frameTime) {
				render = true;
				unprocessedTime -= frameTime;

				if (Window.isCloseRequested())
					stop();
				// TODO: update game
				
				Time.setDelta((float) frameTime);
				
				Input.update();
				
				game.input();
				game.update((float)frameTime);
				
				if(frameCounter >= Time.SECOND){
					System.out.println("Frames Per Second: " + frames);
					frames = 0;
					frameCounter = 0;
				}
			}

			if (render){
				render();
				frames++;
			}
			else
			{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		cleanUp();
	}

	private void render() {
		game.render();
		Window.render();
	}

	private void cleanUp() {
		Window.dispose();
	}

	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		MainComponent game = new MainComponent();
		game.start();
	}

}
