package com.AI;

import java.math.BigInteger;
import java.util.ArrayList;

public class Multiply implements Runnable{

	private ArrayList<BigInteger> numbers;
	private BigInteger product;
	private boolean isDone = false;

	
	public Multiply(ArrayList<BigInteger> num){
		this.numbers = num;
		product = BigInteger.ONE;
		isDone = false;

	}
	
	public void run(){
		for(int i = 0; i < numbers.size(); i++){
			product = product.multiply(numbers.get(i));
		}
		isDone = true;
	}
	
	public boolean isDone(){
		return this.isDone;
	}
	
	public BigInteger getProduct(){
		return product;
	}
	
	public void exit(){
		numbers = null;
		product = null;
		Thread.currentThread().interrupt();
	}
}
