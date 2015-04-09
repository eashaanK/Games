package component;

public class Color4f {

	private float r, g, b, a;
	
	public Color4f(float r, float g, float b, float a) {
		super();
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public float getR() {
		return r;
	}
	
	public void setRGBA(float r, float g, float b, float a){
		this.setA(a);
		this.setB(b);
		this.setG(g);
		this.setR(r);
	}

	public void setR(float r) {
		this.r = r;
		this.r %= 1;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
		this.g %= 1;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
		this.b %= 1;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
		this.a %= 1;
	}

	public String toString(){
		return "Color: (" + r + ", " + g + ", " + b + ", " + a + ")";
	}
}
