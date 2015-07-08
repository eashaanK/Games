package testlighitng;
import org.lwjgl.util.vector.Vector2f;

public class Light {
	public Vector2f location;
	public float red;
	public float green;
	public float blue;
	public float scale;

	public Light(Vector2f location, float red, float green, float blue, float scale) {
		this.scale = scale;
		this.location = location;
		this.red = red * scale;
		this.green = green * scale;
		this.blue = blue * scale;
	}
	

}
