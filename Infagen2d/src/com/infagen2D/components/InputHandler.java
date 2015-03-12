package com.infagen2D.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.infagen2D.core.Game;

public class InputHandler implements KeyListener{
	//public List<Integer> mouse = new ArrayList<Integer>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	public InputHandler(Game game){
		game.addKeyListener(this);
	}


/*	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}*/

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.toggleKey(e.getKeyCode(), true);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.toggleKey(e.getKeyCode(), false);

	}
	
	public void toggleKey(int keyCode, boolean isPressed){
		if(keyCode == KeyEvent.VK_W){
			up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_A){
			left.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S){
			down.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D){
			right.toggle(isPressed);
		}
	}
	
	public class Key{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		public void toggle(boolean isPressed){
			pressed = isPressed;
			if(isPressed)numTimesPressed++;
		}
		
		public boolean isPressed(){
			return pressed;
		}
		
		public int getNumTimesPressed(){
			return numTimesPressed;
		}
	}

}
