package game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

/*
Button button = new Button(
	new PVector(width/2, height/2),
	"button",
	rectangle
		.scale(
			new PVector(2.5, 1)
		)
		.transpose(
			new PVector(25,10)
		),
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
	private boolean clicked;
	private String text;
	
	public Button(Vector2f position, String text, Polygon polygon, CallBack callback) {
		this.position = position;
		this.sprite = sprite;
		this.clicked = false;
		this.callback = callback;
		this.text = text;
	}
	
	public void display(Graphics g) {
		if (!clicked) {
			g.setColor(new Color(255));
		}
		else {
			g.setColor(new Color(20,20,200));
		}
		
		super.display(g);
		
		g.drawString(
			text,
			position.x,
			position.y
		);
	}
}