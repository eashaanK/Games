package tM_entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import tM_models.TexturedModel;
import tM_renderEngine.DisplayManager;
import tM_terrains.Terrain;

public class Player extends Entity {

	private static final float RUN_SPEED = 10;
	private static final float SIDE_SPEED = 5;
	private static final float WALK_SPEED = 8;
	private static final float TURN_SPEED = 160;
	private static final float HORIZONTAL_SENSITIVITY = 2;
	private static final float GRAVITY = -40;
	private static final float JUMP_POWER = 13;

	// private static float terrainHeight = 0;

	private boolean isInAir = false;

	private float currentSpeed = 0, currentTurnSpeed = 0, upwardsSpeed = 0,
			currentSideSpeed = 0;

	private float cameraOffset = 1.5f;

	private Camera currentCamera;
	private ThirdPersonCamera camera3rd;
	private FirstPersonCamera camera1st;

	public Player(TexturedModel model, Vector3f pos, float rotX, float rotY,
			float rotZ, float scale, float cameraOffset) {
		super(model, pos, rotX, rotY, rotZ, scale);
		this.cameraOffset = cameraOffset;

		camera3rd = new ThirdPersonCamera(new Vector3f(pos.x, pos.y
				+ cameraOffset, pos.z), this, 20, 8);
		camera1st = new FirstPersonCamera(new Vector3f(pos.x, pos.y
				+ cameraOffset, pos.z), this);
		this.currentCamera = camera1st;
	}

	public Player(TexturedModel model, int index, Vector3f pos, float rotX,
			float rotY, float rotZ, float scale) {
		super(model, index, pos, rotX, rotY, rotZ, scale);
	}

	public void move(Terrain terrain) {
		this.currentCamera.move(terrain);

		this.checkInputs();
		super.rotateBy(0,
				this.currentTurnSpeed * DisplayManager.getFrameTimeSeconds()
						* HORIZONTAL_SENSITIVITY, 0);
		float distance = this.currentSpeed
				* DisplayManager.getFrameTimeSeconds();
		float dx = (float) (distance * Math
				.sin(Math.toRadians(super.getRotY())));
		float dz = (float) (distance * Math
				.cos(Math.toRadians(super.getRotY())));
		this.moveBy(dx, 0, dz);

		// falling physics
		this.upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		this.moveBy(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		// terrain collision physics
		float terrainHeight = terrain.getHeightOfTerrainRelativeToWorld(
				this.getPos().x, this.getPos().z);
		if (getPos().y < terrainHeight) {
			this.upwardsSpeed = 0;
			getPos().y = terrainHeight;
			isInAir = false;
		}

	}

	private void jump() {
		if (!this.isInAir) {
			this.upwardsSpeed = JUMP_POWER;
			this.isInAir = true;
		}
	}

	/**
	 * 
	 */
	private void checkInputs() {

		// check for mode
		int cameraMode = this.checkCameraMode();

		switch (cameraMode) {
		case 0: // first
			rotY -= Mouse.getDX();
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {

				currentSideSpeed = this.SIDE_SPEED;

				float distance = this.currentSideSpeed
						* DisplayManager.getFrameTimeSeconds();
				float dx = (float) (distance * Math.sin(Math.toRadians(super
						.getRotY() + 90)));
				float dz = (float) (distance * Math.cos(Math.toRadians(super
						.getRotY() + 90)));
				this.moveBy(dx, 0, dz);
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {

				currentSideSpeed = -this.SIDE_SPEED;

				float distance = this.currentSideSpeed
						* DisplayManager.getFrameTimeSeconds();
				float dx = (float) (distance * Math.sin(Math.toRadians(super
						.getRotY() + 90)));
				float dz = (float) (distance * Math.cos(Math.toRadians(super
						.getRotY() + 90)));
				this.moveBy(dx, 0, dz);
			}

			else
				currentSideSpeed = 0;
			break;
		case 1: // 3rd
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				this.currentTurnSpeed = -this.TURN_SPEED;
			} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				this.currentTurnSpeed = this.TURN_SPEED;
			} else {
				this.currentTurnSpeed = 0;
			}
			break;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			this.currentSpeed = this.WALK_SPEED;
		}

		else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			this.currentSpeed = -this.WALK_SPEED;
		} else {
			this.currentSpeed = 0;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			this.jump();
		}

		// firstPerson
		/*
		 * else{ //ist person this.currentTurnSpeed = -Mouse.getDX() *
		 * Camera.HORIZONTAL_SENSITIVITY; //turn horizontally int t = 0;
		 * if(Keyboard.isKeyDown(Keyboard.KEY_D)){ t = -1; } else
		 * if(Keyboard.isKeyDown(Keyboard.KEY_A)){ t = 1; }
		 * 
		 * if(t != 0){ float tempSpeed = 2; float distance = tempSpeed *
		 * DisplayManager.getFrameTimeSeconds(); float dx = (float)(distance *
		 * Math.sin(Math.toRadians(super.getRotY() + t * 90f))); float dz =
		 * (float)(distance * Math.cos(Math.toRadians(super.getRotY() + t *
		 * 90f))); this.moveBy(dx, 0, dz); }
		 * 
		 * }
		 */

	}

	/**
	 * returns 0 if in 1st person returns 1 if in 3rd person NOTE: PLAYER WILL
	 * NOT BE RENDERED IF CAMERA IS IN FIRST PERSON MODE
	 * 
	 * @return
	 */
	private int checkCameraMode() {
		if (Keyboard.isKeyDown(Keyboard.KEY_F1)) {
			this.currentCamera = this.camera1st;
		}

		else if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
			this.currentCamera = this.camera3rd;
		}

		if (currentCamera instanceof FirstPersonCamera)
			return 0;
		else if (currentCamera instanceof ThirdPersonCamera)
			return 1;
		return -1;
	}

	public float getCameraOffset() {
		return cameraOffset;
	}

	public Camera getCurrentCamera() {
		return currentCamera;
	}
}
