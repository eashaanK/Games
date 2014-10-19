
package game;

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
import core.Matrix4f;
import core.Quaternion;
import core.Vector3f;

public class TestGame extends Game
{
	DirectionalLight directionalLight;
	public void Init()
	{
		Display.setTitle("TestGame");
		RenderingEngine rE = new RenderingEngine();
		rE.setClearColor(new ClearColor(101f/255f, 198f/255f, 254f/255f, 0f));
		this.Render(rE);
		
		Mesh mesh = new Mesh("plane3.obj");
		Material material2 = new Material(new Texture("bricks.jpg"), 1, 8,new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.03f, -0.5f);

		Material material = new Material(new Texture("bricks2.jpg"), 1f, 50,
				new Texture("bricks2_normal.png"), new Texture("bricks2_disp.jpg"), 0.04f, -1.0f);


		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

		GameObject planeObject = new GameObject();
		planeObject.AddComponent(meshRenderer);
		planeObject.GetTransform().GetPos().Set(0, -1, 5);
		planeObject.GetTransform().SetScale(new Vector3f(1, 1, 1));

		GameObject directionalLightObject = new GameObject();
		directionalLight= new DirectionalLight(new Vector3f(0,0,1), 0.4f);
		directionalLight.SetColor(new Vector3f(1f, 1f, 1f));
		directionalLightObject.AddComponent(directionalLight);
		Display.setResizable(true);

		//GameObject pointLightObject = new GameObject();
		//pointLightObject.AddComponent(new PointLight(new Vector3f(0, 1, 0), 0.4f, new Attenuation(0, 0, 1)));

		//SpotLight spotLight = new SpotLight(new Vector3f(0,1,1), 0.4f,new Attenuation(0,0,0.1f), 0.7f);

		//GameObject spotLightObject = new GameObject();
		//spotLightObject.AddComponent(spotLight);

		//spotLightObject.GetTransform().GetPos().Set(5, 0, 5);
		//spotLightObject.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(90.0f)));

		AddObject(planeObject);
		AddObject(directionalLightObject);
		//AddObject(pointLightObject);
		//AddObject(spotLightObject);
		
		Mesh tempMesh = new Mesh("monkey3.obj");

		GameObject testMesh3 = new GameObject().AddComponent(new LookAtComponent()).AddComponent(new MeshRenderer(tempMesh, material));

		AddObject(
				//AddObject(
				new GameObject().AddComponent(new FreeLook(0.5f)).AddComponent(new FreeMove(10.0f))
						.AddComponent(new Camera((float) Math.toRadians(70.0f),
								(float) Window.GetWidth() / (float) Window.GetHeight(), 0.01f, 1000.0f)));
		

		AddObject(testMesh3);

		testMesh3.GetTransform().GetPos().Set(5, 5, 5);
		testMesh3.GetTransform().SetRot(new Quaternion(new Vector3f(0, 1, 0), (float) Math.toRadians(-70.0f)));

		AddObject(new GameObject().AddComponent(new MeshRenderer(new Mesh("monkey3.obj"), material2)));

		directionalLight.GetTransform().SetRot(1, 0, 0, -45);
	}
	
	@Override
	public void Update(float delta)
	{
		super.Update(delta);
		this.directionalLight.GetTransform().Rotate(new Vector3f(0, 1, 0), 0.01f);
		directionalLight.SetIntensity(1f);
	}
	
}
