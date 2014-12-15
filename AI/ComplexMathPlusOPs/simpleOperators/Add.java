package simpleOperators;

import java.math.BigInteger;
import java.util.ArrayList;

public class Add implements Runnable{
	
	private ArrayList<BigInteger> numbers;
	private BigInteger sum;
	private boolean isDone = false;
	
	public Add(ArrayList<BigInteger> num){
		this.numbers = num;
		sum = BigInteger.ZERO;
		isDone = false;
	}
	
	public void run(){
		for(int i = 0; i < numbers.size(); i++){
			sum = sum.add(numbers.get(i));
		}
		isDone = true;
	}
	
	public boolean isDone(){
		return this.isDone;
	}
	
	public BigInteger getSum(){
		return sum;
	}
	
	public void exit(){
		numbers = null;
		sum = null;
		Thread.currentThread().interrupt();
	}

}
