package Testing;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import simpleOperators.Add;
import simpleOperators.Divide;
import simpleOperators.Multiply;
import simpleOperators.Subtract;

public class Tester {

	private static 	Scanner user = new Scanner(System.in);

	public static void main(String[] args){
		int menu = 0;
		
		while(menu != 6){
			System.out.println("1. Add");
			System.out.println("2. Sub");
			System.out.println("3. Mult");
			System.out.println("4. Divide");
			System.out.println("5. Sqrt");
			System.out.println("6. Quit");
			
			System.out.print("> Selection Please: ");
			
			menu = user.nextInt();
			
			switch(menu){
			case 1:
				add();
				break;
			case 2:
				sub();   //FIX THIS
				break;
			case 3:
				mult();
				break;
			case 4:
				divide();
				break;
			case 5:
				System.out.println("You chose to sqrt...");
				
				break;
			case 6:
				System.out.println("Thank you for testing. Have a noce day.");
				break;
			}
		}
		

	}
	
	private static void divide(){
		System.out.println("You chose to divide...");
		System.out.print(">How many numbers would you like to divide? : ");
		int iterations = user.nextInt();
		ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
		for(int i = 0; i < iterations; i++){
			System.out.print(">Number #" + i + ": ");
			String t = user.next();
			nums.add(new BigInteger(t));
		}
		System.out.println("Thank you. Your answer will be calculated shortly....");
		Divide quo = new Divide(nums);
		Thread t1 = new Thread(quo);
		t1.start();
		while(!quo.isDone()){}
		BigInteger ans = quo.getQuotient();
		System.out.println("Your quotient was: " + ans.toString());
		quo.exit();
		System.out.println();
	}
	
	private static void mult(){
		System.out.println("You chose to multiply...");
		System.out.print(">How many numbers would you like to multiply? : ");
		int iterations = user.nextInt();
		ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
		for(int i = 0; i < iterations; i++){
			System.out.print(">Number #" + i + ": ");
			String t = user.next();
			nums.add(new BigInteger(t));
		}
		System.out.println("Thank you. Your answer will be calculated shortly....");
		Multiply mult = new Multiply(nums);
		Thread t1 = new Thread(mult);
		t1.start();
		while(!mult.isDone()){}
		BigInteger ans = mult.getProduct();
		System.out.println("Your product was: " + ans.toString());
		mult.exit();
		System.out.println();
	}
	
	
	private static void sub(){
		System.out.println("You chose to subtract...");
		System.out.print(">How many numbers would you like to subtract? : ");
		int iterations = user.nextInt();
		ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
		for(int i = 0; i < iterations; i++){
			System.out.print(">Number #" + i + ": ");
			String t = user.next();
			nums.add(new BigInteger(t));
		}
		System.out.println("Thank you. Your answer will be calculated shortly....");
		Subtract sub = new Subtract(nums);
		Thread t1 = new Thread(sub);
		t1.start();
		while(!sub.isDone()){}
		BigInteger ans = sub.getDifference();
		System.out.println("Your difference was: " + ans.toString());
		sub.exit();
		System.out.println();
	}
	
	private static void add(){
		System.out.println("You chose to add...");
		System.out.print(">How many numbers would you like to add? : ");
		int iterations = user.nextInt();
		ArrayList<BigInteger> nums = new ArrayList<BigInteger>();
		for(int i = 0; i < iterations; i++){
			System.out.print(">Number #" + i + ": ");
			String t = user.next();
			nums.add(new BigInteger(t));
		}
		System.out.println("Thank you. Your answer will be calculated shortly....");
		Add add = new Add(nums);
		Thread t1 = new Thread(add);
		t1.start();
		while(!add.isDone()){}
		BigInteger ans = add.getSum();
		System.out.println("Your sum was: " + ans.toString());
		add.exit();
		System.out.println();
	}
}
