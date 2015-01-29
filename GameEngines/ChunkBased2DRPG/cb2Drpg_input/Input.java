package cb2Drpg_input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Input {

	private List<Integer> keysHeld = new ArrayList<Integer>();
	private List<Integer> mouseButtonsHeld = new ArrayList<Integer>();
	
	public int mouseX, mouseY;
	
	public Input(JFrame frame){
		frame.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if(!keysHeld.contains(e.getKeyCode()))
					keysHeld.add(e.getKeyCode());
			}

			public void keyReleased(KeyEvent e) {
				keysHeld.remove(new Integer(e.getKeyCode()));
			}
			
		});
		
		frame.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				if(!mouseButtonsHeld.contains(e.getButton()))
					mouseButtonsHeld.add(e.getButton());
			}

			public void mouseReleased(MouseEvent e) {
				mouseButtonsHeld.remove(new Integer(e.getButton()));
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			public void mouseExited(MouseEvent e) {
				
			}
			
		});
		
		frame.addMouseMotionListener(new MouseMotionListener(){

			public void mouseDragged(MouseEvent e) {
				
			}

			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
			
		});
		
		frame.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent e) {
				
			}
			
		});
	}
	
	public boolean isKeyDown(char key){
		return (keysHeld.contains(new Integer(key)));
	}
	
	public boolean isMouseButtonDown(char button){
		return (keysHeld.contains(new Integer(button)));
	}
	
	public List<Integer> getKeysHeld() {
		return keysHeld;
	}

	public List<Integer> getMouseButtonsHeld() {
		return mouseButtonsHeld;
	}

}
