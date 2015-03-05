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
						16,44,
						20,43,
						15,39,
						15,23,
						20,19,
						31,19,
						34,22,
						36,25,
						36,20,
						35,9,
						37,7,
						41,4,
						44,7,
						46,9,
						46,16,
						48,17,
						47,27,
						45,29,
						42,23,
						40,23,
						41,26,
						43,31,
						44,33,
						42,34,
						42,36,
						40,37,
						40,40,
						45,44,
						45,46,
						39,46,
						36,44,
						28,44,
						25,43,
						25,46,
						18,46,
						15,45
				}
			)
		);
		
		keyBinds = new TreeMap<String, Integer>();
		keyBinds.put("jump", Keyboard.KEY_SPACE);
		keyBinds.put("right", Keyboard.KEY_RIGHT);
		keyBinds.put("left", Keyboard.KEY_LEFT);
	}
	
	public void update() {
		Shape localized = new MorphShape(this.getSprite());
		localized.setLocation(
			this.getPosition()
		);
		
		Polygon leftbound = new Polygon(
			new float[] {
					localized.getMinX() + this.getSpeed().x,
					localized.getCenterY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		Polygon rightbound = new Polygon(
			new float[] {
					localized.getMaxX() + this.getSpeed().x,
					localized.getCenterY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		Polygon bottombound = new Polygon(
			new float[] {
					localized.getCenterX(),
					localized.getMaxY() + this.getSpeed().y,
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		
		if (Keyboard.isKeyDown(keyBinds.get("right"))) {
			if (!context.getMap().intersects(rightbound)) {
				this.setSpeed(
					new Vector2f(
						context.playerSpeed/2,
						this.getSpeed().y
					)
				);
			}
		}
		
		if (Keyboard.isKeyDown(keyBinds.get("left"))) {
			if (!context.getMap().intersects(leftbound)) {
				this.setSpeed(
					new Vector2f(
						-context.playerSpeed/2,
						this.getSpeed().y
					)
				);
			}
		}
		
		if (context.getMap().intersects(bottombound)) {
			if (Keyboard.isKeyDown(keyBinds.get("jump"))) {
				Audio.playSound("jump.wav");
				this.setPosition(
					this.getPosition().add(
						new Vector2f(0,-5)
					)
				);
				this.setSpeed(
					new Vector2f(this.getSpeed().x, context.playerJump)
				);
			}
		}	
		
		if (this.getPosition().x > context.getGc().getWidth()/2) {
			if (this.getSpeed().x > 0) {
				this.setSpeed(
					new Vector2f(
						0,
						this.getSpeed().y
					)
				);
			}
		}
		
		super.update();
	}
}