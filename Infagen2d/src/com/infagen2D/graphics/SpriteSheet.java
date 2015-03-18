package com.infagen2D.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public String path;
	public int width, height;
	public int[] pixels;
	
	public SpriteSheet(String path){
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			System.exit(-1);
			e.printStackTrace();
		}
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		this.pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = (pixels[i] & 0xff) / 64;
		}
	}
	
}
