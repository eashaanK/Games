
package com.ek.components;

import org.lwjgl.opengl.Display;

import com.ek.core.*;
import com.ek.rendering.Window;

public class Camera extends GameComponent
{
	private Matrix4f m_projection;
	private float fov, aspectRatio, zNear, zFar;

	/**
	 * fov in radians
	 * as (float) Window.GetWidth() / (float) Window.GetHeight()
	 * @param fov Field of View in RADIANS
	 * @param as aspectRatio usually this way: (float) Window.GetWidth() / (float) Window.GetHeight()
	 * @param zN near clipping plane
	 * @param zF far clipping plane
	 */
	public Camera(float fov, float as, float zN, float zF)
	{
		this.fov = fov;
		this.aspectRatio = as;
		this.zNear = zN;
		this.zFar = zF;
		this.m_projection = new Matrix4f();
		this.InitCameraPerspective();
	}

	public Matrix4f GetViewProjection()
	{
		Matrix4f cameraRotation = GetTransform().GetTransformedRot().Conjugate().ToRotationMatrix();
		Vector3f cameraPos = GetTransform().GetTransformedPos().Mul(-1);

		Matrix4f cameraTranslation = new Matrix4f().InitTranslation(cameraPos.GetX(), cameraPos.GetY(), cameraPos.GetZ());

		return m_projection.Mul(cameraRotation.Mul(cameraTranslation));
	}

	@Override
	public void AddToEngine(CoreEngine engine)
	{
		engine.GetRenderingEngine().AddCamera(this);
	}
	
	public Matrix4f InitCameraPerspective(){
		return this.m_projection.InitPerspective(fov, aspectRatio, zNear, zFar);
	}
	
	@Override
	public void Update(float delta){
		super.Update(delta);
		if(Display.isResizable())
		{
			this.setAspectRatio((float) Window.GetWidth() / (float) Window.GetHeight());
			this.InitCameraPerspective();
		}
	}

	public float getZNear() {
		return zNear;
	}

	public void setZNear(float zNear) {
		this.zNear = zNear;
	}

	public float getZFar() {
		return zFar;
	}

	public void setZFar(float zFar) {
		this.zFar = zFar;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}
	
	public void setFOV(float fov){
		this.fov = fov;
	}
	
	public float getFov(){
		return fov;
	}
}
