//test
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.ShapeRenderer;
import org.newdawn.slick.geom.Transform;

public class MainGame extends BasicGame {
	Context context;
	Player player;

	public MainGame(String gamename) {
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		context = new Context(gc, gc.getGraphics(), new Polygon(new float[] {
				0, gc.getHeight() / 2, 0, gc.getHeight() }));
		context.generateChunk();
		player = new Player(context);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		Display.sync(70);
		
		context.sideScroll();
		
		if (context.getMap().getMaxX() <= gc.getWidth()) {
			context.generateChunk();
		}
			
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			context.getGc().exit();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.drawImage((new Image("/src/Tiles/testBackground.png")), 0, 0);
		
		g.setColor(new Color(70,155,70));
		
		g.fill(context.getMap());
		/*g.texture(context.getMap(),
			(new Image("/src/Tiles/testTile.png")),
			5,
			0.5f,
			true
		);*/
		
		g.setColor(new Color(255, 140, 0));
		
		player.update();
		player.display();

	}

	public static void start() {
		try {
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainGame("RunnerMan"));
			//appgc.setFullscreen(true);
			//setDisplayMode(800, 500, false);
			// appgc.setShowFPS(false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		start();
	}
}