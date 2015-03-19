package firstTest;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;


public class TestNXT {

	public static void main(String[] args) {
		System.out.println("LEGOS WORKS!!!!!");
		Button.waitForAnyPress();
		Motor.A.setSpeed(100);

	}

}
