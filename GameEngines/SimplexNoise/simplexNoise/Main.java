package simplexNoise;

import java.awt.Color;
import java.awt.image.BufferedImage;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	/**
	 * Persistence is used to affect the appearance of the terrain, 
	 * high persistance (towards 1) gives rocky mountainous terrain. 
	 * low persistance (towards 0) gives slowly varying flat terrain. 
	 */
	PImage image;
	int largestFeature;
	double persistence;
	int seed;

	public void setup() {
		largestFeature = (int)(Math.random() * 100);
		persistence = 0.292383434;
		seed = (int)(Math.random() * Integer.MAX_VALUE);
		SimplexNoise simplexNoise = new SimplexNoise(largestFeature, persistence, seed);
		System.out.println("largestFeature: " + largestFeature + "\tpersistence: " + persistence + "\tseed: " + seed);
		
		double xStart = 0;
		double XEnd = 500;
		double yStart = 0;
		double yEnd = 500;

		int xResolution = 512;
		int yResolution = 512;

		double[][] result = new double[xResolution][yResolution];

		for (int i = 0; i < xResolution; i++) {
			for (int j = 0; j < xResolution; j++) {
				int x = (int) (xStart + i * ((XEnd - xStart) / xResolution));
				int y = (int) (yStart + j * ((yEnd - yStart) / yResolution));
				result[i][j] = 0.5 * (1 + simplexNoise.getNoise(x, y));
			}
		}

		size(1000, 1000);
		background(255, 255, 255);
		createImage(result);
	}

	public void createImage(double[][] data) {
		image = loadImage("blank.png");
		BufferedImage bimage = new BufferedImage(data.length, data[0].length,
				BufferedImage.TYPE_INT_RGB);

		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {

				if (data[x][y] > 1) {
					data[x][y] = 1;
				}
				if (data[x][y] < 0) {
					data[x][y] = 0;
				}
				Color col = new Color((float) data[x][y], (float) data[x][y],
						(float) data[x][y]);
				bimage.setRGB(x, y, col.getRGB());
				image.set(x, y, col.getRGB());
				image.updatePixels();
			}
			//System.out.println();
		}
		/*
		 * for (int r = 0; r < data.length; r++) { for (int c = 0; c <
		 * data[r].length; c++) { System.out.println(bimage.(r, c)); i++; } }
		 */
	}

	public void mousePressed() {
		largestFeature = (int)(Math.random() * 100);
		persistence = Math.random();
		seed = (int)(Math.random() * Integer.MAX_VALUE);
		SimplexNoise simplexNoise = new SimplexNoise(largestFeature, persistence, seed);
		System.out.println("largestFeature: " + largestFeature + "\tpersistence: " + persistence + "\tseed: " + seed);
		double xStart = 0;
		double XEnd = 500;
		double yStart = 0;
		double yEnd = 500;

		int xResolution = 512;
		int yResolution = 512;

		double[][] result = new double[xResolution][yResolution];

		for (int i = 0; i < xResolution; i++) {
			for (int j = 0; j < xResolution; j++) {
				int x = (int) (xStart + i * ((XEnd - xStart) / xResolution));
				int y = (int) (yStart + j * ((yEnd - yStart) / yResolution));
				result[i][j] = 0.5 * (1 + simplexNoise.getNoise(x, y));
			}
		}
		createImage(result);

	}

	public void draw() {
		image(image, 0, 0);
	}
}
