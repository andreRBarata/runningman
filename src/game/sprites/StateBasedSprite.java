package game.sprites;

import game.Context;

import java.util.TreeMap;

import org.newdawn.slick.geom.Shape;

public class StateBasedSprite extends TreeMap<String, Sprite> implements Sprite {
	private String state;
	
	public StateBasedSprite() {
		this.state = "default";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public void draw() {
		this.get(state).draw();
	}

	@Override
	public void draw(float x, float y) {
		this.get(state).draw(x, y);
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

	@Override
	public Shape getShape() {
		return this.get(state).getShape();
	}
	
	public Sprite getDefault() {
		return this.get("default");
	}
	
	public void setDefault(Sprite sprite) {
		this.put("default", sprite);
	}

}
