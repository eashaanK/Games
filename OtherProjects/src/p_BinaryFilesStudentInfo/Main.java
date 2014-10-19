package p_BinaryFilesStudentInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Notes:
 * As soon as u write something, the pointer moves on.
 * @author eashaan
 *
 */
public class Main {

	private static RandomAccessFile file;
	private static final int NAME_LENGTH = 10;
	private static final int DOUBLE_LENGTH = 8;
	private static final int RECORD = NAME_LENGTH + NAME_LENGTH + DOUBLE_LENGTH;//First name + last name + double
	
	public static void main(String[] args){
		try {
			file = new RandomAccessFile("src/info.dat", "rw");
			
			//write
			System.out.println("Now Writing...\n");
			setFilePointer(file, 0);
			writeString(file, resizeString("Eashaan", NAME_LENGTH));
			writeString(file, resizeString("Kumar", NAME_LENGTH));
			writeDouble(file, 3.5);

			System.out.println("\nNow Reading...\n");
			setFilePointer(file, 0);
			readString(file, Main.NAME_LENGTH);
			readString(file, Main.NAME_LENGTH);
			readDouble(file);
			
			//write
			/*System.out.println("Now Writing...\n");
			setFilePointer(file, 1);
			writeString(file, resizeString("Eashaan", NAME_LENGTH));
			writeString(file, resizeString("Kumar", NAME_LENGTH));
			writeDouble(file, 3.5);

			System.out.println("\nNow Reading...\n");
			setFilePointer(file, 1);
			readString(file, Main.NAME_LENGTH);
			readString(file, Main.NAME_LENGTH);
			readDouble(file);*/

			
			file.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static void setFilePointer(RandomAccessFile file, int pointer) throws IOException{
		file.seek(pointer * RECORD);
		printFilePointer(file);
	}
	/////////////////////////Reading///////////////////////////////
	public static double readDouble(RandomAccessFile file) throws IOException{
		double d = file.readDouble();
		System.out.println("-Double read: " + d);
		printFilePointer(file);
		return d;
	}
	
	public static String readString(RandomAccessFile file, int length) throws IOException{
		String string = "";
		//for(int i = 0; i < length; i++)
		//	string += file.readChar();
		string = file.readUTF();
		System.out.println("-String read: " + string);
		printFilePointer(file);
		return string;
	}
	
	/////////////////////////Writing/////////////////////////////
	public static void writeDouble(RandomAccessFile file, double d) throws IOException{
		file.writeDouble(d);
		System.out.println("-Double written: " + d);
		printFilePointer(file);
	}
	
	public static void writeString(RandomAccessFile file, String s) throws IOException{
		file.writeUTF(s);
		System.out.println("-String written: " + s);
		printFilePointer(file);

	}
	
	public static void printFilePointer(RandomAccessFile file) throws IOException{
		System.out.println("File Pointer at: " + file.getFilePointer());
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
				string += "@";
			}
			return string;
		}
	}
}
