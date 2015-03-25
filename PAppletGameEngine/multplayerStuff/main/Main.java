package main;

import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import processing.core.PApplet;
import processing.event.KeyEvent;
import server.GameServer;
import client.GameClient;
import entities.PlayerMP;

public class Main extends PApplet{

	ArrayList<PlayerMP> entities = new ArrayList<PlayerMP>();
	
	PlayerMP player;
	
	float width = 50, height = 50;

	GameClient client;
	
	public void setup(){
		if(JOptionPane.showConfirmDialog(null, "START SERVER?") == 0)
			try {
				GameServer server = new GameServer();
				server.showDebugger(true);
			} catch (SocketException e) {
				e.printStackTrace();
			}
		size(800, 800);
		client = new GameClient("Eashaan" + (int)(Math.random() * 100), this, (float)(Math.random() * width/2), (float)(Math.random() * height/2));
		client.begin();
		
		player = new PlayerMP(client.username, client.host, client.serverPort);
		player.x = client.spawnX;
		player.y = client.spawnY;
		
		entities.add(player);
	}
	
	public void draw(){
		background(0, 0, 0);
		
		
		for(int i = 0; i < entities.size(); i++){
			fill(255, 0, 0);
			rect(entities.get(i).x, entities.get(i).y, width, height);
			fill(255, 255, 255);
			textSize(10);
			text(entities.get(i).name, entities.get(i).x - 5, entities.get(i).y - 5);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		boolean isMoved = false;
		if(e.getKey() == 'w' || e.getKey() == 'W'){
			player.y-=5;
			isMoved = true;
		}
		
		if(e.getKey() == 's' || e.getKey() == 'S'){
			player.y+=5;
			isMoved = true;
		}
		
		if(e.getKey() == 'a' || e.getKey() == 'A'){
			player.x-=5;
			isMoved = true;
		}
		
		if(e.getKey() == 'd' || e.getKey() == 'D'){
			player.x+=5;
			isMoved = true;
		}
		
		if(e.getKey() == 'q' || e.getKey() == 'Q'){
			client.sendDisconnect();
			System.exit(-1);
		}
		
		if(isMoved){
			client.sendMoveData(player.x, player.y);
		}
	}
	
	public synchronized ArrayList<PlayerMP> getEntities(){
		return this.entities;
	}
	
	public synchronized void setEntity(PlayerMP p, int index){
		this.entities.set(index, p);
	}

	public synchronized void addEntity(PlayerMP playerMP) {
		this.entities.add(playerMP);
	}
	
	public synchronized void removeEntity(int index) {
		this.entities.remove(index);
	}

	public synchronized void displayMessgae(String message) {
		System.out.println("Main: " + message);
	}
	
}
