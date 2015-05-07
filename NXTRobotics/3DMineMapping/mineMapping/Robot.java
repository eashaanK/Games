package mineMapping;

import org.lwjgl.util.vector.Vector2f;

import rendering.Material;
import rendering.Mesh;
import rendering.Texture;

import components.MeshRenderer;

import core.Game;
import core.GameObject;
import core.Vector3f;

public class Robot {

	private GameObject gameObj;
	private Game game;
	private Vector2f speed;
private float currentAngle = 0;
	
	public Robot(GameObject roboObj, Game game){
		this.game = game;
		this.gameObj = roboObj;
		speed = new Vector2f(0, 0);
	}
	
	public void turnRight(){
		System.out.print("Old Rotation: " + this.gameObj.GetTransform().GetRot().GetY() + " (" + Math.toDegrees(this.gameObj.GetTransform().GetRot().GetY()) + ")");
		currentAngle += 3.14/2;
		this.gameObj.GetTransform().Rotate(new Vector3f(0, 1, 0), 3.14f/2);
		System.out.println(" New Rotation: " + this.gameObj.GetTransform().GetRot().GetY() + " (" + Math.toDegrees(this.gameObj.GetTransform().GetRot().GetY()) + ")");

	}
	
	public void turnLeft(){
		System.out.print("Old Rotation: " + this.gameObj.GetTransform().GetRot().GetY() + " (" + Math.toDegrees(this.gameObj.GetTransform().GetRot().GetY()) + ")");
		currentAngle -= 3.14/2;
		this.gameObj.GetTransform().Rotate(new Vector3f(0, 1, 0), -3.14f/2);
		System.out.println(" New Rotation: " + this.gameObj.GetTransform().GetRot().GetY() + " (" + Math.toDegrees(this.gameObj.GetTransform().GetRot().GetY()) + ")");
	}
	
	public void goForward(float maxSpeed){
		this.speed.x = (float) (Math.sin(currentAngle) * maxSpeed);
		this.speed.y = (float) (Math.cos(currentAngle) * maxSpeed);	
	}
	
	final float MAX = 1;
	float current = 0;
	final float INC = 0.00345f;
	
	public void update(float delta){
		Vector3f pos = this.gameObj.GetTransform().GetPos();
		pos.Set(pos.GetX() + delta * speed.x, 0, pos.GetZ() + delta * speed.y);
		
		current += INC;
		if(current > MAX){
			GameObject tempPlane = createNewPlane();
			GameObject left = createNewPlane();
			GameObject right = createNewPlane();
			left.GetTransform().Rotate(new Vector3f(0, 0, 1), -3.14f/2);
			Vector3f leftPos = left.GetTransform().GetPos();
			//leftPos.
			
			//tempPlane.GetTransform().SetPos(this.gameObj.GetTransform().GetPos());
			game.AddObject(tempPlane);
		//	game.AddObject(left);

			current = 0;
		}
		
	
		System.out.println(this.gameObj.GetTransform().GetPos());
	}
	
	private GameObject createNewPlane(){
		//plane
		Mesh plane = new Mesh("plane3.obj");
		Material planeMaterial = new Material(new Texture("defaultTexture.png"), 1f, 50, new Texture("default_normal.jpg"), new Texture("default_disp.png"), 0.04f, -1.0f);
		MeshRenderer planeMeshRenderer = new MeshRenderer(plane, planeMaterial);
		GameObject planeObject = new GameObject();
		planeObject.AddComponent(planeMeshRenderer);
		planeObject.GetTransform().GetPos().Set(this.gameObj.GetTransform().GetPos().GetX(), -1, this.gameObj.GetTransform().GetPos().GetZ());
		planeObject.GetTransform().SetScale(new Vector3f(0.3f, 0.3f, 0.3f));
		planeObject.GetTransform().SetRot(this.gameObj.GetTransform().GetRot());
		return planeObject;
	}
}
