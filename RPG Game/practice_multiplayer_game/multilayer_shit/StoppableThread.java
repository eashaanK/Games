package multilayer_shit;

public class StoppableThread {

	private boolean isAcitve = true;
	
	
	public void stopThread(){
		this.isAcitve = false;
	}
	
	public boolean isActive(){
		return this.isAcitve;
	}
	
}
