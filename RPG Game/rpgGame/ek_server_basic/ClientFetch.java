package ek_server_basic;

import java.util.Scanner;

import javax.swing.JOptionPane;

import rpg_game_components.MultiplayerPlayer;
import rpg_game_main.Game;
import rpg_game_main.GameState;

public class ClientFetch extends StoppableThread implements Runnable{

	private Scanner in;
	public ClientFetch(Scanner in){
		this.in = in;
	}

	@Override
	public void run() {
		while(this.isActive()){
			if(in.hasNext())
			{
				String message = in.nextLine();
				
				if(message != null){
					String[] parts = message.split("/");
					if(parts[0].equals(Client.FETCH))
						handleFetch(parts);
					else if(parts[0].equals(Client.LIST))
						handleList(parts);
					else if(parts[0].equals(Client.JOIN_SUCCESSFUL))
						handleJoinSuccess();
					else if(parts[0].equals(Client.JOIN_FAIL))
						handleJoinFail(parts);
					else if(parts[0].equals(Client.FETCH_PLAYER_BOUNDS))
						handleFetchPlayerBounds(parts);
				}
			}
		}
		
	}
	
	private void handleFetchPlayerBounds(String[] parts){
		//Client.console.println(Client.FETCH_PLAYER_BOUNDS);
		if(parts[1].equals("NULL")){
			return;
		}
		String name = parts[1];
		int xPos = Integer.parseInt(parts[2]);
		int yPos = Integer.parseInt(parts[3]);
		int width = Integer.parseInt(parts[4]);
		int height = Integer.parseInt(parts[5]);
		String imageType = parts[6];
		String imagePath = parts[7];
		
		System.out.println(imagePath); //not collective!!!!!!

	//	Client.console.println("Bounds : name: " + name + " x:" + xPos + " y:" + yPos + " w: " + width + " h:" + height);
	//	Client.console.println("imageType: " + imageType + " imagePath: " + imagePath + " image Width: " + imageW + " imageHeight: " + imageH);

		MultiplayerPlayer mult = new MultiplayerPlayer(xPos, yPos, width, height, name, imageType, imagePath);
		
		if(!mult.getName().equals(Game.player.getName()))
		{
			//System.out.println("This client belongs to : " + Game.player.getName() + " name recieved: " + name);
			if(!Game.onlinePlayers.contains(mult.getName()))
				Game.onlinePlayers.add(mult);	
		}
	}
	
/*	private boolean onlineListContains(String name){
		for(int i = 0; i < Game.onlinePlayers.size(); i++){
			if(Game.onlinePlayers.get(i).getName().equals(name)){
				return true;	
			}
		}
		return false;
	}*/
	/**
	 * Join Fail
	 * Brings Player back to home screen
	 * stops the client
	 */
	private void handleJoinFail(String[] parts){
		System.err.println(parts[1] + ". Try Again");
		JOptionPane.showMessageDialog(null, "Server Connection Denied", parts[1] + ". Try Again.", JOptionPane.ERROR_MESSAGE);
		Game.stopClient();
		Game.gm = GameState.MainMenu;
	}
	
	/**
	 * JOin Success
	 */
	private void handleJoinSuccess(){
		System.out.println("You connected to Server!");
		//tell user that their info was applicable
	}
	
	/**
	 * Update the list of the players
	 * @param parts
	 */
	private void handleList(String[] parts){
		//Client.console.println("LIST: " + parts[1]);
		//update the list of players
		parts[1] = parts[1].replace("[", "");
		parts[1] = parts[1].replace("]", "");

		String[] userNames = parts[1].split(", ");
		Game.updateUserOnline(userNames);
	}
	
	/**
	 * Fetch
	 * @param parts
	 */
	private void handleFetch(String[] parts){
		String message = this.getRestOfMessage(1, parts);
		if(message == null)
			return;
		//Client.console.println("MESSAGE: " + message);
		//do rest of fetch
	}
	
	private String getRestOfMessage(int index, String[] parts){
		String ans = "";
		for(int i = index; i < parts.length; i++)
			ans += parts[i];
		return ans;
	}
}
