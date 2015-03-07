package com.infagen.helpers;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import com.infagen.gameObject.Camera;
import com.infagen.gameObject.Transform;

public class Maths {

	public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
		Matrix4f matrix = new Matrix4f();
		matrix.setIdentity();
		Matrix4f.translate(translation, matrix, matrix);
		Matrix4f.rotate((float)(Math.toRadians(rx)), new Vector3f(1, 0, 0), matrix, matrix);
		Matrix4f.rotate((float)(Math.toRadians(ry)), new Vector3f(0, 1, 0), matrix, matrix);
		Matrix4f.rotate((float)(Math.toRadians(rz)), new Vector3f(0, 0, 1), matrix, matrix);
		Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
		return matrix;
	}
	
	public static Matrix4f createTransformationMatrix(Transform t){
		return createTransformationMatrix(t.getPosition(), t.getRotX(), t.getRotY(), t.getRotZ(), t.getScale());
	}
	
	public static Matrix4f createViewMatrix(Camera camera){
		Transform cTrans = camera.getTransform();
		Matrix4f viewMatrix = new Matrix4f();
		viewMatrix.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(cTrans.getRotX()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(cTrans.getRotY()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
		Matrix4f.rotate((float) Math.toRadians(cTrans.getRotZ()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
		
		Vector3f cameraPos = cTrans.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x, -cameraPos.y, -cameraPos.z);
		
		Matrix4f.translate(negativeCameraPos, viewMatrix, viewMatrix);
		return viewMatrix;

	}
	
	public static float getDistance(Vector3f pos1, Vector3f pos2){
		return  (float) Math.sqrt( (double)(Math.pow(pos1.x - pos2.x, 2) )+  (double)(Math.pow(pos1.y - pos2.y, 2)) + (double)(Math.pow(pos1.z - pos2.z, 2)));

	}
}
