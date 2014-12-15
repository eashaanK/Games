package speechRecognition;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class EKVoice {
	private static final String VOICENAME_kevin = "kevin";
	private static final String VOICENAME_alan = "alan";
	private static final String VOICENAME_kevin16 = "kevin16";

	private Voice voice;

	public EKVoice() {
		VoiceManager voiceManager = VoiceManager.getInstance();
		voice = voiceManager.getVoice(VOICENAME_kevin16);
		voice.allocate();

		voice.setPitch(110);
		voice.setRate(200);

	}

	public void speak(String text) {
		voice.speak(text);
	}

}
