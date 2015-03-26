package conponent;

import java.util.TreeMap;

import processing.core.PApplet;

public class GameObject {

	private TreeMap<ComponentType, Component> components = new  TreeMap<ComponentType, Component>();
	
	private String name;
	
	public GameObject(String name){
		this.name = name;
	}
	
	public void addComponent(ComponentType type, Component component){
		if(!this.containsComponent(type)){
			components.put(type, component);
		}
	}
	
	public void removeComponent(ComponentType type){
		components.remove(type);
	}
	
	public boolean containsComponent(ComponentType type){
		return components.containsKey(type);
	}
	
	public Component getComponent(ComponentType type){
		return this.components.get(type);
	}
	
	public Transform getTransform(){
		return (Transform)this.getComponent(ComponentType.TRANSFORM);
	}
	
	public void update(float delta){
		this.getTransform().x++;
	}
	
	public void render(PApplet parent){
		parent.fill(255, 0, 0);
		parent.rect(this.getTransform().x, this.getTransform().y, 50, 50);
	}
	
	@Override
	public String toString(){
		return name + " " + this.getTransform().toString();
	}
}
