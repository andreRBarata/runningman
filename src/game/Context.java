package game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Shape;

public class Context {
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
