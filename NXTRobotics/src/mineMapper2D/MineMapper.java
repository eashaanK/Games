package mineMapper2D;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * Basic Game Class
 * @author eashaan
 *
 */
public class MineMapper {

	private Robot robot;
	private Grid grid;
	
	public MineMapper(){
		robot= new Robot(Window.WIDTH/2, 100, 0, 1, 0, 0);
		grid = new Grid();
		Draw.setFontFromTTF("Roboto-Black.ttf");
	}
	
	public void update(float delta){		
		robot.update(delta);
		
		grid.draw(robot, delta);
		robot.render();
		
		
		if(Mouse.isButtonDown(0))
			robot.goForward(30);
		else
			robot.stop();
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			robot.turnLeft();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			robot.turnRight();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void cleanUp(){
		
	}
}
