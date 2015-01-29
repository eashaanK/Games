package cb2Drpg_images;

import java.awt.Image;

import javax.swing.ImageIcon;

public class TextureManager {

	/**
	 * DO NOt ENTER .png
	 * @param resource
	 * @return
	 */
	public static Image loadTexture(String resource){
		Image image = new ImageIcon("ChunkBased2DRPG/cb2Drpg_res/" + resource + ".png").getImage();
		return image;
	}
}
