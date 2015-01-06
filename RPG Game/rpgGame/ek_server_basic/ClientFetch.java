package ek_server_basic;

import java.util.Scanner;

import javax.swing.JOptionPane;

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
				}
			}
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
