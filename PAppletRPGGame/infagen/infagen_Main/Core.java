package infagen_Main;

import infagen_entity.Entity;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;

public class Core extends PApplet{
	
	private boolean titleSet = false;
	private static final String TITLE = "INFAGEN";
	
	private float delta;
	private long lastFrame;
	private float timeSpeed = 100000000;
	
	private static List<Entity> allEntities = new ArrayList<Entity>();
	
	private static Game game;
		
	public void setup(){
		size(800, 800);
		this.rectMode(CENTER);
		this.ellipseMode(CENTER);
		this.imageMode(CENTER);
		
		game = new Game(this);
		
		lastFrame = System.nanoTime();
	}
	
	public void draw(){
		background(0, 0, 0);
		
		long currentFrame = System.nanoTime();
		delta = (currentFrame - lastFrame) / timeSpeed;
		lastFrame = currentFrame;
		System.out.println(delta);
		
		for(int i = allEntities.size()-1; i >= 0; i--){
			PVector pos = allEntities.get(i).getTransform().getPosition();
			float w = allEntities.get(i).getTransform().getWidth();
			float h = allEntities.get(i).getTransform().getHeight();
			float rightSide = pos.x + w/2;
			float leftSide = pos.x - w/2;
			float downSide = pos.y + h/2;
			float upSide = pos.y - h/2;
			
			if(rightSide < 0 || leftSide > width || downSide < 0 || upSide > height){
				continue;
			}
			
			else{
				if(!allEntities.get(i).isAlive()){
					allEntities.get(i).onClose();
					allEntities.remove(i);
				}
				else{
					allEntities.get(i).render(this);
					allEntities.get(i).update(delta);
				}

			}

		}
		
		game.update(delta);
		
	}
	
	public static void add(Entity entity){
		Core.allEntities.add(entity);
	}
	
	public void keyPressed(KeyEvent e){
		
	}
}
