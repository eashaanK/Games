package rpg_game_main;

import javax.swing.JFrame;

import rpg_game_input.Controls;

public class RPGFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RPGPanel panel;
	public Controls controls;
	
	public RPGFrame(){
		
		
		controls = new Controls();
		this.addKeyListener(controls);
		this.addMouseListener(controls);

		
		panel = new RPGPanel(controls);
		add(panel);		
	}
	
}
