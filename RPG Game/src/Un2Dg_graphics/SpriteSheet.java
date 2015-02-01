package Un2Dg_graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * To get pixel color: 255/3 * 0..1...2... to add more colors to ur player from 0 - 64, 64 - 128
 * @author eashaan
 *
 */
public class SpriteSheet {

	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public SpriteSheet(String path){
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image == null){
			return;
		}
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = (pixels[i] & 0xff)/64; //gets rid of alpha channel
		}
		
		for(int i = 0; i < 8; i++){
			System.out.println(pixels[i]);
		}
	}
	
}
