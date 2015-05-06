package mineMapperWindow;

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
		
		
		robot.goForward(30);
		
		if(Mouse.isButtonDown(0)){
			robot.turnLeft();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void cleanUp(){
		
	}
}
