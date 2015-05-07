
package game;

import mineMapping.Robot;

import org.lwjgl.input.Keyboard;
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
import components.PointLight;

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
		//rE.setClearColor(new ClearColor(0, 0, 0, 0));
		this.Render(rE);
		
	

		//directional light
		GameObject directionalLightObject = new GameObject();
		directionalLight= new DirectionalLight(new Vector3f(0,0,1), 1);
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
		Mesh robotMesh = new Mesh("monkey3.obj");
		Material robotMaterial = new Material(new Texture("bricks2.jpg"), 1f, 50, new Texture("bricks2_normal.jpg"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);
		MeshRenderer robotMeshRenderer = new MeshRenderer(robotMesh, robotMaterial);
		GameObject robotObject = new GameObject();
		robotObject.AddComponent(robotMeshRenderer);
		robotObject.GetTransform().GetPos().Set(0, 0, 5);
		robotObject.GetTransform().SetScale(new Vector3f(1, 1, 1));		
		
		//PointLight light = new PointLight();
		
		AddObject(directionalLightObject);
		AddObject(robotObject);
		
		robot = new Robot(robotObject, this);


	}
	
	
	@Override
	public void Update(float delta)
	{
		super.Update(delta);
		
		robot.goForward(1);
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			robot.turnRight();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		robot.update(delta);
	
	}
	
}
