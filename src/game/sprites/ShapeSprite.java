package game.sprites;

import game.Context;
import org.newdawn.slick.geom.Shape;

public class ShapeSprite implements Sprite {
	private Shape shape;
	private Context context;
	
	public Shape getShape() {
		return shape;
	}

	public ShapeSprite(Context context, Shape shape) {
		this.context = context;
		this.shape = shape;
	}

	@Override
	public void draw() {
		context.getG().draw(shape);
	}

	@Override
	public void draw(float x, float y) {
		context.getG().pushTransform();
		
		context.getG().translate(
			x,
			y
		);
		
		context.getG().fill(shape);
		
		context.getG().popTransform();
	}

	@Override
	public float getMinX() {
		return shape.getMinX();
	}

	@Override
	public float getMinY() {
		return shape.getMinY();
	}

	@Override
	public float getMaxX() {
		return shape.getMaxX();
	}

	@Override
	public float getMaxY() {
		return shape.getMaxX();
	}

	@Override
	public float getCenterX() {
		return shape.getCenterX();
	}

	@Override
	public float getCenterY() {
		return shape.getCenterX();
	}

}
