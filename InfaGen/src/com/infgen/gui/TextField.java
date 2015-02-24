package com.infgen.gui;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ENABLE_BIT;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_RGB;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDeleteTextures;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glPopAttrib;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushAttrib;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import de.matthiasmann.twl.utils.PNGDecoder;

public class TextField {

	private static final StringBuilder renderString = new StringBuilder(
			"Enter your text");
	private Vector2f pos;
	private float width, height;

	public TextField(String text, float x, float y, float w, float h) {
		setText(text);
		this.pos = new Vector2f(x, y);
		this.width = w;
		this.height = h;

		System.err.println("MUST CLEAN UP TEXT FIELD");
		try {
			setUpTextures();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getText() {
		return renderString.toString();
	}

	public void setText(String text) {
		this.renderString.delete(0, renderString.length());
		renderString.append(text);
	}

	public void update() {

		render();
		input();
	}
	
	public Vector2f getPos(){
		return pos;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}

	// ////////////////////////////////////////////////////////////////////////////////////LWJGL
	// STUUFFF/////////////////////////////////
	private static int fontTexture;

	private static void setUpTextures() throws IOException {
		// Create a new texture for the bitmap font.
		fontTexture = glGenTextures();
		// Bind the texture object to the GL_TEXTURE_2D target, specifying that
		// it will be a 2D texture.
		glBindTexture(GL_TEXTURE_2D, fontTexture);
		// Use TWL's utility classes to load the png file.
		PNGDecoder decoder = new PNGDecoder(new FileInputStream(
				"src/textures/InfaGenFont.png"));
		ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth()
				* decoder.getHeight());
		decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
		buffer.flip();
		// Load the previously loaded texture data into the texture object.
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, decoder.getWidth(),
				decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
		// Unbind the texture.
		glBindTexture(GL_TEXTURE_2D, 0);
	}

	private static void render() {
		renderString(renderString.toString(), fontTexture, 16, -0.9f, 0, 0.3f,
				0.225f);
	}

	/**
	 * Renders text using a font bitmap.
	 * 
	 * @param string
	 *            the string to render
	 * @param textureObject
	 *            the texture object containing the font glyphs
	 * @param gridSize
	 *            the dimensions of the bitmap grid (e.g. 16 -> 16x16 grid; 8 ->
	 *            8x8 grid)
	 * @param x
	 *            the x-coordinate of the bottom-left corner of where the string
	 *            starts rendering
	 * @param y
	 *            the y-coordinate of the bottom-left corner of where the string
	 *            starts rendering
	 * @param characterWidth
	 *            the width of the character
	 * @param characterHeight
	 *            the height of the character
	 */
	private static void renderString(String string, int textureObject,
			int gridSize, float x, float y, float characterWidth,
			float characterHeight) {
		glPushAttrib(GL_TEXTURE_BIT | GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT);
		glEnable(GL_CULL_FACE);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, textureObject);
		// Enable linear texture filtering for smoothed results.
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		// Enable additive blending. This means that the colours will be added
		// to already existing colours in the
		// frame buffer. In practice, this makes the black parts of the texture
		// become invisible.
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		// Store the current model-view matrix.
		glPushMatrix();
		// Offset all subsequent (at least up until 'glPopMatrix') vertex
		// coordinates.
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		// Iterate over all the characters in the string.
		for (int i = 0; i < string.length(); i++) {
			// Get the ASCII-code of the character by type-casting to integer.
			int asciiCode = (int) string.charAt(i);
			// There are 16 cells in a texture, and a texture coordinate ranges
			// from 0.0 to 1.0.
			final float cellSize = 1.0f / gridSize;
			// The cell's x-coordinate is the greatest integer smaller than
			// remainder of the ASCII-code divided by the
			// amount of cells on the x-axis, times the cell size.
			float cellX = ((int) asciiCode % gridSize) * cellSize;
			// The cell's y-coordinate is the greatest integer smaller than the
			// ASCII-code divided by the amount of
			// cells on the y-axis.
			float cellY = ((int) asciiCode / gridSize) * cellSize;
			glTexCoord2f(cellX, cellY + cellSize);
			glVertex2f(i * characterWidth / 3, y);
			glTexCoord2f(cellX + cellSize, cellY + cellSize);
			glVertex2f(i * characterWidth / 3 + characterWidth / 2, y);
			glTexCoord2f(cellX + cellSize, cellY);
			glVertex2f(i * characterWidth / 3 + characterWidth / 2, y
					+ characterHeight);
			glTexCoord2f(cellX, cellY);
			glVertex2f(i * characterWidth / 3, y + characterHeight);
		}
		glEnd();
		glPopMatrix();
		glPopAttrib();
		
		//System.out.println("Works");
	}

	private static void input() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				// Reset the string if we press escape.
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					renderString.setLength(0);
				}
				// Append the pressed key to the string if the key isn't the
				// back key or the shift key.
				if (Keyboard.getEventKey() != Keyboard.KEY_BACK) {
					if (Keyboard.getEventKey() != Keyboard.KEY_LSHIFT) {
						renderString.append(Keyboard.getEventCharacter());
						// renderString.append((char)
						// Keyboard.getEventCharacter() - 1);
					}
					// If the key is the back key, shorten the string by one
					// character.
				} else if (renderString.length() > 0) {
					renderString.setLength(renderString.length() - 1);
				}
			}
		}
	}

	public void cleanUp() {
		glDeleteTextures(fontTexture);
	}
}
