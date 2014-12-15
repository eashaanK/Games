package rpg_game_main;

import javax.swing.JFrame;

public class RPGMain {

	public static final int WIDTH = 800, HEIGHT = 600;
	public static final String TITLE = "RPG GAME";
	
	public RPGMain(){
		RPGFrame frame = new RPGFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String[] args){
		new RPGMain();
	}
}
