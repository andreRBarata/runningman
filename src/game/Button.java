package game;

import java.awt.Font;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.MorphShape;
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
	private int timer;
	private boolean clicked;
	
	
	public Button(Vector2f position, String text, Shape sprite, CallBack callback) {
		super(position, sprite);
		this.clicked = false;
		this.callback = callback;
		this.text = text;
		this.timer = 0;
	}
	
	public Button(Vector2f position, String text, float width, float height, CallBack callback) {
		super(
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
		this.timer = 0;
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

	public void display(Graphics g) {
		if (!clicked) {
			g.setColor(new Color(255));
			if (timer != 0) {
				timer--;
				System.out.println("teste4" + timer);
			}
			else {
				clicked = false;
			}
		}
		else {
			g.setColor(new Color(20,20,200));
			if (timer == 0) {
				timer = 10000;
			}
		}
		
		super.display(g);
		FontUtils.drawCenter(
			new TrueTypeFont(
					new Font("Verdana", Font.BOLD, 20),
					false
			),
			text,
			(int)super.getPosition().x,
			(int)super.getPosition().y - 10,
			0,
			Color.black
		);
	}
}