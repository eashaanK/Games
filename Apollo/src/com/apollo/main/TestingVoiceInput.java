package com.apollo.main;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import processing.core.PApplet;

public class TestingVoiceInput extends PApplet{
	
	int numBytesRead;
	byte[] targetData;
	TargetDataLine targetLine;
	SourceDataLine sourceLine;
	
	public void setup(){
		size(800, 800);
		initVoice();
	}
	
	private void initVoice(){
		AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
		 
		DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
		DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);
 
		try {
			targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
			targetLine.open(format);
			targetLine.start();
			
			sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
			sourceLine.open(format);
			sourceLine.start();
 
		
			targetData = new byte[targetLine.getBufferSize() / 5];
		}catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void draw(){
		background(0, 0, 0);
		stroke(255, 255, 255);
		numBytesRead = targetLine.read(targetData, 0, targetData.length);
		
		//for(int i = 0; i < targetData.length; i++)
			//System.out.print(targetData[i]);
		for(int i = 0; i < targetData.length; i++){
			fill(255, 255, 255);
			rect(i + (width/ targetData.length), 100, (width/ targetData.length), targetData[i]);
		}

		
		if (numBytesRead == -1)	return;

		sourceLine.write(targetData, 0, numBytesRead);
		
		//System.out.println();
	}

}
