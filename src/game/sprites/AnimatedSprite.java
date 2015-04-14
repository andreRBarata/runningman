package game.sprites;

import game.Context;
import game.sprites.Sprite;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.newdawn.slick.geom.Shape;

public class AnimatedSprite extends ArrayList<Sprite> implements Sprite {
	private static final long serialVersionUID = 1L;
	private int loop;
	private int delta;
	private int maxDelta;

	public AnimatedSprite(ArrayList<Sprite> sprites, int maxDelta) {
		super(sprites);
		
		this.loop = 0;
		this.delta = 0;
		this.maxDelta = maxDelta;
	}
	
	public AnimatedSprite(int maxDelta) {
		super();
		
		this.loop = 0;
		this.delta = 0;
		this.maxDelta = maxDelta;
	}
	
	@Override
	public void draw() {
		draw(0, 0);
	}

	@Override
	public void draw(float x, float y) {
		this.get(loop).draw(x, y);
		
		if (delta == maxDelta) { 
			loop = (loop + 1) % this.size();
			delta = 0;
		}
		
		delta++;
	}

	public int getMaxDelta() {
		return maxDelta;
	}

	public void setMaxDelta(int maxDelta) {
		this.maxDelta = maxDelta;
	}

	@Override
	public float getMinX() {
		return this.getShape().getMinX();
	}

	@Override
	public float getMinY() {
		return this.getShape().getMinY();
	}

	@Override
	public float getMaxX() {
		return this.getShape().getMaxX();
	}

	@Override
	public float getMaxY() {
		return this.getShape().getMaxY();
	}

	@Override
	public float getCenterX() {
		return this.getShape().getCenterX();
	}

	@Override
	public float getCenterY() {
		return this.getShape().getCenterY();
	}
	
	public Shape getShape() {
		return this.get(loop).getShape();
	}
}
