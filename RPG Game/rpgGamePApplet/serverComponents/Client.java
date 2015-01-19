package serverComponents;

import gameObject.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import main.Game;
import main.GameState;
import main.MultiPlayerGame;

public class Client extends StoppableThread implements Runnable {

	public String host;
	public int port;
	public Socket socket;
	public PrintWriter out;


	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		try {
			socket = new Socket(host, port);
			out = new PrintWriter(socket.getOutputStream());
			ClientFetch fetch = new ClientFetch(new Scanner(
					socket.getInputStream()));
			Thread tFetch = new Thread(fetch);
			tFetch.start();

			join(MultiPlayerGame.player.getName());
			while (this.isActive()) {
				if (System.nanoTime() % 100000000 == 0) {
					this.fetchMap();
				}
				
				if (System.nanoTime() % 10000000 == 0) {
					this.fetchMPGS();
					this.fetchAI();
					this.fetchMessage();
				}
				if (System.nanoTime() % 100000 == 0) {
					this.fetchPlayer();

				}
			}

		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, e.toString()
					+ "\n Could not Connect to host", "Uknown Host",
					JOptionPane.ERROR_MESSAGE);
			Game.gameState = GameState.Welcome;
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString()
					+ "\n Could not Connect to host", "IOException",
					JOptionPane.ERROR_MESSAGE);
			Game.gameState = GameState.Welcome;
			e.printStackTrace();
		}
	}

	private void join(String name) {
		out.println(C.JOIN + C.REGEX + name);
		out.flush();
	}

	private void fetchPlayer() {
		out.println(C.FETCH_PLAYER);
		out.flush();
	}

	private void fetchMap() {
		out.println(C.FETCH_MAP);
		out.flush();
	}

	private void fetchMPGS() {
		out.println(C.FETCH_MULTIPLAYER_GAME_STATUS);
		out.flush();
	}

	private void fetchMessage() {
		out.println(C.FETCH_MESSAGE);
		out.flush();
	}

	private void fetchAI() {
		out.println(C.FETCH_AI);
		out.flush();
	}

	/**
	 * PLAYER/x/y/width/height/name/health/(,...,pixels)/currentR, currentC,
	 * soundFilename
	 * 
	 * @param p
	 */
	public void sendPlayer(Player p) {
		if(!p.getName().equals(MultiPlayerGame.player.getName()))
			throw new IllegalStateException("Player's name did not match Client's version of Player: Player: " + p.getName() + " Client's: " + MultiPlayerGame.player.getName());
		
		out.println(C.PLAYER + C.REGEX + p.X() + C.REGEX + p.Y() + C.REGEX
				+ p.getWidth() + C.REGEX + p.getHeight() + C.REGEX + p.getName() + C.REGEX
				+ p.getHealth() + C.REGEX
				+ this.turnIntArrayToString(p.getSprite().spriteSheet.pixels) + C.REGEX
				+ p.getSprite().currR + C.REGEX + p.getSprite().currC + C.REGEX
				+ "NO SOUND FILE YET");
		out.flush();
	}

	/**
	 * MAP/Its actual name, not the location
	 */
	public void sendMap(String mapName) {
		out.println(C.MAP + C.REGEX + mapName);
		out.flush();
	}

	/**
	 * MULTIPLAYER_GAME_STATUS/byte
	 * 
	 * @param status
	 */
	public void sendMultiplayerGameStatus(byte status) {
		out.println(C.MULTIPLAYER_GAME_STATUS + C.REGEX + status);
		out.flush();
	}

	/**
	 * MESSAGE/userName/message
	 * 
	 * @param message
	 * @param userName
	 */
	public void sendMessage(String message) {
		if (MultiPlayerGame.player.getName().contains(C.REGEX))
			throw new IllegalArgumentException(
					"The name u tried to send contained the regex " + MultiPlayerGame.player.getName());
		out.println(C.MESSAGE + C.REGEX + MultiPlayerGame.player.getName() + C.REGEX + message);
		out.flush();
	}

	/*
	 * public void sendAI(){
	 * 
	 * }
	 */

	/**
	 * Disconnect/playerName
	 */
	public void sendDisconnect() {
		out.println(C.DISCONNECT + C.REGEX + MultiPlayerGame.player.getName());
		out.flush();
	}
	
	
	// ////////////////////////////////////////////////Helper/////////////////////////////////////////////////////
	private String turnIntArrayToString(int[] t) {
		String ans = "(";
		for (int i = 0; i < t.length - 1; i++) {
			ans += t[i] + ", ";
		}
		ans += t[t.length - 1] + ")";
		return ans;
	}

}
