package gameEngine;

public class Vertex {

	private Vector3f pos;
	public static final int SIZE = 3; //how many total numbers in this class
	
	public Vertex(Vector3f pos){
		this.pos = pos;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}
	
	public String toString(){
		return "Vertex: " + pos.toString();
	}
}
