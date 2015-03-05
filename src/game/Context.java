package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Context {
	public final float playerJump = -7;
	public final float gravity = 0.2f;
	public final float chunkSize = 130;
	public final float scale = 0.3f;
	
	public float playerSpeed = 2;
	
	private GameContainer gc;
	private Graphics g;
	private ArrayList<Vector2f> mapPath;
	private Polygon map;
	
	public ArrayList<Drawable> objects;
	
	public Context(GameContainer gc, Graphics g, Polygon map) {
		super();
		this.mapPath = new ArrayList<Vector2f>();
		this.gc = gc;
		this.g = g;
		this.map = map;
		this.objects = new ArrayList<Drawable>();
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
		float random = 0;
		
		if (Math.random() < 0.5) {
			random = -this.chunkSize/2f;
		}
		
		/*if (Math.random() < 0.1) {
			random = -(map.getMaxY() - map.getMinY() - 10);
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
	
	public static Image getImage(String url) {
		Image img = null;
		try {
			img = new Image("/src/Images/" + url);
			return img;
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.WARNING, null,
					ex);
			return null;
		}
	}
}
