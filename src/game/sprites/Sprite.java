package game.sprites;

import org.newdawn.slick.geom.Shape;

public interface Sprite {
	public void draw();
	public void draw(float x, float y);
	
	public float getMinX();
	public float getMinY();
	
	public float getMaxX();
	public float getMaxY();
	
	public float getCenterX();
	public float getCenterY();
	
	public Shape getShape();
}
