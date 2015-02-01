package Un2Dg_input;

public class Key {

	private boolean isPressed = false;
	private int numTimesPressed = 0;
	public void toogle(boolean isPressed){
		this.isPressed = isPressed;
		if(isPressed){
			numTimesPressed++;
		}
	}
	
	public boolean isPressed(){
		return isPressed;
	}
	public int getNumTimesPressed(){
		return numTimesPressed;
	}
}
