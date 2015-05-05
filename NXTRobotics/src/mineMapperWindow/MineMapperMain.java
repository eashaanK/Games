package mineMapperWindow;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

/**
 * The Main Class that instantiates the window
 * @author eashaan
 *
 */
public class MineMapperMain {
	
	private static MineMapper mineMapper;
	
	public static void main(String[] args){
		initDisplay();
		initGL();
		
		mineMapper = new MineMapper();

		gameLoop();
		cleanUp();
	}
	
	private static void initDisplay(){
		Window.createWindow();
		
	}

	private static void initGL(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Window.WIDTH, 0, Window.HEIGHT, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glClearColor(0, 0, 0, 1);
		glDisable(GL_DEPTH_TEST);
	}
	
	private static void cleanUp(){
		mineMapper.cleanUp();
		Window.closeDisplay();

	}
	
	private static void gameLoop(){
		while(!Display.isCloseRequested()){
			Window.updateDelta();
			glClear(GL_COLOR_BUFFER_BIT);
			glLoadIdentity();
			mineMapper.update(Window.getDelta());
			Window.updateDisplay();
		}
	}
	

	private static void draw(){
	}
}
