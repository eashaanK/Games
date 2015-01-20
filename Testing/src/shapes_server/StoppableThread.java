package shapes_server;

public class StoppableThread {

	private boolean isAcitve = true;
	
	
	public void stop(){
		this.isAcitve = false;
	}
	
	public void disconnect(){
		if(this.isAcitve)
		{
			System.err.println("Stoppable Thread can't stop cause its still acitve. Stop it first");
			return;
		}
		
		Thread.currentThread().interrupt();
	}
	
	public boolean isActive(){
		return this.isAcitve;
	}
	
	public void fullStop(){
		this.stop();this.disconnect();
	}
}
