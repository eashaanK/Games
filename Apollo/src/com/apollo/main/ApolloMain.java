package com.apollo.main;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class ApolloMain {

	public static void main(String args[]) throws LineUnavailableException {
		// PApplet.main(new String[] {/* "--present", */"com.apollo.main.Apollo"
		// });
		AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
		 
		DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);
 
		try {
			TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
			targetLine.open(format);
			targetLine.start();
			
			SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
			sourceLine.open(format);
			sourceLine.start();
 
			int numBytesRead;
			byte[] targetData = new byte[targetLine.getBufferSize() / 5];
 
			while (true) {
				numBytesRead = targetLine.read(targetData, 0, targetData.length);
				
				//for(int i = 0; i < targetData.length; i++){
					//System.out.print(targetData[i] + ", ");
				//}
				System.out.println();
				
				if (numBytesRead == -1)	break;
 
				sourceLine.write(targetData, 0, numBytesRead);
			}
		}
		catch (Exception e) {
			System.err.println(e);
		}
	}

}
