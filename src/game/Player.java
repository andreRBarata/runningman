package game;

import java.util.TreeMap;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

class Player extends Droppable {
	private TreeMap<String, Character> keyBinds;
	private int index;
	private char[] name;
	
	public Player(Context context) {
		super(
			context,
			new Vector2f(
				context.getGc().getWidth()/2,
				0
			),
			new Polygon(
				new float[] {
						14,4,
						16,6,
						16,8,
						18,10,
						20,11,
						19,14,
						21,15,
						23,18,
						25,20,
						25,24,
						25,26,
						26,28,
						27,30,
						25,33,
						20,31,
						18,32,
						19,35,
						11,35,
						13,30,
						11,26,
						7,28,
						4,22,
						9,22,
						11,21,
						10,17,
						6,16,
						5,11,
						7,10,
						9,5,
						11,2
				}
			)
		);
	}
	
	public void display() {
		Shape localized = new MorphShape(super.getSprite());
		localized.setLocation(super.getPosition());
		
		context.getG().fill(localized);
	}
}