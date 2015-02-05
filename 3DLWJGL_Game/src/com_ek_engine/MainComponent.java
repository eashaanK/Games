package com_ek_engine;

import org.lwjgl.opengl.Display;

import com.nishu.utils.Time;

import com_ek_game.Game;
import com_ek_input.Input;
import com_ek_ref.Ref;
import com_ek_rendering.RenderUtil;

/**
 * This class has the main loop, controls the update and rendering and time
 * @author eashaan
 *
 */
public class MainComponent {
	
	private boolean isRunning;
	
	private GameInterface game;
	
	public MainComponent(){
		RenderUtil.initGraphics();
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
		
		double unProcessedTime = 0;
		
		while(isRunning)
		{
			boolean render = false;
			
			long startTime = Time.getTime();
			
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unProcessedTime += passedTime / (double)Ref.SECOND;
			frameCounter += passedTime;
			
			while(unProcessedTime > frameTime){
				render = true;
				
				unProcessedTime -= frameTime;
				
				if(Window.isCloseRequested())
					stop();
				
				Time.setDelta(frameTime);
				
				//TODO: update game
				game.input();
				Input.Update();

				game.update();
				
				if(frameCounter >= Ref.SECOND)
				{
					Ref.FPS = frames;
					Display.setTitle(Ref.TITLE + " FPS: " + Ref.FPS);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render){
				render();
				frames++;
			}
			else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		cleanUp();
	}
	
	private void render(){
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}
	
	private void cleanUp(){
		Window.dispose();
	}

	public static void main(String[] args){
		Window.createWindow(Ref.WIDTH, Ref.HEIGHT, Ref.TITLE);
		
		MainComponent game = new MainComponent();
		game.start();
	}
}
