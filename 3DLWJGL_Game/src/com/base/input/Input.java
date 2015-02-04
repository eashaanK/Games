package com.base.input;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import com.base.ref.Ref;

public class Input {

	private static ArrayList<Integer> currentKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> downKeys = new ArrayList<Integer>();
	private static ArrayList<Integer> upKeys = new ArrayList<Integer>();

	
	public static void update(){
		upKeys.clear();

		for(int i = 0; i < Ref.NUM_KEYCODES; i++){
			if(!getKey(i) && currentKeys.contains(i))
				upKeys.add(i);
		}

		downKeys.clear();

		for(int i = 0; i < Ref.NUM_KEYCODES; i++){
			if(getKey(i) && !currentKeys.contains(i))
				downKeys.add(i);
		}
		
		currentKeys.clear();
		
		for(int i = 0; i < Ref.NUM_KEYCODES; i++){
			if(getKey(i))
				currentKeys.add(i);
		}
	}
	
	public static boolean getKey(int keyCode){
		return Keyboard.isKeyDown(keyCode);
	}
	
	/**
	 * This is only true for one frame
	 * @param keyCode
	 * @return
	 */
	public static boolean getKeyDown(int keyCode){
		return downKeys.contains(keyCode);
	}
	
	/**
	 * This is only true for one frame
	 * @param keyCode
	 * @return
	 */
	public static boolean getKeyUp(int keyCode){
		return upKeys.contains(keyCode);
	}
}
