package mineMapperWindow;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;


public class Draw {
	
	private static TrueTypeFont font;

	public static void drawRect(float x, float y, float w, float h, float rot)
	{
		glPushMatrix();{
			w/=2;
			h/=2;
			glTranslatef(x, y, 0);
			glRotatef(rot, 0, 0, 1);
			
			glBegin(GL_QUADS);{
				glVertex2f(-w, -h);
				glVertex2f(-w, h);
				glVertex2f(w, h);
				glVertex2f(w, -h);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public static void drawTri(float x, float y, float w, float h, float rot)
	{
		glPushMatrix();{
			w/=2;
			h/=2;
			glTranslatef(x, y, 0);
			glRotatef(rot, 0, 0, 1);
			
			glBegin(GL11.GL_TRIANGLES);{
				glVertex2f(-w, -h);
				glVertex2f(0, h);
				glVertex2f(w, -h);
				//glVertex2f(w, -h);
			}
			glEnd();
		}
		glPopMatrix();
	}
	
	public static void color(float r, float g, float b, float a){
		glColor4f(r, g, b, a);
	}
	
	public static void rotate(float angle){
		glRotatef(angle, 0, 0, 1);
	}
	
	public static void setFontFromTTF(String fileNameTTF){
		try {
		    InputStream inputStream = ResourceLoader.getResourceAsStream(fileNameTTF);

		    Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
		    awtFont = awtFont.deriveFont(24f); // set font size
		    font = new TrueTypeFont(awtFont, false);
		    return;

		} catch (Exception e) {
		    //e.printStackTrace();
			Font awtFont = new Font("Times New Roman", Font.BOLD, 24); //name, style (PLAIN, BOLD, or ITALIC), size
			font = new TrueTypeFont(awtFont, false); //base Font, anti-aliasing true/false

		}
	}
	
	public static void text(int x, int y, String message, int r, int g, int b){
		if(font == null){
			setFontFromTTF(null);
		}
		font.drawString(x, y, message, new Color(r * 255, g * 255, b * 255)); //x, y, string to draw, color
	}

}
