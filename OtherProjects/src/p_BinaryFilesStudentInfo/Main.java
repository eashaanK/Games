package p_BinaryFilesStudentInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Notes:
 * As soon as u write something, the pointer moves on.
 * @author eashaan
 *
 */
public class Main {

	private static RandomAccessFile file;
	private static Scanner scanner;
	private static final int NAME_LENGTH = 10;
	private static final int DOUBLE_LENGTH = 8;
	private static final int RECORD = NAME_LENGTH + NAME_LENGTH + DOUBLE_LENGTH;//First name + last name + double
	private static int currentIDCount = 0;
	
	public static void main(String[] args){
		try {
			file = new RandomAccessFile("src/info.dat", "rw");
			scanner = new Scanner(System.in);
			int input = 0;
			System.out.println("Inital File Length: "  + file.length());
			//write
			/*System.out.println("Now Writing...\n");
			setFilePointer(file, 3);
			writeString(file, resizeString("Eashaan", NAME_LENGTH));
			writeString(file, resizeString("Kumar", NAME_LENGTH));
			writeDouble(file, 3.5);*/
			


			/*System.out.println("\nNow Reading...\n");
			setFilePointer(file, 0);
			readString(file, Main.NAME_LENGTH);
			readString(file, Main.NAME_LENGTH);
			readDouble(file);*/
			while(input != 4){
				currentIDCount = (int) ((file.length() - 4) / RECORD); // gives the next available ID to put new data in

				System.out.println("\nChoose a number from Menu.\n1. Add new Student");
				System.out.println("2. Change a Student");
				System.out.println("3. Get student's info");
				System.out.println("4. Quit\n");
				System.out.print("Menu: ");
				input = scanner.nextInt();
				menu(scanner, file, input);

			}
			
			System.out.println("You chose to end the program");

			
			file.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/////////////////////////menu///////////////////////////////////
	public static void menu(Scanner s, RandomAccessFile file, int m) throws IOException {
		switch(m){
		case 1:
			System.out.println("\nYou chose to add a new Student");
			setFilePointer(file, currentIDCount);
			String rF = resizeString(promptForName(s, "first"), Main.NAME_LENGTH);
			String rL = resizeString(promptForName(s, "last"), Main.NAME_LENGTH);
			double gPA = promptForGPA(s);
			writeString(file, rF);
			writeString(file, rL);
			writeDouble(file, gPA);
			System.out.println("\nInfo added to File. This Student's ID is: " + currentIDCount);

			break;
		case 2:
			System.out.println("\nYou chose to change a Student");

			break;
		case 3:
			System.out.println("\nYou chose to get a student's information");
			int id = promptForID(s);
			try {
				setFilePointer(file, id);

				if(isFileAtEnd(file))
				{
					System.err.println("At the end of the file. Cannot read");
					break;
				}
				String firstName = readString(file, Main.NAME_LENGTH).trim();
				String lastName = readString(file, Main.NAME_LENGTH).trim();
				double gpa = readDouble(file);
				
				System.out.println(firstName + " " + lastName + " " + gpa);
			} catch (IOException e) {
				System.err.println("This ID does not have any Student info");
			}
			
			break;
		}
	}
	
	/////////////////////////Reading///////////////////////////////
	public static double readDouble(RandomAccessFile file) throws IOException{
		double d = file.readDouble();
		//System.out.println("-Double read: " + d);
		//printFilePointer(file);
		return d;
	}
	
	public static String readString(RandomAccessFile file, int length) throws IOException{
		String string = "";
		string = file.readUTF();
		//System.out.println("-String read: " + string);
		//printFilePointer(file);
		return string;
	}
	
	/////////////////////////Writing/////////////////////////////
	public static void writeDouble(RandomAccessFile file, double d) throws IOException{
		file.writeDouble(d);
		//System.out.println("-Double written: " + d);
		//printFilePointer(file);
	}
	
	public static void writeString(RandomAccessFile file, String s) throws IOException{
		file.writeUTF(s);
		//System.out.println("-String written: " + s);
		//printFilePointer(file);
	}
	/////////////////////////////Other//////////////////////////////////
	public static int promptForID(Scanner s){
		System.out.print("Enter Student's ID: ");
		return s.nextInt();
	}
	public static double promptForGPA(Scanner s){
		System.out.print("Enter Student's GPA: ");
		return s.nextDouble();
	}
	public static String promptForName(Scanner s, String namePart){
		System.out.print("Enter Student's " + namePart + " name: ");
		return s.next();
	}
	
	public static void printFilePointer(RandomAccessFile file) throws IOException{
		System.out.println("File Pointer at: " + file.getFilePointer());
	}
	
	public static void setFilePointer(RandomAccessFile file, int pointer) throws IOException{
		file.seek(pointer * RECORD);
		//printFilePointer(file);
	}
	
	public static boolean isFileAtEnd(RandomAccessFile file) throws IOException{
		return (file.getFilePointer() >= file.length());
	}
	
	/**
	 * Makes a String a certain length
	 * @param string
	 * @param length
	 * @return
	 */
	public static String resizeString(String string, int length){
		int tempLength = string.length();
		if(tempLength >= length)
			return string.substring(0, length);
		else
		{
			for(int i = tempLength; i < length; i++)
			{
				string += " ";
			}
			return string;
		}
	}
}
