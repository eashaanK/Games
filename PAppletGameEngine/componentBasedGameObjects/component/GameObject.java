package component;

import java.util.ArrayList;

public class GameObject {
	public ArrayList< Component> components = null;
	public Transform transform;
	public String name;
	public GameObject parent;

	public GameObject(String name, Transform t, GameObject parent){
		this.name = name;
		this.transform = t;
		components = new ArrayList< Component>();
		this.parent = parent;
	}
	
	public void addComponent(Component c){
		components.add(c);
	}
	
	public void removeComponent(Component component){
		this.components.remove(component);
	}
	
	public void Input(){
		this.GetAllComponentInput();
		System.out.println(name + "'s input was taken");

	}
	
	public void Update(){
		this.GetAllComponentUpdate();
		System.out.println(name + "'s update was done");
	}
	
	public void Destroy(){
		this.parent = null;
		for(Component c : components){
			c.close();
		}
	}
	
	private void GetAllComponentInput(){
		for(Component c : components){
			c.input();
		}
	}
	
	private void GetAllComponentUpdate(){
		for(Component c : components){
			c.update();
		}
	}
	
	
}
