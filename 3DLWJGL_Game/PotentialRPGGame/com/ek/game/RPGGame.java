
package com.ek.game;


import com.ek.components.Camera;
import com.ek.components.DirectionalLight;
import com.ek.components.FreeLook;
import com.ek.components.FreeMove;
import com.ek.components.MeshRenderer;
import com.ek.core.Game;
import com.ek.core.GameObject;
import com.ek.core.Vector3f;
import com.ek.rendering.Material;
import com.ek.rendering.Mesh;
import com.ek.rendering.Texture;
import com.ek.rendering.Window;


public class RPGGame extends Game
{
	
	GameObject freePlayer;
	
	public void Init()
	{
		super.Init();
		
		//Free Moving player
		freePlayer = new GameObject();
		Camera camera = new Camera((float) Math.toRadians(70.0f), (float) Window.GetWidth() / (float) Window.GetHeight(), 0.001f, 1000f);
		FreeLook freeLook = new FreeLook(0.5f);
		FreeMove freeMove = new FreeMove(5);
		
		freePlayer.AddComponent(camera);
		freePlayer.AddComponent(freeLook);
		freePlayer.AddComponent(freeMove);
		
		this.AddObject(freePlayer);
		
		//plane
		GameObject planeObject = new GameObject();
		Mesh mesh = new Mesh("plane3.obj");
		Material material = new Material(new Texture("bricks.jpg"), 1, 8,new Texture("bricks_normal.jpg"), new Texture("bricks_disp.png"), 0.03f, -0.5f);
		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		planeObject.AddComponent(meshRenderer);
		planeObject.GetTransform().GetPos().Set(0, -1, 5);
		planeObject.GetTransform().SetScale(new Vector3f(1, 1, 1));
		
		this.AddObject(planeObject);
		
		//Directional Light
		GameObject directionalLightObject = new GameObject();
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(0,0,1), 0.4f);
		directionalLight.SetColor(new Vector3f(1, 1, 1));
		directionalLight.SetIntensity(1);
		directionalLightObject.AddComponent(directionalLight);

		this.AddObject(directionalLightObject);

	}
	
	@Override
	public void Update(float delta)
	{
		super.Update(delta);
			
	}
	
}
