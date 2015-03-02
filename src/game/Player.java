package game;

import java.util.TreeMap;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

class Player extends Droppable {
	private TreeMap<String, Integer> keyBinds;
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
		
		keyBinds = new TreeMap<String, Integer>();
		keyBinds.put("jump", Keyboard.KEY_SPACE);
		keyBinds.put("move", Keyboard.KEY_RIGHT);
	}
	
	public void update() {
		Shape localized = new MorphShape(this.getSprite());
		
		localized.setLocation(
			this.getPosition()
		);
		super.update();
		
		if (localized.intersects(this.context.getMap())) {
			Vector2f nextposition = this.getPosition();
			nextposition.x -= context.playerSpeed;
			
			localized.setLocation(
				nextposition
			);
			
			if (!localized.intersects(this.context.getMap())) {
				this.setPosition(nextposition);
			}
			else {
				localized.setLocation(
					this.getPosition()
				);
			}
		}
		
		if (localized.intersects(this.context.getMap())) {
			if (Keyboard.isKeyDown(keyBinds.get("jump"))) {
				this.setPosition(
					this.getPosition().add(
						new Vector2f(0,-2)
					)
				);
				this.setSpeed(
					this.getSpeed().add(
						new Vector2f(0, context.playerJump)
					)
				);
			}
			
			if (Keyboard.isKeyDown(keyBinds.get("move"))) {
				
			}
		}
	}
}