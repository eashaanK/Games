package physics;

public class Size2D {
	public float width, height;
	public Size2D(float w, float h){
		this.width = w;
		this.height = h;
	}
	
	public String toString(){
		return "(" + width + ", " + height + ")";
	}
}
