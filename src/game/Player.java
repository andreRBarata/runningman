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
	private Image img1;
	private Image img2;
	private int index;
	private char[] name;
	private float timer;
	
	public Player(Context context) {
		super(
			context,
			new Vector2f(
				context.getGc().getWidth()/2,
				0
			),
			new Polygon(
				//new float[] {-30,-30,30,-30,30,30,-30,30}
			)
		);
		
		img1 = Context.getImage("player01.png").getScaledCopy(0.5f);
		img2 = Context.getImage("player02.png").getScaledCopy(0.5f);
		
		this.setSprite(
			new Polygon(
				new float[] {
					0,0,
					img1.getWidth(),0,
					img1.getWidth(), img1.getHeight(),
					0, img1.getHeight()
				}
			)
		);
		
		
		timer = 0;
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
				if (this.getSpeed().y <= 0) {
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
		
		if (timer < 15) {
			img2.draw();
		}
		else {
			img1.draw();
			
		}

		context.getG().popTransform();
		
		timer = ((timer + 1) % 30);
	}
}