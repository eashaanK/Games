package com.AI;

import java.math.BigInteger;
import java.util.ArrayList;

public class Divide implements Runnable{

	private ArrayList<BigInteger> numbers;
	private ArrayList<BigInteger> remainders;
	private BigInteger quotient;
	private boolean isDone = false;

	
	public Divide(ArrayList<BigInteger> num){
		this.numbers = num;
		quotient = numbers.get(0);
		remainders = new ArrayList<BigInteger>();
		isDone = false;
	}
	
	public void run(){
		for(int i = 1; i < numbers.size(); i++){
			BigInteger[] x = quotient.divideAndRemainder(numbers.get(i));
			quotient = x[0];
			remainders.add(x[1]);
		}
		isDone = true;
	}
	
	public boolean isDone(){
		return this.isDone;
	}
	
	public ArrayList<BigInteger> getRemainders(){
		return this.remainders;
	}

	
	public BigInteger getQuotient(){
		return quotient;
	}
	
	public void exit(){
		numbers = null;
		quotient = null;
		Thread.currentThread().interrupt();
	}
}
