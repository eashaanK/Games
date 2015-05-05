package mineMapperWindow;

import org.lwjgl.input.Mouse;

public class MineMapper {

	private Robot robot;
	
	public MineMapper(){
		robot= new Robot(Window.WIDTH/2, 100, 0, 1, 0, 0);
		Draw.setFontFromTTF("Roboto-Black.ttf");

	}
	
	public void update(float delta){
		robot.update(delta);
		robot.render();
		
		
		robot.goForward(10);
		
		if(Mouse.isButtonDown(0)){
			robot.turnLeft();
			
		}
		
	}
	
	public void cleanUp(){
		
	}
}
