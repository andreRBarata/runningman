package game;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Polygon;
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
	
	
	public Button(Context context, Vector2f position, String text, Shape sprite, CallBack callback) {
		super(context, position, sprite);
		this.clicked = false;
		this.callback = callback;
		this.text = text;
	}
	
	public Button(Context context, Vector2f position, String text, float width, float height, CallBack callback) {
		super(
				context,
				position,
				new Polygon(
					new float[] {
						-width,	-height,
						width,	-height,
						width,	height,
						-width,	height
					}
				)
		);
		this.clicked = false;
		this.callback = callback;
		this.text = text;
	}
	
	public boolean isClicked() {
		return clicked;
	}

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}

	public CallBack getCallback() {
		this.clicked = false;
		return callback;
	}

	public String getText() {
		return text;
	}

	public void display() {
			
		if (!clicked) {
			context.getG().setColor(new Color(255));
		}
		else {
			context.getG().setColor(new Color(20,20,200));
		}
		
		super.display();
		/*g.getFont().drawString(
			(int)super.getPosition().x - g.getFont().getWidth(text)/2,
			(int)super.getPosition().y - 10,
			text
		);*/
	}
}