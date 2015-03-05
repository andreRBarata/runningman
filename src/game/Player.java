package game;

import java.util.TreeMap;

import javafx.scene.shape.Rectangle;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.MorphShape;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

class Player extends Droppable {
	private TreeMap<String, Integer> keyBinds;
	private Image imgFill;
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
				//new float[] {-30,-30,30,-30,30,30,-30,30}
				new float[] {4.5f, 15, 6.5f, 10, 12.5f, 10, 14.5f, 10, 16.5f, 13, 16.5f, 16, 19.5f, 19, 20.5f, 24, 18.5f, 26, 15.5f, 29, 12.5f, 28, 10.5f, 30, 13.5f, 32, 14.5f, 35, 14.5f, 38, 14.5f, 41, 10.5f, 41, 9.5f, 42, 10.5f, 44, 12.5f, 46, 14.5f, 47, 12.5f, 49, 6.5f, 49, 5.5f, 43, -1.5f, 44, -0.5f, 46, 0.5f, 49, -6.5f, 49, -9.5f, 45, -8.5f, 42, -6.5f, 38, -8.5f, 34, -6.5f, 30, -2.5f, 26, 0.5f, 24, -1.5f, 21, 2.5f, 16}
			)
		);
		
		imgFill = Context.getImage("player.png");
		
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
	
	public void display() {
		context.getG().pushTransform();
		
		context.getG().translate(
			this.getPosition().x,
			this.getPosition().y
		);
		context.getG().fill(this.getSprite());

		context.getG().popTransform();
		
	}
}