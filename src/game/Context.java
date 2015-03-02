package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Context {
	public final float playerJump = -5;
	public final float playerSpeed = 2;
	public final float gravity = 0.2f;
	public final float chunkSize = 25;
	
	private GameContainer gc;
	private Graphics g;
	private Shape map;
	
	public GameContainer getGc() {
		return gc;
	}

	public Graphics getG() {
		return g;
	}

	public Shape getMap() {
		return map;
	}
	
	public void setMap(Shape map) {
		this.map = map;
	}
	
	public void sideScroll() {
		this.setMap(
				this.getMap().transform(
					Transform.createTranslateTransform(-this.playerSpeed,0)
				)
		);
	}
	
	public void generateChunk() {
		
	}
	
	public Context(GameContainer gc, Graphics g, Shape map) {
		super();
		this.gc = gc;
		this.g = g;
		this.map = map;
	}
	
	public Context(GameContainer gc, Graphics g) {
		super();
		this.gc = gc;
		this.g = g;
	}	
}
