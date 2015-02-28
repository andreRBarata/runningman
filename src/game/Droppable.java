package game;

import org.newdawn.slick.geom.MorphShape;
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
			speed.y = -speed.y;
		}
		
		speed.add(new Vector2f(0.0f,0.2f));
		
		nextposition.add(speed);
		
		this.setPosition(
			nextposition
		);
	}
}
