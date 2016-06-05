package timer;

public class StopWatch {
	
	private long startTime;
	private double deltaTime;

	public StopWatch(){
		reset();
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public void stop(){
		deltaTime = System.currentTimeMillis() - startTime;
	}
	
	public void reset(){
		startTime = 0;
	}
	
	public double getDeltaTime(){
		return this.deltaTime;
	}
}
