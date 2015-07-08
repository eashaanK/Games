package com.AI;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;


public class EKVoice {
	private static final String VOICENAME_kevin = "kevin";
	private static final String VOICENAME_alan = "alan";
	private static final String VOICENAME_kevin16 = "kevin16";

	private Voice voice;

	/**
	 * 
	 * @param speed recommended 200
	 * @param pitch recommended 110
	 */
	public EKVoice(float speed, float pitch) { 
		VoiceManager voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(VOICENAME_kevin16);
		voice.allocate();

		voice.setPitch(pitch);
		voice.setRate(speed);

	}

	public void speak(String text) {
		//voice.speak(text);
		Speaker s = new Speaker(text);
		s.start();
	}
	
	private class Speaker extends Thread{

		private String text;
		
		public Speaker(String t){
			this.text = t;
		}
		
		@Override
		public void run() {		
			EKVoice.this.voice.speak(text);	
			System.out.println("Done Speaking");
			this.interrupt();
		}
	
	}

}
