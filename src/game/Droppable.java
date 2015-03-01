package game;

import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Droppable extends Drawable {
	private Vector2f speed;
	
	public Droppable(Context context, Vector2f position, Shape sprite) {
		super(context, position, sprite);
		
		speed = new Vector2f(0,0);
	}

	public void update() {
		Vector2f nextposition = new Vector2f(
			this.getPosition()
		);
		Shape localized = new MorphShape(super.getSprite());
		localized.setLocation(nextposition);
		
		if (context.getMap().intersects(localized)) {
			speed.y = -speed.y/2;
		}
		else {
			speed.add(new Vector2f(0.0f,context.gravity));
		}
		
		nextposition.add(speed);
		
		this.setPosition(
			nextposition
		);
	}

	public Vector2f getSpeed() {
		return speed;
	}

	public void setSpeed(Vector2f speed) {
		this.speed = speed;
	}
}
