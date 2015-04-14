package game;

import game.sprites.Sprite;


import org.newdawn.slick.geom.Vector2f;

public class Drawable {
	protected Context context;
	private Vector2f position;
	private Sprite sprite;	
	
	public Drawable(Vector2f position, Sprite sprite) {
		this.position = position;
		this.sprite = sprite;
	}
	
	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public void display() {
		sprite.draw(
			position.x, 
			position.y
		);
	}
	
}
