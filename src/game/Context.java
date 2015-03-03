package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class Context {
	public final float playerJump = -5;
	public final float playerSpeed = 2;
	public final float gravity = 0.2f;
	public final float chunkSize = 100;
	public final float scale = 0.3f;
	
	private GameContainer gc;
	private Graphics g;
	private Polygon mapPath;
	private Polygon map;
	
	public Context(GameContainer gc, Graphics g, Polygon map) {
		super();
		this.mapPath = new Polygon();
		this.gc = gc;
		this.g = g;
		this.map = map;
	}
	
	public Context(GameContainer gc, Graphics g) {
		super();
		this.mapPath = new Polygon();
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
	
	public Polygon getMapPath() {
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
		
		mapPath = (Polygon)mapPath.transform(
			Transform.createTranslateTransform(
					-this.playerSpeed,
				0
			)
		);
	}
	
	public void generateChunk() {
		float random = (Math.round(Math.random()) * this.chunkSize/2);
		
		if (Math.random() < 0.5 || map.getMinY() < gc.getHeight()/2) {
			random = -random;
		}
		
		mapPath.addPoint(
			map.getMaxX(), map.getMinY() - random
		);
		mapPath.addPoint(
			map.getMaxX() + this.chunkSize, map.getMinY() - random
		);
		
		map = new Polygon();
		
		map.addPoint(
			0,
			gc.getHeight()
		);
		
		for (int i = 0; i < mapPath.getPointCount(); i++) {
			map.addPoint(
				mapPath.getPoint(i)[0],
				mapPath.getPoint(i)[1]
			);
		}
		
		map.addPoint(
			mapPath.getMaxX(),
			gc.getHeight()
		);
	}
}
