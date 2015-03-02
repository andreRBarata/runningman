//test
package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Transform;

public class MainGame extends BasicGame {
	Context context;
	Player player;
	
	public MainGame(String gamename) {
		super(gamename);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		context = new Context(
			gc,
			gc.getGraphics(),
			new Polygon(
					new float[] {
							0, gc.getHeight()/2,
							0, gc.getHeight()
					}
			)
		);
		context.generateChunk();
		player = new Player(context);
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		context.sideScroll();
		
		if (context.getMap().getMaxX() <= gc.getWidth()) {
			context.generateChunk();
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		
		g.setColor(Color.green);
		g.fill(context.getMap());
		g.setColor(Color.black);
		player.update();
		player.display();
		
		
	}

	public static void main(String[] args) {
		try	{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new MainGame("RunnerMan"));
			appgc.setDisplayMode(800, 500, false);
			//appgc.setShowFPS(false);
			appgc.start();
		}
		catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}