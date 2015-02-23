package cb2Drpg_main;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static final String TITLE = "Chunk Based 2D RPG";
	public static int WIDTH;
	public static int HEIGHT;
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				System.out.println("Starting Main Thread");
				new Main();
			}
		});
	}
	
	public Main(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	//	WIDTH = toolkit.getScreenSize().width;
	//	HEIGHT = toolkit.getScreenSize().height;
		
		WIDTH = 600;
		HEIGHT = 600;

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(TITLE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(WIDTH, HEIGHT);
		//frame.setUndecorated(true); full screen
		
		//custom icon
		//custom cursor
		//screen
		frame.add(new Screen(frame));
		frame.setVisible(true);
	}
}
