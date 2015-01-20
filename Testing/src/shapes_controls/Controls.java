package shapes_controls;

import java.util.LinkedList;

public class Controls {

	private LinkedList<Integer> keys, mouse;
	
	public Controls(){
		keys = new LinkedList<Integer>();
		mouse = new LinkedList<Integer>();
	}
	
	public void addKey(int key){
		if(!keys.contains(key))
			keys.add(key);
	}
	
	public void removeKey(int key){
		keys.remove(new Integer(key));
	}
	
	public void addButton(int b){
		if(!mouse.contains(b))
			mouse.add(b);
	}
	
	public void removeButton(int b){
		mouse.remove(new Integer(b));
	}
	
	public boolean keyHeld(int key){
		return keys.contains(key);
	}
	
	public boolean buttonHeld(int b){
		return mouse.contains(b);
	}
}
