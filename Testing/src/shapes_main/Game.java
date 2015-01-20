package shapes_main;

import helpers.DrawHelp;

import java.awt.Color;

import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import shapes_controls.Controls;
import shapes_gameObjects.GameObject;
import shapes_gameObjects.GameObjectMP;
import shapes_server.Client;
import shapes_server.Server;

public class Game extends PApplet{

	public static final int WIDTH = 500, HEIGHT = 500;
	
	public static Controls controls;
	public static GameObject player;
	
	//server stuff
	public static GameObjectMP otherPlayer;
	private boolean isHost = false;
	public static boolean gameConnected = false;
	private Client client;
	
	public void setup(){
		rectMode(CENTER);
		size(WIDTH, HEIGHT);
		controls = new Controls();
		player = new GameObject(this, width/2, height/2, 0, Color.red, "BOB");
		//multiplayer stiff
		if(JOptionPane.showConfirmDialog(null, "Do u want to start a Server?") == 0){
			isHost = true;
			System.out.println("U are now the host!");
		}
		
		if(isHost){
			Thread tServer = new Thread(new Server(8888, -1));
			tServer.start();
		}
	
		

	}
	
	
	public void draw(){
		background(255, 255, 255);
		this.moveWorld(player.X(), player.Y(), player.A());
		
		
		player.draw();
		player.update(controls);
		
		//multiplayer stuff
		if(!gameConnected){

			client = new Client(JOptionPane.showInputDialog(null, "Host / IP address"), 8888);
			Thread t = new Thread(client);
			t.start();
			this.gameConnected = client.isActive();
		}
		
		if(gameConnected){
			if(otherPlayer != null && otherPlayer.isConnected){
				otherPlayer.draw();
				client.sendGameObject(player);
				otherPlayer.isConnected = client.isActive();
			}
		}
		
		gameConnected = client.isActive();
		
		DrawHelp.drawFixedText(this, player.getName(), Color.black, width/2 - player.getName().length() * 3/2, 30, player.X(), player.Y(), 20);
	}
	
	public void keyPressed(KeyEvent e){
		controls.addKey(e.getKeyCode());

	}
	
	@Override
	public void keyReleased(KeyEvent e){
		controls.removeKey(e.getKeyCode());
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		controls.addButton(e.getButton());

	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		controls.removeButton(e.getButton());
	
	}
	
/*	public void controlEvent(ControlEvent theEvent){
		 if(theEvent.getController().getName().equals("Button 1")){
			 System.out.println("Button 1 pressed");
		 }
	}
	*/
	/////////////////////////////////////////////////////////////////////////
	
	private void moveWorld(float x, float y, float a){
		g.translate(-x + width / 2, -y + height / 2);
	}
	
}
