package main;

import gameObjects.GameObject;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.event.MouseEvent;
import betterMultiplayerSetup.Client;


public class Game extends PApplet{
	
	private int count = 0;
	private Client client;
	
	private GameObject tester;
	
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	
	public void setup(){
		size(500, 500);
		
		tester = new GameObject(100, 321, 40, 40, "Tester Bob", count);
		this.add(tester);
		
		initServer();
		
	}
	
	private void initServer() {
		client = new Client("localhost", 8888);
		Thread t = new Thread(client);
		t.start();		
	}

	public void draw(){
		background(255, 255, 255);
		
		this.updateSendClient(client);
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		System.out.println("new Gameobject added");
		this.add(new GameObject(mouseX, mouseY, 100, 100, "GO: " +count, count));
	}
	
	////////////////////////////////////////////////////////////HELPER METHODS////////////////////////////////////////////
	private void add(GameObject g){
		this.gameObjects.add(g);
		this.count++;
	}
	
	private void updateSendClient(Client c){
		for(GameObject g: this.gameObjects)
			client.sendGameObject(g);
	}
	
}
