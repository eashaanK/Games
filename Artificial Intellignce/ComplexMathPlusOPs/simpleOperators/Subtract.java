package simpleOperators;

import java.math.BigInteger;
import java.util.ArrayList;

public class Subtract implements Runnable{


	private ArrayList<BigInteger> numbers;
	private BigInteger difference;
	private boolean isDone = false;

	
	public Subtract(ArrayList<BigInteger> num){
		this.numbers = num;
		difference = numbers.get(0);
		isDone = false;
	}
	
	public void run(){
		for(int i = 1; i < numbers.size(); i++){
			difference = difference.subtract(numbers.get(i));
		}
		isDone = true;
	}
	
	public BigInteger getDifference(){
		return difference;
	}
	
	public boolean isDone(){
		return this.isDone;
	}
	
	public void exit(){
		numbers = null;
		difference = null;
		Thread.currentThread().interrupt();
	}
	
}
