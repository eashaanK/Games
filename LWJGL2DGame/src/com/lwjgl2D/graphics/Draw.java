package com.lwjgl2D.graphics;
import static org.lwjgl.opengl.GL11.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.nishu.utils.Vector2f;

public class Draw {

	/**
	 * Draws a rectangle relative to the screen
	 * @param x x pos
	 * @param y y pos
	 * @param w width
	 * @param h height
	 */
	public static void rectRTS(float x, float y, float w, float h){
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + w, y);
			glVertex2f(x + w, y + h);
			glVertex2f(x, y + h);
		glEnd();
	}
	
	/**
	 * Draws a rectangle relative to the screen
	 * @param pos pos of rect
	 * @param w width
	 * @param h height
	 */
	public static void rectRTS(Vector2f pos, float w, float h){
		rectRTS(pos.getX(), pos.getY(), w, h);
	}
	
	/**
	 * Draws a line Relative to the Screen
	 * @param x1 x point
	 * @param y1 y point
	 * @param x2 second x point
	 * @param y2 second y point
	 */
	public static void lineRTS(float x1, float y1, float x2, float y2){
		glBegin(GL_LINES);
			glVertex2f(x1, y1);
			glVertex2f(x2, y2);
		glEnd();
	}
	
	/**
	 * Draws a line Relative to the Screen
	 * @param v1 first position
	 * @param v2 second position
	 */
	public static void lineRTS(Vector2f v1, Vector2f v2){
		lineRTS(v1.getX(), v1.getY(), v2.getX(), v2.getY());
	}
	
	public static Texture loadTexture(String textureName, String resFolder){
		try {
			return TextureLoader.getTexture("PNG", new FileInputStream(new File("src/"  + resFolder + "/" + textureName + ".png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Are u using .png extention? When loading textures, only write the name");
		} catch (IOException e) {
			System.out.println("Are u using .png extention? When loading textures, only write the name");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ALL TEXTURES MUST BE A POWER OF 2
	 * @param texture
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static void drawTexture(Texture texture, float x, float y, float w, float h){
		
		drawTexture(texture, x, y, w, h, 1, 1, 1, 1);
	}
	
	public static void drawTexture(Texture texture, float x, float y, float w, float h, float r, float g, float b, float a){
		glColor4f(r, g, b, a);

		texture.bind();
		
		glBegin(GL_QUADS);
		
		glTexCoord2f(0, 0); //mapping the tex coords
		glVertex2f(x, y); //upper left
		
		glTexCoord2f(1, 0); //upper right 
		glVertex2f(x + w, y);
		
		glTexCoord2f(1, 1); //bottom right
		glVertex2f(x + w, y + h);
		
		glTexCoord2f(0, 1); //bottom - left
		glVertex2f(x, y + h);
		glEnd();
	}
}
