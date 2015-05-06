
package game;

import mineMapping.Robot;

import org.lwjgl.opengl.Display;

import rendering.ClearColor;
import rendering.Material;
import rendering.Mesh;
import rendering.RenderingEngine;
import rendering.Texture;
import rendering.Window;

import components.Camera;
import components.DirectionalLight;
import components.FreeLook;
import components.FreeMove;
import components.MeshRenderer;

import core.Game;
import core.GameObject;
import core.Vector3f;

public class TestGame extends Game
{
	DirectionalLight directionalLight;
	Robot robot;
	public void Init()
	{
		Display.setTitle("TestGame");
		RenderingEngine rE = new RenderingEngine();
		rE.setClearColor(new ClearColor(130f/255f, 212f/255f, 250f/255f, 0));
		this.Render(rE);
		
		//plane
		Mesh plane = new Mesh("plane3.obj");
		Material planeMaterial = new Material(new Texture("defaultTexture.png"), 1f, 50, new Texture("default_normal.jpg"), new Texture("default_disp.png"), 0.04f, -1.0f);
		MeshRenderer planeMeshRenderer = new MeshRenderer(plane, planeMaterial);
		GameObject planeObject = new GameObject();
		planeObject.AddComponent(planeMeshRenderer);
		planeObject.GetTransform().GetPos().Set(0, -1, 5);
		planeObject.GetTransform().SetScale(new Vector3f(1, 1, 1));

		//directional light
		GameObject directionalLightObject = new GameObject();
		directionalLight= new DirectionalLight(new Vector3f(0,0,1), 1f);
		directionalLight.SetColor(new Vector3f(1, 1, 1));
		directionalLightObject.AddComponent(directionalLight);
		directionalLight.GetTransform().SetRot(1, 0, 0, -45);
		
		//free look camera
		AddObject(
				//AddObject(
				new GameObject().AddComponent(new FreeLook(0.5f)).AddComponent(new FreeMove(10.0f))
						.AddComponent(new Camera((float) Math.toRadians(70.0f),
								(float) Window.GetWidth() / (float) Window.GetHeight(), 0.01f, 1000.0f)));
		
		//robot
		Mesh robotMesh = new Mesh("Avent.obj");
		Material robotMaterial = new Material(new Texture("bricks2.jpg"), 1f, 50, new Texture("bricks2_normal.jpg"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);
		MeshRenderer robotMeshRenderer = new MeshRenderer(robotMesh, robotMaterial);
		GameObject robotObject = new GameObject();
		robotObject.AddComponent(robotMeshRenderer);
		robotObject.GetTransform().GetPos().Set(0, -1, 5);
		robotObject.GetTransform().SetScale(new Vector3f(1, 1, 1));
		
		//robot = new Robot(robotObject);
		
		
		AddObject(planeObject);
		AddObject(directionalLightObject);
		AddObject(robotObject);

	}
	
	
	@Override
	public void Update(float delta)
	{
		super.Update(delta);
		
		if(core.Input.GetMouseUp(0)){
		//	robot.rotateLeft();
		}
	
	}
	
}
