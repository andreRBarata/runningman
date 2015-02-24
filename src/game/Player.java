package game;

import java.util.TreeMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

class Player implements Entity {
	private Vector2f position;
	private Shape sprite;
	
	private TreeMap<String, Character> keyBinds;
	private int index;
	private char[] name;
	
	Player() {
		this.sprite = new Polygon(
			new float[] {
				-50,	-50,
				50,	-50,
				50,	50,
				-50,	50
			}
		);
		this.position = new Vector2f(0,0);
	}
	
	public void display(Graphics g) {
		Shape localized = new MorphShape(sprite);
		
		localized.setLocation(position);
		g.fill(localized);
	}
	
	public void update() {
	}
}