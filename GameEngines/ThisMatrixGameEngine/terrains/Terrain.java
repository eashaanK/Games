package terrains;

import java.util.ArrayList;
import java.util.List;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.Loader;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Bush;
import entities.Flower;
import entities.Grass;
import entities.Rock;
import entities.Tree;

public class Terrain {

	public static final float SIZE = 200;
	private static final int VERTEX_COUNT = 128;
	
	private float x;
	private float z;
	private RawModel model;
	private TerrainTexturePack texturePack;
	private TerrainTexture blendMap;
	
	private static ArrayList<Tree> trees = new ArrayList<Tree>();
	private static ArrayList<Grass> grass = new ArrayList<Grass>();
	private static ArrayList<Bush> bushes = new ArrayList<Bush>();
	private static ArrayList<Rock> rocks = new ArrayList<Rock>();
	private static ArrayList<Flower> flowers = new ArrayList<Flower>();


	public Terrain(int gridX, int gridZ, Loader loader, TerrainTexturePack texturePack, TerrainTexture blendMap){
		this.texturePack = texturePack;
		this.blendMap = blendMap;
		this.x = gridX * SIZE;
		this.z = gridZ * SIZE;
		this.model = this.generateTerrain(loader);
	}
	
	private RawModel generateTerrain(Loader loader){
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE;
				vertices[vertexPointer*3+1] = 0; //actual height???
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE;
				normals[vertexPointer*3] = 0;
				normals[vertexPointer*3+1] = 1;
				normals[vertexPointer*3+2] = 0;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
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
	
	public void addTree(Tree tree){
		this.trees.add(tree);
	}
	
	public void addGrass(Grass grass){
		this.grass.add(grass);
	}
	
	public void addBush(Bush bush){
		this.bushes.add(bush);
	}
	
	public void addRock(Rock rock){
		this.rocks.add(rock);
	}
	
	public void addFlower(Flower flower){
		this.flowers.add(flower);
	}
	
	public void addTree(TexturedModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale){
		this.trees.add(new Tree(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addGrass(TexturedModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale){
		this.grass.add(new Grass(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addBush(TexturedModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale){
		this.bushes.add(new Bush(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addRock(TexturedModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale){
		this.rocks.add(new Rock(model, pos, rotX, rotY, rotZ, scale));
	}
	
	public void addFlower(TexturedModel model, Vector3f pos, float rotX, float rotY, float rotZ, float scale){
		this.flowers.add(new Flower(model, pos, rotX, rotY, rotZ, scale));
	}

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

	
}
