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
		keyBinds.put("right", Keyboard.KEY_RIGHT);
		keyBinds.put("left", Keyboard.KEY_LEFT);
	}
	
	public void update() {
		Shape localized = new MorphShape(this.getSprite());
		localized.setLocation(
			this.getPosition()
		);
		
		Polygon fullbound = new Polygon(
			new float[] {
					localized.getMinX(),
					localized.getCenterY(),
					localized.getMaxX(),
					localized.getCenterY()
			}
		);
		
		Polygon leftbound = new Polygon(
			new float[] {
					localized.getMinX(),
					localized.getCenterY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		Polygon rightbound = new Polygon(
			new float[] {
					localized.getMaxX(),
					localized.getCenterY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		Polygon bottombound = new Polygon(
			new float[] {
					localized.getCenterX(),
					localized.getMaxY(),
					localized.getCenterX(),
					localized.getCenterY()
			}
		);
		
		super.update();
		
		if (context.getMap().intersects(rightbound)) {
			this.setSpeed(
				new Vector2f(
					-context.playerSpeed * 1.5f,
					this.getSpeed().y
				)
			);
		}
		
		if (context.getMap().intersects(leftbound)) {
			this.setSpeed(
				new Vector2f(
					0,
					this.getSpeed().y
				)
			);
		}
		
		if (context.getMap().intersects(bottombound)) {
			if (Keyboard.isKeyDown(keyBinds.get("jump"))) {
				this.setPosition(
					this.getPosition().add(
						new Vector2f(0,-5)
					)
				);
				this.setSpeed(
					this.getSpeed().add(
						new Vector2f(0, context.playerJump)
					)
				);
			}
			
			
			
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
			
			if (this.getPosition().x > context.getGc().getWidth()/2) {
				this.setSpeed(
					new Vector2f(
						0,
						this.getSpeed().y
					)
				);
			}
			
			if (!localized.intersects(this.context.getMap())) {
				this.setSpeed(
					new Vector2f(
						this.getSpeed().x/2,
						this.getSpeed().y
					)
				);
			}
			
		}
	}
}