package infagen_entity;

import processing.core.PApplet;

public class Player extends Entity{

	public Player(String name, float x, float y, float w, float h) {
		super(name, x, y, w, h);
	}

	@Override
	public boolean isAlive() {
		return this.health > 0;
	}

	@Override
	public void attack(Entity e) {
		e.takeDamage(10);
	}

	@Override
	public void takeDamage(float d) {
		this.health -= d;
	}

	@Override
	public void render(PApplet parent) {
		parent.fill(255, 0, 0);
		parent.rect(this.getX(), this.getY(), this.transform.width, this.transform.height);
	}

	@Override
	public void update(float delta) {
		//System.out.println("Player is being updated!");
	}

	@Override
	public void onClose() {
		System.out.println("Good bye world!:(");
	}

}
