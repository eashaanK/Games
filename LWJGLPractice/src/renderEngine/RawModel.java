package renderEngine;

/**
 * This thing doesn't actually exist. LOL. I know. Thats pretty abstract.....
 * @author eashaan
 *
 */
public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}
}
