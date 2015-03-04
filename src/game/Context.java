package game;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Context {
	public final float playerJump = -7;
	public final float playerSpeed = 2;
	public final float gravity = 0.2f;
	public final float chunkSize = 90;
	public final float scale = 0.3f;
	
	private GameContainer gc;
	private Graphics g;
	private ArrayList<Vector2f> mapPath;
	private Polygon map;
	
	public Context(GameContainer gc, Graphics g, Polygon map) {
		super();
		this.mapPath = new ArrayList<Vector2f>();
		this.gc = gc;
		this.g = g;
		this.map = map;
	}
	
	public Context(GameContainer gc, Graphics g) {
		super();
		this.mapPath = new ArrayList<Vector2f>();
		this.gc = gc;
		this.g = g;
	}
	
	public GameContainer getGc() {
		return gc;
	}

	public Graphics getG() {
		return g;
	}

	public Polygon getMap() {
		return map;
	}
	
	public void setMap(Polygon map) {
		this.map = map;
	}
	
	public ArrayList<Vector2f> getMapPath() {
		return mapPath;
	}
	
	public void sideScroll() {
		this.setMap(
			(Polygon)this.getMap().transform(
				Transform.createTranslateTransform(
					-this.playerSpeed,
					0
				)
			)
		);
		
		for (int i = 0; i < mapPath.size(); i++) {
			mapPath.get(i).x -= this.playerSpeed;
		}
	}
	
	public void generateChunk() {
		float random = (Math.round(Math.random()) * this.chunkSize/1.5f);
		
		if (map.getMinY() < gc.getHeight()) {
			random = -random;
		}
		
		/*if (Math.random() < 0.1) {
			random = -gc.getHeight();
		}*/
		
		mapPath.add(
			new Vector2f(map.getMaxX(), map.getMinY() - random)
		);
		mapPath.add(
			new Vector2f(map.getMaxX() + this.chunkSize, map.getMinY() - random)
		);
		
		map = new Polygon();
		
		map.addPoint(
			0,
			gc.getHeight()
		);
		
		for (int i = 0; i < mapPath.size(); i++) {
			map.addPoint(
				mapPath.get(i).x,
				mapPath.get(i).y
			);
		}
		
		map.addPoint(
			map.getMaxX(),
			gc.getHeight()
		);
	}
}
