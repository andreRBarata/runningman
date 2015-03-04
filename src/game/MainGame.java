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
	float gameTimer;
	Context context;
	Player player;
	ArrayList<Button> buttons = new ArrayList<Button>();


	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		context = new Context(gc, gc.getGraphics(), new Polygon(new float[] {
				0, gc.getHeight() / 2, 0, gc.getHeight() }));
		
		gameTimer = 0;
		context.generateChunk();
		player = new Player(context);
		//buttons(gc,game);
	}
	
	public void buttons(GameContainer gc, StateBasedGame game) throws SlickException
	{
		
		float width;
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
				
		});
		
		buttons.add(gameOverBtn);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		Display.sync(70);
		
		gameTimer += i;
		
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
			
			g.setColor(Color.black);
			
			g.drawString(
				Integer.toString((int)gameTimer/1000),
				gc.getWidth()/2 - g.getFont().getWidth(
					Integer.toString((int)gameTimer/1000)
				)/2,
				g.getFont().getLineHeight()
			);
			
			g.setColor(new Color(255, 140, 0));
			
			player.display();
		}
		
		if (player.getPosition().x < 0) {
			
			context.getGc().pause();
			
			game.addState(new EndGame(
					(float)Math.floor(gameTimer/1000)
				)
			);
			game.enterState(2);
		}
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