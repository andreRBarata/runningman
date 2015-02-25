package game;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Drawable {
	protected Vector2f position;
	protected Shape sprite;	
	
	public Drawable(Vector2f position, Shape sprite) {
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
	
	public void display(Graphics g) {
		Shape localized = new MorphShape(sprite);
		
		localized.setLocation(
			position
		);
		g.fill(localized);
	}
	
}
