package game;

import game.sprites.ImgSprite;
import game.sprites.StateBasedSprite;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/*
 Button button = new Button(
 new PVector(width/2, height/2),
 "button",
 50,
 50,
 new CallBack() {
 public void run() {
 println("teste");
 }
 }
 );

 buttons.add(button);
 */

public class Button extends Drawable {
	private CallBack callback;
	private String text;
	private boolean clicked;
	private boolean hover;
	private float width, height;

	public Button(Context context, Vector2f position, String text,
			CallBack callback) {
		super(position, new StateBasedSprite());
		this.clicked = false;
		this.text = text;
	}

	public Button(Context context, Vector2f position, Image img, Image hoverImg) {
		super(position, new StateBasedSprite());
		
		((StateBasedSprite)this.getSprite()).setDefault(
				new ImgSprite(context, img.getScaledCopy(context.scale))
		);
		((StateBasedSprite)this.getSprite()).put("hover",
				new ImgSprite(context, hoverImg.getScaledCopy(context.scale))
		);
		this.width = img.getWidth();
		this.height = img.getHeight();
		
	}

	public void onClick(CallBack callback) {
		this.callback = callback;
	}

	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		if (!this.clicked && clicked)
			callback.run();
		this.clicked = clicked;
	}

	public boolean isMouseOver() {
		return hover;
	}

	public void SetMouseOver(boolean isMouseOver) {
		this.hover = isMouseOver;
	}

	public boolean containsPoint(Vector2f point) {
		Shape shape = this.getSprite().getShape();
		shape.setLocation(this.getPosition());
		
		return shape.contains(point.x, point.y);
	}

	public boolean containsPoint(float x, float y) {
		return containsPoint(new Vector2f(x, y));
	}

	public CallBack getCallback() {
		this.clicked = false;
		return callback;
	}

	public String getText() {
		return text;
	}

	public void display() {

		if (!isMouseOver()) {
			((StateBasedSprite)this.getSprite()).setState("default");
		}
		else {
			((StateBasedSprite)this.getSprite()).setState("hover");
		}
		
		this.getSprite().draw(getPosition().x, getPosition().y);

	}

}