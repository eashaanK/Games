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

import cb2Drpg_refereces.Ref;

public class Input {
	
	public Input(JFrame frame){
		frame.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if(!Ref.keysHeld.contains(e.getKeyCode()))
					Ref.keysHeld.add(e.getKeyCode());
			}

			public void keyReleased(KeyEvent e) {
				Ref.keysHeld.remove(new Integer(e.getKeyCode()));
			}
			
		});
		
		frame.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				
			}

			public void mousePressed(MouseEvent e) {
				if(!Ref.mouseButtonsHeld.contains(e.getButton()))
					Ref.mouseButtonsHeld.add(e.getButton());
			}

			public void mouseReleased(MouseEvent e) {
				Ref.mouseButtonsHeld.remove(new Integer(e.getButton()));
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
				Ref.mouseX = e.getX();
				Ref.mouseY = e.getY();
			}
			
		});
		
		frame.addMouseWheelListener(new MouseWheelListener(){

			public void mouseWheelMoved(MouseWheelEvent e) {
				
			}
			
		});
	}
}
