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
import org.newdawn.slick.geom.Rectangle;
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
	ArrayList<Button> buttons;


	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		context = new Context(gc, gc.getGraphics(), new Polygon(new float[] {
				0, gc.getHeight() / 2, 0, gc.getHeight() }));
		
		
		
		buttons = new ArrayList<Button>();

	}
	
	@Override
	   public void enter(GameContainer gc, StateBasedGame game)
	         throws SlickException {
		gameTimer = 0;
		context.generateChunk();
		player = new Player(context);
<<<<<<< HEAD
		gc.setPaused(false);
	   }
=======
		
		//buttons(gc,game);
		
	}
>>>>>>> 0d457bcc60dc7e9a646eed0f55df9e2efc81562e
	
	public void buttons(GameContainer gc, StateBasedGame game) throws SlickException {
		float width;
		float height;
		height = gc.getHeight();
		width = gc.getWidth();
		
		context = new Context(gc, gc.getGraphics());

	//	buttons = new ArrayList<Button>();

		Button gameOverBtn = new Button(context, new Vector2f(width / 2 - 75, 150f),
				Context.getImage("start.png"), Context.getImage("highStart.png"));
		gameOverBtn.onClick(() -> {
			Audio.playSound("testSample.wav");
			game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				
		});
		
		buttons.add(gameOverBtn);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		if (!gc.isPaused()) {
			
			if(isPlayerDead()){				
				game.addState(new EndGame(
						(float)Math.floor(gameTimer/1000)
					)
				);
				
				game.getState(2).init(gc, game);
				game.enterState(2);
			}
			
			Display.sync(70);
			gameTimer += i;
			
			if ((int)(gameTimer/1000) > (int)((gameTimer - i)/1000)) {
				context.playerSpeed = (float) (context.playerSpeed + 0.1 % 10);
			}
			
			context.sideScroll();
			player.update();
	
			
			if (context.getMap().getMaxX() <= gc.getWidth()) {
				context.generateChunk();
			}
				
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				context.getGc().exit();
			}
			
			if (Math.random() < 0.001) {
				context.objects.add(
					new Catchable(
						context,
						new Vector2f(
							gc.getWidth() - 40,
							gc.getHeight()/2
						),
						new Rectangle(0,0,9,9),
						"heart"
					)
				);
			}

		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
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
		
		for (Drawable drawable: context.objects) {
			drawable.display();
			
			if (drawable instanceof Droppable) {
				((Droppable) drawable).update();
			}
		}
			
			

	}

	public boolean isPlayerDead(){
		
		if (player.getPosition().x < 0) {
			return true;
		}
		return false;
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