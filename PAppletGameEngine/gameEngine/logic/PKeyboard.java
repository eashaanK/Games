package logic;

import java.util.ArrayList;

import processing.event.KeyEvent;

public class PKeyboard {

	private static ArrayList<Integer> keyHeld = new ArrayList<Integer>();
	
	public static void addKey(KeyEvent e){
		int keyCode = e.getKeyCode();
		if(!keyHeld.contains(keyCode))
			keyHeld.add(keyCode);
	}
	
	public static void removeKey(KeyEvent e){
		int keyCode = e.getKeyCode();
		if(keyHeld.indexOf(keyCode) >= 0)
			keyHeld.remove(new Integer(keyCode));
	}
	
	public static boolean keyHeld(int key){
		return keyHeld.contains(key);
	}

}
