package game;

import java.util.TreeMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

class Player extends Drawable {
	private TreeMap<String, Character> keyBinds;
	private int index;
	private char[] name;
	
	public Player() {
		super(
			new Vector2f(0,0),
			new Polygon(
				new float[] {
					-50,	-50,
					50,	-50,
					50,	50,
					-50,	50
				}
			)
		);
	}
	
	public void display(Graphics g) {
		Shape localized = new MorphShape(super.getSprite());
		
		localized.setLocation(super.getPosition());
		g.fill(localized);
	}
}