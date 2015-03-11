package infagen_Input;

import java.util.ArrayList;

public class Input {

	public static ArrayList<Integer> keysDown = new ArrayList<Integer>();
	public static ArrayList<Integer> mouseDown = new ArrayList<Integer>();

	
	public static void addKey(Integer key){
		if(!keysDown.contains(key))
			keysDown.add(key);
	}
	
	public static void addMouse(Integer mouse){
		if(!mouseDown.contains(mouse))
			mouseDown.add(mouse);
	}
	
	public static boolean isKeyDown(Integer key){
		return keysDown.contains(key);
	}
	
	public static boolean isMouseDown(Integer button){
		return mouseDown.contains(button);
	}
	
	public static boolean anyKeyDown(){
		return Input.keysDown.size() > 0;
	}
	
	public static boolean anyMouseButtonDown(){
		return Input.mouseDown.size() > 0;
	}
}
