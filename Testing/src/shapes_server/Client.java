package shapes_server;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import shapes_gameObjects.GameObject;
import shapes_gameObjects.GameObjectMP;
import shapes_main.Game;

public class Client extends StoppableThread implements Runnable{

	private String host;
	private int port;
	private Scanner in;
	private PrintWriter out; 
	
	public Client(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			Socket socket = new Socket(host, port);
			in = new Scanner(socket.getInputStream());
			out = new PrintWriter(socket.getOutputStream());
			
			this.join();
			
			while(this.isActive()){
				if(in.hasNext()){
					String message = in.nextLine();
					String[] parts = message.split(C.REGEX);
					if(parts[0].equals(C.JOIN_CONFIRMED)){
						System.out.println("YAYA!!!!!!!!");
					}
					else if(parts[0].equals(C.JOIN_FAIL)){
						JOptionPane.showMessageDialog(null,  "Server denied your connection on terms of : " + parts[1], C.JOIN_FAIL + " Error", JOptionPane.ERROR_MESSAGE);
						this.fullStop();
					}
					else if(parts[0].equals(C.PLAYER_INFO)){
						playerInfo(parts);
					}
				}
			}
			
		} catch (UnknownHostException e) {
			JOptionPane.showMessageDialog(null, "Failed to connect to: " + host + " " + port, e.toString(), JOptionPane.ERROR_MESSAGE);
			this.fullStop();
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,  "Failed to connect to: " + host + " " + port, e.toString(), JOptionPane.ERROR_MESSAGE);
			this.fullStop();
			e.printStackTrace();
		}
	}
	
	public void sendPlayer(GameObject p){
		if(p == null)
			throw new NullPointerException("U tried to send a null object to the server");
		out.println(C.PLAYER_INFO +C.REGEX + C.DECOY + C.REGEX + p.getName() + C.REGEX + p.X() + C.REGEX + p.Y() + C.REGEX + p.A() + C.REGEX + p.C().getRed() + 
				C.REGEX + p.C().getGreen() + C.REGEX + p.C().getBlue() + C.REGEX + p.C().getAlpha());
		out.flush();
	}
	
	private void playerInfo(String[] parts){
		String name = parts[2];
		float x = Float.parseFloat(parts[3]);
		float y = Float.parseFloat(parts[4]);
		float angle = Float.parseFloat(parts[5]);
		int red = Integer.parseInt(parts[6]);
		int green = Integer.parseInt(parts[7]);
		int blue = Integer.parseInt(parts[8]);
		int alpha = Integer.parseInt(parts[9]);
		Color color = new Color(red, green, blue, alpha);
		
		if(parts[1].equals(C.NEW))
		{
			Game.otherPlayer = new GameObjectMP(Game.player.getParent(), x, y, angle, color, name);
		}
		
		else if(parts[1].equals(C.OLD))
		{
			Game.otherPlayer.updateValues(x, y, angle, color, name);
		}
	}
	
	private void join(){
		out.println(C.JOIN + C.REGEX + C.DECOY + C.REGEX + Game.player.getName() + C.REGEX + Game.player.X() + C.REGEX + Game.player.Y() + C.REGEX + Game.player.A() + C.REGEX + Game.player.C().getRed() + 
				C.REGEX + Game.player.C().getGreen() + C.REGEX + Game.player.C().getBlue() + C.REGEX + Game.player.C().getAlpha());
		out.flush();
	}

}
