package cb2Drpg_images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriter {

	public static void greyWriteImage(double[][] data){
		BufferedImage image = new BufferedImage(data.length, data[0].length, BufferedImage.TYPE_INT_RGB);
		for(int x = 0; x < data.length; x++){
			for(int y = 0; y < data.length; y++){
				Color color = new Color((float)data[x][y], (float)data[x][y], (float)data[x][y]);
				image.setRGB(x, y, color.getRGB());
			}
		}
		
		try{
			File file = new File("noise.png");
			file.createNewFile();
			
			ImageIO.write(image, "PNG", file);
		}
		catch(IOException e){
			throw new RuntimeException("<ImageWriter> Unable to load noise");
		}
	}
}
