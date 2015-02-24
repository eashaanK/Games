package com.infgen.helpers;

import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Draw {

	
	public static void drawRect(float x, float y, float w, float h){
		GL11.glBegin(GL11.GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + w, y);
			glVertex2f(x + w, y + h);
			glVertex2f(x, y + h);
		glEnd();
	}
	
	/**
	 * @param format
	 * @param file
	 * @return
	 */
	public static Texture loadTexture(String format, String file){
		try {
			return TextureLoader.getTexture(format, new FileInputStream(new File("src/textures/" + file)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void drawTexture(Texture t, float x, float y, float w, float h){
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(x, y);
			glTexCoord2f(1, 0);
			glVertex2f(x + w, y);
			glTexCoord2f(1, 1);
			glVertex2f(x + w, y + h);
			glTexCoord2f(0, 1);
			glVertex2f(x, y + h);
		glEnd();
	}
}
