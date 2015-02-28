package game;

import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Drawable {
	protected Context context;
	private Vector2f position;
	private Shape sprite;	
	
	public Drawable(Context context, Vector2f position, Shape sprite) {
		this.context = context;
		this.position = position;
		this.sprite = sprite;
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Shape getSprite() {
		return sprite;
	}

	public void setSprite(Shape sprite) {
		this.sprite = sprite;
	}
	
	public void display() {
		Shape localized = new MorphShape(sprite);
		
		localized.setLocation(
			position
		);
		context.getG().fill(localized);
	}
	
}
