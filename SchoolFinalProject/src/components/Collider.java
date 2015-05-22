package components;

import java.awt.Rectangle;

public class Collider {

	public static boolean isColliding(Entity a, Entity b){
		Rectangle rectA = new Rectangle((int)a.T().X(), (int)a.T().Y(), (int)a.T().getWidth(), (int)a.T().getHeight());
		Rectangle rectB = new Rectangle((int)b.T().X(), (int)b.T().Y(), (int)b.T().getWidth(), (int)b.T().getHeight());
		return rectA.intersects(rectB);
	}
}
