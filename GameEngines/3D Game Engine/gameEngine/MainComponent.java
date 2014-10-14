package gameEngine;


/**
https://www.youtube.com/watch?v=4_54jIrhyQM&list=PLEETnX-uPtBXP_B2yupUKlflXBznWIlL5&index=22

at time: 6:22
 * @author eashaan
 *
 */
public class MainComponent {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final String NAME = "3D Game Engine v.AlPHA";
	public static final double FRAME_CAP = 5000.0;

	private boolean isRunning;
	private Game game;

	public MainComponent(){
		System.out.println(RenderUtil.getOpenGLVersion());
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
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while(isRunning){
			
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND; //how much time still need to be accounted for
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime){
				render = true;
				
				unprocessedTime -= frameTime;
				
				if(Window.isCloseReqeusted())
					stop();
				
				Time.setDelta(frameTime);
				
				Input.update();
				
				game.input();
				Input.update();
				game.update();
				
				if(frameCounter >= Time.SECOND)
				{
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			if(render)
			{
				render();
				frames++;
			}
			else //so that the loop doesn't keep running while render = false
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
		Window.render();
		RenderUtil.clearScreen();

		game.render();
	}
	
	private void cleanUp(){
		Window.dispose();
	}
	
	public static void main(String[] args){
		Window.createWindow(WIDTH, HEIGHT, NAME);
		MainComponent game = new MainComponent();
		game.start();
	}
	
}
