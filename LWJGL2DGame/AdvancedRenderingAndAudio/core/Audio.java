package core;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.alBufferData;
import static org.lwjgl.openal.AL10.alDeleteBuffers;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alSourcePlay;
import static org.lwjgl.openal.AL10.alSourcei;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.WaveData;

import window.Window;

import com.lwjgl2D.input.Input;


public class Audio {

	public static void main(String[] args) throws FileNotFoundException, LWJGLException{
		Window.createWindow();
		//initOPENGL();
		
		//init OPENAL
		String soundName = "thump.wav";
		
		//first step, create a specific buffer for the specific sound file and dispose it
			WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("AdvancedRenderingAndAudio/audioRes/" + soundName)));
			int blopSoundBuffer = AL10.alGenBuffers();
			alBufferData(blopSoundBuffer, data.format, data.data, data.samplerate);
			data.dispose();
			
		//second step, make the current screen a source
			int source = alGenSources();
			alSourcei(source, AL_BUFFER, blopSoundBuffer);
			
			System.out.println(blopSoundBuffer);

		
		while(!Display.isCloseRequested()){
			//updateOPENGL();
			if(Input.GetKeyDown(Input.KEY_SPACE)){
				//play Sound
				alSourcePlay(source);
				System.out.println("Played Sound");
			}	
			Window.updateDelta();
			Window.updateDisplay();
		}
		
		//clean up audio buffer
		alDeleteBuffers(blopSoundBuffer);
		
		Window.closeWindow();
	}
	
	/* public static void main(String[] args) throws FileNotFoundException {
	        try {
	            Display.setDisplayMode(new DisplayMode(640, 480));
	            Display.setTitle("OpenAL Demo");
	            Display.create();
	            AL.create();
	        } catch (LWJGLException e) {
	            e.printStackTrace();
	            Display.destroy();
	            AL.destroy();
	            System.exit(1);
	        }
	        WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("AdvancedRenderingAndAudio" + File.separatorChar +
	                "audioRes" + File.separatorChar + "Blop.wav")));
	        int buffer = alGenBuffers();
	        alBufferData(buffer, data.format, data.data, data.samplerate);
	        data.dispose();
	        int source = alGenSources();
	        alSourcei(source, AL_BUFFER, buffer);
	        while (!Display.isCloseRequested()) {
	            while (Keyboard.next()) {
	                if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
	                    alSourcePlay(source);
	                    System.out.println("lol");
	                }
	            }
	            Display.update();
	            Display.sync(60);
	        }
	        alDeleteBuffers(buffer);
	        AL.destroy();
	        Display.destroy();
	        System.exit(0);
	    }*/
}
