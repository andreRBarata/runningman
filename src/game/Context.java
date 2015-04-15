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
	public final float chunkSize = 128;
	public final float scale = 0.3f;
<<<<<<< HEAD
	static boolean mute = false;
=======
	public static boolean mute = false;
	public static int spriteAnimOne = 0;
	public static int spriteAnimTwo = 2;
>>>>>>> 8f937c6bf9b99d38bcfb58b71c21d9694e22adcb
	
	public static float playerSpeed = 2;
	
	private GameContainer gc;
	private Graphics g;
	private ArrayList<Vector2f> mapPath;
	private Polygon map;
	
	public ArrayList<Drawable> objects;
	
	public Context(GameContainer gc, Graphics g, Polygon map) {
		this.mapPath = new ArrayList<Vector2f>();
		this.gc = gc;
		this.g = g;
		this.map = map;
		this.objects = new ArrayList<Drawable>();
	}
	
	public Context(GameContainer gc, Graphics g) {
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
		
		if (Math.random() < 0.1) {
			random = -(this.chunkSize/2);
		}
		
		mapPath.add(
			new Vector2f(map.getMaxX() + 7, map.getMinY() - random)
		);
		mapPath.add(
			new Vector2f(map.getMaxX() + this.chunkSize - 7, map.getMinY() - random)
		);
		
		map = new Polygon();
		
		map.addPoint(
			0,
			gc.getHeight()*2
		);
		
		for (int i = 0; i < mapPath.size(); i++) {
			map.addPoint(
				mapPath.get(i).x,
				mapPath.get(i).y
			);
		}
		
		map.addPoint(
			map.getMaxX(),
			gc.getHeight()*2
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
	
	public static Image getSpriteImg(String url) {
		Image img = null;
		try {
			img = new Image("/src/SpriteImages/" + url);
			return img;
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.WARNING, null,
					ex);
			return null;
		}
	}
	
	public void setImage(GameContainer gc, org.newdawn.slick.Graphics g,
			final String url, float x, float y, float h, float w) throws SlickException {

		Image img = new Image("/src/Images/" + url);

		img.draw(x, y, h, w);
	}
	
	public void title(GameContainer gc, org.newdawn.slick.Graphics g,
			final String url) throws SlickException {

		Image img = new Image("/src/Tiles/" + url);

		// MainMenu.class.getResourceAsStream("/Samples/" + url);
		float imgWidth = img.getWidth();
		float imgHeight = img.getHeight();
		float scaleWidth = imgWidth * scale + 20;
		float scaleHeight = imgHeight * scale;

		img.draw(0, 0, scaleWidth * 3, scaleHeight * 3);
	}
	
	public void background(GameContainer gc, org.newdawn.slick.Graphics g,
			final String url) throws SlickException {

		Image img = new Image("/src/Tiles/" + url);

		// MainMenu.class.getResourceAsStream("/Samples/" + url);
		float imgWidth = img.getWidth();
		float imgHeight = img.getHeight();
		float scaleWidth = imgWidth * scale + 20;
		float scaleHeight = imgHeight * scale;

		img.draw(0, 0, scaleWidth * 1.8f, scaleHeight * 2.7f);
	}

}
