//test
package game;

import java.util.ArrayList;
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
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainGame extends BasicGameState {
	Context context;
	Player player;
	ArrayList<Button> buttons;


	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		context = new Context(gc, gc.getGraphics(), new Polygon(new float[] {
				0, gc.getHeight() / 2, 0, gc.getHeight() }));
		context.generateChunk();
		player = new Player(context);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		Display.sync(70);
		
		context.sideScroll();
		
		player.update();

		
		if (context.getMap().getMaxX() <= gc.getWidth()) {
			context.generateChunk();
		}
			
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			context.getGc().exit();
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		if (!gc.isPaused()) {
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
			
			player.display();
		}
		
		if (player.getPosition().x < 0) {
			context.getG().setBackground(Color.white);
			context.getG().setColor(Color.black);
			
			context.getG().drawString(
					
				"End",
			
				
				context.getGc().getWidth()/2,
				context.getGc().getHeight()/2
				
				
			);
			context.getGc().pause();
			
	/*		float width;
			float height;
			height = gc.getHeight();
			width = gc.getWidth();
			
			context = new Context(gc, gc.getGraphics());

		//	buttons = new ArrayList<Button>();

			Button gameOverBtn = new Button(context, new Vector2f(width / 2 - 75, 150f),
					MainMenu.GetImage("start.png"), MainMenu.GetImage("highStart.png"));
			gameOverBtn.onClick(() -> {
				Audio.playSound("testSample.wav");
				game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				
				buttons.add(gameOverBtn);
			});*/
		}
		
	/*	float width;
		float height;
		height = gc.getHeight();
		width = gc.getWidth();
		
		context = new Context(gc, gc.getGraphics());

		buttons = new ArrayList<Button>();

		Button startBtn = new Button(context, new Vector2f(width / 2 - 75, 150f),
				MainMenu.GetImage("start.png"), MainMenu.GetImage("highStart.png"));
		startBtn.onClick(() -> {
			Audio.playSound("testSample.wav");
			game.enterState(1, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		});*/
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}


//	public static void start() {
//		try {
//			AppGameContainer appgc;
//			appgc = new AppGameContainer(new MainGame("RunnerMan"));
//			appgc.setFullscreen(false);
//			appgc.setDisplayMode(800, 500, false);
//			// appgc.setShowFPS(false);
//			appgc.start();
//		} catch (SlickException ex) {
//			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}

//	public static void main(String[] args) {
//		start();
//	}
}