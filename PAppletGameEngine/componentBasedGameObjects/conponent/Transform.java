package conponent;

public class Transform implements Component{

	public float x, y;
	
	@Override
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
}
