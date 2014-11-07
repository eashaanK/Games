package physics;

public class PhysicsBody {

	public float currentX, initialX;
	public float currentY, initialY;
	public float currentVx, initialVx;
	public float currentVy, initialVy;
	public float gravityX, gravityY;
	public float time = 0, timeInc = 0.1f;
	public boolean dynamic = false;
	public int xDirection = 1, yDirection = 1;

	public PhysicsBody(){
		gravityY = 10;
	}
	
	public PhysicsBody(float x, float y, float vx, float vy, float gravityX, float gravityY, float timeInc) {
		this.initialX = x;
		this.initialY = y;
		this.initialVx = vx;
		this.initialVy = vy;
		this.gravityX = gravityX;
		this.gravityY = gravityY;
		this.timeInc = timeInc;
	}

	public void update() {
		if (dynamic) {
			time+= timeInc;
			// velocity
			currentVx = this.xDirection * (gravityX * time + initialVx);
			currentVy = this.yDirection * (gravityY * time + initialVy);
			// position
			currentX = (currentVx * time + initialX);
			currentY = (currentVy * time + initialY);
		}
	}

}
