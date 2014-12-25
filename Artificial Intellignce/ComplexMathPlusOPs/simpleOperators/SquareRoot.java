package simpleOperators;

import java.math.BigInteger;
import java.util.ArrayList;

public class SquareRoot implements Runnable{
	
	private BigInteger number;
	private BigInteger sqrtNum;
	
	public SquareRoot(BigInteger num){
		this.number = num;
		sqrtNum = BigInteger.ZERO;
	}
	
	public void run(){
		sqrtNum = this.sqrt(number);
	}
	
	public BigInteger getSQRTNum(){
		return sqrtNum;
	}
	
	public void exit(){
		number = null;
		sqrtNum = null;
		Thread.currentThread().interrupt();
	}
	
	public BigInteger sqrt(BigInteger n) {
		BigInteger a = BigInteger.ONE;
		BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8"))
				.toString());
		while (b.compareTo(a) >= 0) {
			BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
			if (mid.multiply(mid).compareTo(n) > 0)
				b = mid.subtract(BigInteger.ONE);
			else
				a = mid.add(BigInteger.ONE);
		}
		return a.subtract(BigInteger.ONE);
	}
}
