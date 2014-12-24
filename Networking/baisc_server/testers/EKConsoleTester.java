package testers;

import java.awt.Color;

import ek_GUI.EKConsole;

public class EKConsoleTester {

	public static void main(String[] args){
		EKConsole console = new EKConsole(500, 400, "Tester", Color.white);
		Thread t = new Thread(console);
		t.start();
		
		for(int i = 0; i < 100; i++){
			console.println(i + "", Color.red);
		}


	}
}
