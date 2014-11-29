package logic;

import java.util.ArrayList;

import processing.event.KeyEvent;
import processing.event.MouseEvent;

public class PMouse {
private static ArrayList<Integer> mouseHeld = new ArrayList<Integer>();
	
	public static void addButton(MouseEvent e){
		int keyCode = e.getButton();
		if(!mouseHeld.contains(keyCode))
			mouseHeld.add(keyCode);
	}
	
	public static void removeButton(MouseEvent e){
		int keyCode = e.getButton();
		if(mouseHeld.indexOf(keyCode) >= 0)
			mouseHeld.remove(new Integer(keyCode));
	}
	
	public static boolean buttonHeld(int button){
		return mouseHeld.contains(button);
	}
}
