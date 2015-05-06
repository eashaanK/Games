package mineMapping;

import core.GameObject;
import core.Vector3f;

public class Robot {

	private GameObject gameObj;
	
	public Robot(GameObject gameObj){
		this.gameObj = gameObj;
	}
	
	public void rotateLeft(){
		this.gameObj.GetTransform().Rotate(new Vector3f(0, 1, 0), -90);
	}
}
