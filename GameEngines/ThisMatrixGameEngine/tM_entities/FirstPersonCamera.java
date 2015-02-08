package tM_entities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import tM_terrains.Terrain;

public class FirstPersonCamera extends Camera{

	public FirstPersonCamera(Vector3f pos, Player player) {
		super(pos, player);
	}

	@Override
	public void move(Terrain terrain) {
		pos.x = this.getPlayer().getX();
		pos.y = this.getPlayer().getY() + getPlayer().getCameraOffset();
		pos.z = this.getPlayer().getZ();
		this.pitch -= Mouse.getDY();
		this.yaw = 180 - this.getPlayer().getRotY();
		this.roll = this.getPlayer().getRotZ();

	}

}
