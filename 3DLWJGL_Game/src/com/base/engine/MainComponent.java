package com.base.engine;

import com.base.game.Game;
import com.base.input.Input;
import com.base.ref.Ref;

public class MainComponent {

	private boolean isRunning;
	private Game game;
	public MainComponent(){
		isRunning = false;
		game = new Game();
	}
	
	public void start(){
		if(isRunning)
			return;
		run();
	}
	
	public void stop(){
		if(!isRunning)
			return;
		isRunning = false;
	}
	
	private void run(){
		isRunning = true;
		int frames = 0;
		long frameCounter = 0;
		final double frameTime = 1.0 / Ref.FRAME_CAP;
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		while(isRunning){
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Ref.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime){
				render = true;
				unprocessedTime -= frameTime;
				if(Window.isCloseRequested())
					stop();
				Time.setDelta(frameTime);
				Input.update();
				game.input();
				game.update();
				if(frameCounter >= Ref.SECOND){
					System.out.println("FPS: " + frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render){
				render();
				frames++;
			}
			else
			{
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
		cleanUp();
	}
	
	private void render(){
		game.render();
		Window.render();
	}
	
	private void cleanUp(){
		Window.dispose();
	}
	
	public static void main(String[] args) {
		Window.createWindow(Ref.WIDTH, Ref.HEIGHT, Ref.TITLE);
		MainComponent main = new MainComponent();
		main.start();
	}

}
