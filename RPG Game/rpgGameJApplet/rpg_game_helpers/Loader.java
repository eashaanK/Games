package rpg_game_helpers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Loader {

	public static Image loadImage(String path){
		return new ImageIcon(path).getImage();
	}
}
