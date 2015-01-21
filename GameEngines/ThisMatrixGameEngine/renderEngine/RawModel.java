package renderEngine;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexC){
		this.vaoID = vaoID;
		this.vertexCount = vertexC;
	}

	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}
}
