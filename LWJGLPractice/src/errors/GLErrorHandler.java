package errors;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import renderEngine.DisplayManager;

public class GLErrorHandler {

	public static void checkErrors(String part) {
		int errorValue = GL11.glGetError();

		if (errorValue != GL11.GL_NO_ERROR) {
			String errorString = GLU.gluErrorString(errorValue);
			System.err.println("ERROR - " + part + ": " + errorString);

			DisplayManager.closeDisplay();
		}
	}

}