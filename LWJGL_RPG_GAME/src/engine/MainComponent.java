package engine;

import org.lwjgl.opengl.Display;

import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;

import component.Color4f;


public class MainComponent {

	public static final double FRAME_CAP = 1200;
	public static double framesPerSecond;
	
	private boolean isRunning;
	private Game game;
	private Renderer renderer;
	private Loader loader;
	private StaticShader staticShader = new StaticShader();

	public MainComponent()
	{
		renderer = new Renderer();
		loader = new Loader();
		renderer.initGraphics(new Color4f(0, 0, 0, 0));
		isRunning = false;
		game = new Game(loader);
	}
	
	public void start()
	{
		if(isRunning)
			return;
		run();
	}
	
	public void stop()
	{
		if(!isRunning)
			return;
		isRunning = false;
	}
	
	private void run()
	{
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0/FRAME_CAP;
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while(isRunning)
		{
			boolean render = false;
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime){
				render = true;
				unprocessedTime -= frameTime;
				if(Display.isCloseRequested())
					stop();
				Time.setDelta(frameTime);
				
				game.input();
				game.update();
				
				//TODO:UPDATE
				if(frameCounter >= Time.SECOND){
					framesPerSecond = frames;
					//System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render)
			{
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
	
	private void render()
	{
		renderer.clearScreen(true);
		staticShader.start();
		game.render(renderer);
		staticShader.stop();
		Window.render();
	}
	
	private void cleanUp()
	{
		game.cleanUp();
		staticShader.cleanUp();
		loader.cleanUp();
		Window.cleanUp();
	}
	
	public static void main(String[] args) 
	{
		Window.createWindow(800, 600, "3D GAME");
		
		MainComponent game = new MainComponent();
		game.start();
	}

}
