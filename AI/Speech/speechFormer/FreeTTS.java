package speechFormer;

import java.util.Scanner;

public class FreeTTS {

	public static void main(String[] args) {
		EKVoice speaker = new EKVoice();
		Scanner user = new Scanner(System.in);
		
		String input =  "";
		
		while(!input.equals("quit")){
			System.out.print("Message: ");
			
			input = user.nextLine();
			
			speaker.speak(input);

		}
	}
}