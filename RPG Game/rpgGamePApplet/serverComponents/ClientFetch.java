package serverComponents;

import gameObject.Player;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import main.MultiPlayerGame;

public class ClientFetch extends StoppableThread implements Runnable{

	public Scanner in;
	public ClientFetch(Scanner in){
		this.in = in;
	}

	@Override
	public void run() {
		
		while(this.isActive()){
			if(in.hasNext()){
				String message = in.nextLine();
				String[] parts = message.split(C.REGEX);
				
				if(parts[0].equals(C.PLAYER)){
					handlePlayer(parts);
				}
				
				else if (parts[0].equals(C.MAP)) {
					handleMap(parts);

				}
				else if (parts[0].equals(C.MULTIPLAYER_GAME_STATUS)) {
					handleMGS(parts);

				}

				else if (parts[0].equals(C.MESSAGE)) {
					handleMessage(parts);

				}
				else if (parts[0].equals(C.AI)) {
					handleAI(parts);

				}
				
				else if (parts[0].equals(C.JOIN_SUCCESSFUL)) {
					MultiPlayerGame.isJoined = true;
				}
				
				//rest of message should contain the reason why join failed
				else if (parts[0].equals(C.JOIN_FAILURE)) {
					MultiPlayerGame.isJoined = false;
					JOptionPane.showMessageDialog(null, C.getRestOfMessage(1, parts), "Join attempt failed", JOptionPane.ERROR_MESSAGE);
				}

				else
					System.err.println("THE MESSAGE RECIEVED BY CLIENT DID NOT CONTAIN ANY UNDERSTANBLE PREFIX: " + message);

			}
		}
	}

	private void handleMGS(String[] parts) {
		System.out.println(parts[0]);
		
	}

	private void handleMap(String[] parts) {
		System.out.println(parts[0]);
		
	}

	private void handleAI(String[] parts) {
		System.out.println(parts[0]);
		
	}

	private void handleMessage(String[] parts) {
		System.out.println(parts[0]);
		
	}

	/**
	 * PLAYER/x/y/width/height/name/health/(,...,pixels)/currentR, currentC,
	 * soundFilename
	 * 
	 * @param p
	 */
	private void handlePlayer(String[] parts) {
		float x = Float.parseFloat(parts[1]);
		float y = Float.parseFloat(parts[2]);
		float width = Float.parseFloat(parts[3]);
		float height = Float.parseFloat(parts[4]);
		String name = parts[5];
		if(name.equals(MultiPlayerGame.player.getName()))
			return;
		float health = Float.parseFloat(parts[6]);
		String pixelsString = parts[7];
		byte currR = Byte.parseByte(parts[8]);
		byte currC = Byte.parseByte(parts[9]);
		String soundFileName = parts[10];
		
		


	///	System.out.println(x + " " + y + " " + width + " " + height + " " + name + " " + health + " " + currR+ " " + currC + " " + soundFileName);
	//	System.out.println(pixelsString);
		int index = MGameHasName(MultiPlayerGame.onlinePlayers, name);
		if( index == -1){ //its name is not in the list, meaning its not there
			pixelsString = pixelsString.replace("(", "");
			pixelsString = pixelsString.replace(")", "");
			pixelsString = pixelsString.replace(" ", "");
			
			String[] pixelStringArray = pixelsString.split(",");
			int[] playerImagePixels = new int[pixelStringArray.length];
			
			for(int i = 0; i < playerImagePixels.length; i++){
				playerImagePixels[i] = Integer.parseInt(pixelStringArray[i]);
			}
			//playerImagePixels must not be the entire sprite sheet?
			MultiPlayer p = new MultiPlayer(MultiPlayerGame.parent, playerImagePixels, x, y, name);
			p.setWidth(width);
			p.setHeight(height);
			p.setHealth(health);
			MultiPlayerGame.onlinePlayers.add(p);
		}
		
		else
		{
			MultiPlayer p = MultiPlayerGame.onlinePlayers.get(index);
			p.setX(x);
			p.setY(y);
			p.setWidth(width);
			p.setHeight(height);
			p.setName(name);
			p.setHealth(health);
			p.setCurrentR(currR);
			p.setCurrentC(currC);
			p.setCurrentSoundFile(soundFileName);
		}

	}
	
	private int MGameHasName(ArrayList<MultiPlayer> array, String name){
		for(int i = 0; i < array.size(); i++){
			if(array.get(i).getName().equals(name))
				return i;
		}
		
		return -1;
	}
}
