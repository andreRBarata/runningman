package game;

import java.awt.Font;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.Position;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.util.FontUtils;

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
	private Image img;
	private Image hoverImg;
	private float width, height;
	private float scaleWidth, scaleHeight;

	public Button(Context context, Vector2f position, String text,
			CallBack callback) {
		super(context, position, new Polygon(new float[] { 0, 0,
				context.getG().getFont().getWidth(text), 0,
				context.getG().getFont().getWidth(text),
				context.getG().getFont().getHeight(text), 0,
				context.getG().getFont().getHeight(text) }));
		this.clicked = false;
		this.callback = callback;
		this.text = text;
	}

	public Button(Context context, Vector2f position, Image img, Image hoverImg) {
		super(context, position, null);
		this.img = img;
		this.hoverImg = hoverImg;
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.scaleWidth = width * context.scale;
		this.scaleHeight = height * context.scale;
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
		return new Rectangle(getPosition().x, getPosition().y, scaleWidth,
				scaleHeight).contains(point.x, point.y);
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

	public void drawImage(GameContainer gc, org.newdawn.slick.Graphics g,
			float x, Image img) {

		img.draw(getPosition().x, getPosition().y, scaleWidth, scaleHeight);
	}

	public void display() {

		if (img != null && !img.equals("") && !isMouseOver())
			drawImage(context.getGc(), context.getG(), 4, img);
		else if (hoverImg != null && !hoverImg.equals("") && isMouseOver())
			drawImage(context.getGc(), context.getG(), 4, hoverImg);

		if (!clicked) {
			context.getG().setColor(new Color(255));
		} else {
			context.getG().setColor(new Color(20, 20, 200));
		}

		/*
		 * context.getG() .getFont() .drawString((int) super.getPosition().x,
		 * (int) super.getPosition().y - 10, text);
		 */
	}

}