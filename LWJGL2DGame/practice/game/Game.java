package game;

import input.Input;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

import window.Window;

public class Game {

	Vector3f[] points;
	float speed = 0.0f;
	
	public Game(){
		init();
	}
	
	public void init(){
		points = new Vector3f[1000];
		Random random = new Random( (int) (Math.random() * 100000));
		
		for(int i = 0; i < points.length; i++){
			points[i] = new Vector3f( (random.nextFloat() - 0.5f) * 100f, (random.nextFloat() - 0.5f) * 100f, random.nextInt(200) - 200);
		}
	}
	
	public void update(float delta){
		glTranslatef(0, 0, speed);
		
		glBegin(GL_POINTS);
		for(Vector3f p: points){
			glVertex3f(p.x, p.y, p.z);
		}
		glEnd();
		
		if(Input.GetKeyDown(Input.KEY_W))
			speed += 0.01f ;
		
		if(Input.GetKeyDown(Input.KEY_S))
			speed -= 0.01f;

		//else
			//speed = 0;
	}
	
	public void close(){
		
	}
}
