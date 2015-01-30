package terrains;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import Tester.MainGameLoop;
import renderEngine.Loader;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import toolbox.Maths;
import entities.Bush;
import entities.Flower;
import entities.Grass;
import entities.Rock;
import entities.Tree;

public class Terrain {

	public static final float SIZE = 400;
	public final float MAX_HEIGHT;
	public static final float MAX_PIXEL_COLOR = 256 * 256 * 256;

	
	private float x;
	private float z;
	private RawModel model;
	private TerrainTexturePack texturePack;
	private TerrainTexture blendMap;
	
	public float heights[][];
	
	private static ArrayList<Tree> trees = new ArrayList<Tree>();
	private static ArrayList<Grass> grass = new ArrayList<Grass>();
	private static ArrayList<Bush> bushes = new ArrayList<Bush>();
	private static ArrayList<Rock> rocks = new ArrayList<Rock>();
	private static ArrayList<Flower> flowers = new ArrayList<Flower>();


	public Terrain(int gridX, int gridZ, Loader loader, TerrainTexturePack texturePack, TerrainTexture blendMap, String heightMap, float maxHeight){
		this.MAX_HEIGHT = maxHeight;
		this.texturePack = texturePack;
		this.blendMap = blendMap;
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = this.generateTerrain(loader, heightMap);
	}
	
	private RawModel generateTerrain(Loader loader, String heightMap){
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("ThisMatrixGameEngine/res/" + heightMap + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int VERTEX_COUNT = image.getHeight();
		
		this.heights = new float[VERTEX_COUNT][VERTEX_COUNT];
		
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				float height = getHeight(j, i, image);
				heights[j][i] = height;
				vertices[vertexPointer*3+1] = height; //make a flat[][] height and store height at j, i
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				Vector3f normal = calculateNormal(j, i, image);
				normals[vertexPointer*3] = normal.x;
				normals[vertexPointer*3+1] = normal.y;
				normals[vertexPointer*3+2] = normal.z;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1) ;
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		return loader.loadToVAO(vertices, textureCoords, normals, indices);
	}
	
	private Vector3f calculateNormal(int x, int z, BufferedImage image){
		float hieghtL = this.getHeight(x, z, image);
		float heightR = this.getHeight(x + 1, z, image);
		float heightD = this.getHeight(x, z - 1, image);
		float heightU = this.getHeight(x, z + 1, image);

		Vector3f normal = new Vector3f(hieghtL - heightR, 2f, heightD - heightU);
		normal.normalise();
		return normal;
	}
	
	private float getHeight(int x,  int z, BufferedImage image){
		if(x < 0 || x >= image.getHeight() || z < 0 || z >= image.getHeight()){
			return 0;
		}
		float height = image.getRGB(x, z);
		height += MAX_PIXEL_COLOR/2f; //gives us a range between -MPV/2 and MPV/2
		height /= MAX_PIXEL_COLOR/2f; //range between -1 and 1
		height *= MAX_HEIGHT; //range between MaxHeight and -MaxHeight
		return height;
	}
	
	public void addTree(Tree tree){
		tree.setY(this.getHeightOfTerrainRelativeToWorld(tree.getX(), tree.getZ()));
		this.trees.add(tree);
	}
	
	public void addGrass(Grass grass){
		grass.setY(this.getHeightOfTerrainRelativeToWorld(grass.getX(), grass.getZ()));
		this.grass.add(grass);
	}
	
	public void addBush(Bush bush){
		bush.setY(this.getHeightOfTerrainRelativeToWorld(bush.getX(), bush.getZ()));
		this.bushes.add(bush);
	}
	
	public void addRock(Rock rock){
		rock.setY(this.getHeightOfTerrainRelativeToWorld(rock.getX(), rock.getZ()));
		this.rocks.add(rock);
	}
	
	public void addFlower(Flower flower){
		flower.setY(this.getHeightOfTerrainRelativeToWorld(flower.getX(), flower.getZ()));
		this.flowers.add(flower);
	}
	
	/*public void addTree(TexturedModel model, float x, float z, float rotX, float rotY, float rotZ, float scale){
		float height = this.getHeightOfTerrainRelativeToWorld(x, z);
		Tree tree = new Tree(model, new Vector3f(x, height, z), rotX, rotY, rotZ, scale);
		this.addTree(tree);
	}
	
	public void addGrass(TexturedModel model, float x, float z, float rotX, float rotY, float rotZ, float scale){
		this.grass.add(new Grass(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addBush(TexturedModel model, float x, float z, float rotX, float rotY, float rotZ, float scale){
		this.bushes.add(new Bush(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addRock(TexturedModel model, float x, float z, float rotX, float rotY, float rotZ, float scale){
		this.rocks.add(new Rock(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addFlower(TexturedModel model, float x, float z, float rotX, float rotY, float rotZ, float scale){
		this.flowers.add(new Flower(model, pos, rotX, rotY, rotZ, scale));
	}*/

	public List<Tree> getTrees(){
		return this.trees;
	}
	
	public List<Grass> getGrass(){
		return this.grass;
	}
	
	public List<Bush> getBushes(){
		return this.bushes;
	}
	
	public List<Rock> getRocks(){
		return this.rocks;
	}
	
	public List<Flower> getFlowers(){
		return this.flowers;
	}

	public float getX() {
		return x;
	}

	public float getZ() {
		return z;
	}

	public RawModel getModel() {
		return model;
	}

	public TerrainTexturePack getTexturePack() {
		return texturePack;
	}

	public TerrainTexture getBlendMap() {
		return blendMap;
	}
	
	public float getHeightOfTerrainRelativeToWorld(float worldX, float worldZ){
		float terrainX = worldX - this.x;
		float terrainZ = worldZ - this.z;
		float gridSquareSize = SIZE / (float)(heights.length - 1);
		int gridX = (int) Math.floor(terrainX /gridSquareSize);
		int gridZ = (int) Math.floor(terrainZ /gridSquareSize);
		if(gridX >= heights.length - 1 || gridZ >= heights.length - 1 || gridX < 0 || gridZ < 0){
			return 0;
		}
		float xCoord = (terrainX % gridSquareSize) / gridSquareSize;
		float zCoord = (terrainZ % gridSquareSize) / gridSquareSize;
		float answer;
		if (xCoord <= (1-zCoord)) {
			answer = Maths.barryCentric(new Vector3f(0, heights[gridX][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ], 0), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		} else {
			answer = Maths.barryCentric(new Vector3f(1, heights[gridX + 1][gridZ], 0), new Vector3f(1,
							heights[gridX + 1][gridZ + 1], 1), new Vector3f(0,
							heights[gridX][gridZ + 1], 1), new Vector2f(xCoord, zCoord));
		}	
		return answer;
	}

	
}
