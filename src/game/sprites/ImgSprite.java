package game.sprites;

import game.Context;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class ImgSprite implements Sprite {
	private Image img;
	private Context context;
	
	public ImgSprite(Context context, Image img) {
		this.context = context;
		this.img = img;
		
	}

	@Override
	public void draw() {
		context.getG().drawImage(img, 0, 0);
	}

	@Override
	public void draw(float x, float y) {
		context.getG().drawImage(img, x, y);
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
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
		return new Rectangle(0, 0, img.getWidth(),
				img.getHeight());
	}
}
