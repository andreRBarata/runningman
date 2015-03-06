package game;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Droppable extends Drawable {
	protected Vector2f speed;
	protected Polygon leftbound;
	protected Polygon rightbound;
	protected Polygon bottombound;
	
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
		
		leftbound = new Polygon(
			new float[] {
					localized.getMinX() + this.getSpeed().x,
					localized.getCenterY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		rightbound = new Polygon(
			new float[] {
					localized.getMaxX() + this.getSpeed().x,
					localized.getCenterY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		bottombound = new Polygon(
			new float[] {
					localized.getCenterX(),
					localized.getMaxY() + Math.abs(this.getSpeed().y),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		
		if (context.getMap().intersects(bottombound)) {
			this.setSpeed(
				new Vector2f(
					this.getSpeed().x/1.2f,
					this.getSpeed().y
				)
			);
		}
		
		if (context.getMap().intersects(rightbound)) {
			this.setSpeed(
				new Vector2f(
					(context.getMap().intersects(bottombound))?
						-context.playerSpeed:
						-context.playerSpeed * 1.5f,
					this.getSpeed().y
				)
			);
		}
		
		if (context.getMap().intersects(leftbound)) {
			this.setSpeed(
				new Vector2f(
					0,
					this.getSpeed().y
				)
			);
		}
		
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
