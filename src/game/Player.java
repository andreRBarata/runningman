package game;

import game.sprites.AnimatedSprite;
import game.sprites.ImgSprite;
import game.sprites.Sprite;

import java.util.ArrayList;
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
	private int index;
	private char[] name;
	private float timer;
	
	public Player(Context context) {
		super(
			new Vector2f(
				context.getGc().getWidth()/2,
				context.getGc().getHeight()/3
			),
			new AnimatedSprite(
				30
			)
		);
		this.context = context;
		
		((AnimatedSprite)this.getSprite()).add(
				new ImgSprite(context, Context.getImage("player01.png").getScaledCopy(0.5f))
		);
		((AnimatedSprite)this.getSprite()).add(
				new ImgSprite(context, Context.getImage("player02.png").getScaledCopy(0.5f))
		);
		
		timer = 0;
		keyBinds = new TreeMap<String, Integer>();
		keyBinds.put("jump", Keyboard.KEY_SPACE);
		keyBinds.put("right", Keyboard.KEY_RIGHT);
		keyBinds.put("left", Keyboard.KEY_LEFT);
	}
	
	public void update() {
		Shape localized = new MorphShape(this.getSprite().getShape());
		localized.setLocation(
			this.getPosition()
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
		bottombound = new Polygon(
			new float[] {
					localized.getCenterX(),
					localized.getMaxY(),
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
	
	public void jump() {
		if (context.getMap().intersects(bottombound)) {
			Audio.playSound("jump.wav", MainMenu.mute);
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
	
	public void display() {
		this.getSprite().draw(
			this.getPosition().x,
			this.getPosition().y
		);
	}

	public TreeMap<String, Integer> getKeyBinds() {
		return keyBinds;
	}
}