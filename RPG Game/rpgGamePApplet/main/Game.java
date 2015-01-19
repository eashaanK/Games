package main;

import java.awt.Color;

import gameObject.Player;
import helpers.DrawHelp;
import processing.core.PApplet;
import processing.event.KeyEvent;
import processing.event.MouseEvent;
import controls.Controls;

//204, 2, 255 hair
//eyes 201, 127, 246
//skin 193, 137, 228
//clothes 214, 192, 227
public class Game extends PApplet{

	public static Controls controls;
	public static final int WIDTH = 800, HEIGHT = 800;
	public SinglePlayerGame singlePlayer;
	public MultiPlayerGame multiPlayer;
	public static GameState gameState = GameState.MultiPlayer;
//	public static ControlP5 gui;

	
	public void setup(){
		size(WIDTH, WIDTH);
		controls = new Controls();
		
		singlePlayer = new SinglePlayerGame(this);
		multiPlayer = new MultiPlayerGame(this);
		

	}
	
	public void draw(){
		background(255, 255, 255);
		//draw everything relative to world

		switch(gameState){
		case Welcome:
			g.resetMatrix();
			textSize(20);
			fill(0, 0, 0);
			text(gameState + "", width/2 - 100, 50);
			//DrawHelp.drawFixedText(this, gameState + "", Color.black, width/2 - 100, 50, 0, 0);
			break;
		case SinglePlayer:
			this.moveWorld(singlePlayer.player);
			//draw the level here
			this.singlePlayer.drawPostTranslate();
			DrawHelp.drawFixedText(this, gameState + "", Color.black, width/2 - 100, 50, singlePlayer.player.X(), singlePlayer.player.Y(), 20);
			break;
		case MultiPlayer:
			this.moveWorld(multiPlayer.player);
			this.multiPlayer.drawPostTranslate();
			DrawHelp.drawFixedText(this, gameState + "", Color.black, width/2 - 100, 50, multiPlayer.player.X(), multiPlayer.player.Y(), 20);
			break;
		case Options:
			break;
		case Pause:
			break;
		}
			

	}
	
	
	
	//////////////////////////////////CONTROLS///////////////////////////////////////
	@Override
	public void keyPressed(KeyEvent e){
		controls.addKey(e.getKeyCode());

		switch(gameState){
		case Welcome:
		//	this.welcome.keyPressed(e);
			break;
		case SinglePlayer:			
			this.singlePlayer.keyPressed(e);
			break;
		case MultiPlayer:
			this.multiPlayer.keyPressed(e);
			break;
		case Options:
		//	this.options.keyPressed(e);
			break;
		case Pause:
		//	this.pause.keyPressed(e);
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
		controls.removeKey(e.getKeyCode());
		switch(gameState){
		case Welcome:
		//	this.welcome.keyReleased(e);
			break;
		case SinglePlayer:			
			this.singlePlayer.keyReleased(e);
			break;
		case MultiPlayer:
			this.multiPlayer.keyReleased(e);
			break;
		case Options:
		//	this.options.keyReleased(e);
			break;
		case Pause:
		//	this.pause.keyReleased(e);
			break;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		controls.addButton(e.getButton());
		switch(gameState){
		case Welcome:
		//	this.welcome.mousePressed(e);
			break;
		case SinglePlayer:			
			this.singlePlayer.mousePressed(e);
			break;
		case MultiPlayer:
			this.multiPlayer.mousePressed(e);
			break;
		case Options:
		//	this.options.mousePressed(e);
			break;
		case Pause:
		//	this.pause.mousePressed(e);
			break;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		controls.removeButton(e.getButton());
		switch(gameState){
		case Welcome:
		//	this.welcome.mouseReleased(e);
			break;
		case SinglePlayer:			
			this.singlePlayer.mouseReleased(e);
			break;
		case MultiPlayer:
			this.multiPlayer.mouseReleased(e);
			break;
		case Options:
		//	this.options.mouseReleased(e);
			break;
		case Pause:
		//	this.pause.mouseReleased(e);
			break;
		}
	}
	
/*	public void controlEvent(ControlEvent theEvent){
		 if(theEvent.getController().getName().equals("Button 1")){
			 System.out.println("Button 1 pressed");
		 }
	}
	*/
	/////////////////////////////////////////////////////////////////////////
	
	private void moveWorld(Player player){
		g.translate(-player.X() + width / 2, -player.Y() + height / 2);
	}
	
	
	

}

