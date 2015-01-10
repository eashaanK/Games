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
					else if(parts[0].equals(Client.SEND_PLAYER_BOUNDS))
						handleSendPlayerBounds(parts);
				}
			}
		}
		
	}
	
	private void handleSendPlayerBounds(String[] parts){
		String name = parts[1];
		int xPos = Integer.parseInt(parts[2]);
		int yPos = Integer.parseInt(parts[3]);
		int width = Integer.parseInt(parts[4]);
		int height = Integer.parseInt(parts[5]);
		String imageType = parts[6];
		String imagePath = parts[7];
		int imageW = Integer.parseInt(parts[8]);
		int imageH = Integer.parseInt(parts[9]);
		Client.console.println("Bounds : name: " + name + " x:" + xPos + " y:" + yPos + " w: " + width + " h:" + height);
		Client.console.println("imageType: " + imageType + " imagePath: " + imagePath + " image Width: " + imageW + " imageHeight: " + imageH);

		MultiplayerPlayer mult = new MultiplayerPlayer(xPos, yPos, width, height, name);
		/*
		 * 1st Option: the player name exists in both places. If so, update all its components
		 * 2nd Option: the player name exists in userList but not in Player list. In that case, add the player to the list
		 * 3rd Option: the player name does't exist in any List
		 */
		boolean inUserNameList = Game.userNamesOnline.contains(name), inMultPlayerList = Game.onlinePlayers.contains(mult);
		if(inUserNameList && inMultPlayerList){
			System.out.println("Player: " + name + " is in both lists");
			for(int i = 0; i < Game.onlinePlayers.size(); i++){
				if(Game.onlinePlayers.get(i).equals(mult)){
					Game.onlinePlayers.set(i, mult);
					break;
				}
			}
		}
		
		else if(inUserNameList && !inMultPlayerList){
			System.out.println("Player: " + name + " is only in userList and not Player list. So adding Player to list to be drawn");
			Game.onlinePlayers.add(mult);
			Client.console.println(name + " added to be drawn");
		}
		
		else{
			System.err.println("Player: " + name + " was not found in userName List / playerList");
		}
		
	}
	
	/**
	 * JOin Fail
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
		Client.console.println("LIST: " + parts[1]);
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
		Client.console.println("MESSAGE: " + message);
		//do rest of fetch
	}
	
	private String getRestOfMessage(int index, String[] parts){
		String ans = "";
		for(int i = index; i < parts.length; i++)
			ans += parts[i];
		return ans;
	}
}
