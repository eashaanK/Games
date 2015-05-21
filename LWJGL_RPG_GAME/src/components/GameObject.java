package components;

import java.util.ArrayList;

import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;

public class GameObject {

	public ArrayList<GameObject> childern;
	public Transform transform;
	public String name;
	public String tag;
	
	public GameObject(String name, String tag, Transform transform){
		this.name = name;
		this.tag = tag;
		this.transform = transform;
	}
	
	public GameObject(String name, String tag){
		this(name, tag, new Transform(new Vector3f(), new Quaternion(), new Vector3f()));
	}
	
	public GameObject(String name){
		this(name, Tags.DEFAULT, new Transform(new Vector3f(), new Quaternion(), new Vector3f()));
	}
	
	public GameObject(){
		this("GameObject", Tags.DEFAULT, new Transform(new Vector3f(), new Quaternion(), new Vector3f()));
	}

	public void addChild(GameObject child){
		this.childern.add(child);
	}
}
