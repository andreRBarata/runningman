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
	BackgroundObject skyBackground;
	BackgroundObject grassBackground;
	float halfWidth;
	float halfHeight;
	boolean paused = false;
	
	@Override
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		context = new Context(gc, gc.getGraphics(), new Polygon(new float[] {
				0, gc.getHeight() / 1.5f, 0, gc.getHeight() }));
		
		
		halfWidth = gc.getWidth() / 2;
		halfHeight = gc.getHeight() / 2;
		
		buttons = new ArrayList<Button>();

	}
	
	@Override
	   public void enter(GameContainer gc, StateBasedGame game)
	         throws SlickException {
		gameTimer = 0;
		context.generateChunk();
		player = new Player(context);
		context.playerSpeed = 2f;
		skyBackground = new BackgroundObject(gc, new Image("/src/Tiles/sky.png"), new Vector2f(0f, 0f), 1f);
		grassBackground = new BackgroundObject(gc, new Image("/src/Tiles/grass.png"), new Vector2f(0f, 0f), 3f);

		gc.setPaused(false);
	   }

		
		//buttons(gc,game);	

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
			if(context.mute)
			Audio.playSound("testSample.wav",MainMenu.mute);
			game.enterState(0, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
				
		});
		
		buttons.add(gameOverBtn);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
		if (!gc.isPaused()) {
			
			skyBackground.update();
			grassBackground.update();
			
			if(isPlayerDead()){				
				game.addState(new EndGame(
						(float)Math.floor(gameTimer/1000)
					)
				);
				
				game.getState(2).init(gc, game);
				game.enterState(2);
			}
			
			Display.sync(70);
			player.update();
			gameTimer += i;
			
			if ((int)(gameTimer/1000) > (int)((gameTimer - i)/1000)) {
				context.playerSpeed = (float) (context.playerSpeed + 0.1 % 10);
			}
			
			context.sideScroll();
			
	
			
			if (context.getMap().getMaxX() <= gc.getWidth()) {
				context.generateChunk();
			}
				
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				context.getGc().exit();
			}
			
			/*if (Math.random() < 0.001) {
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
			}*/

		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		
	//	g.drawImage((new Image("/src/Tiles/testBackground.png")), 0, 0);
		skyBackground.render(gc, game, g);
		grassBackground.render(gc, game, g);
		g.setColor(new Color(70,155,70));
		
		g.fill(context.getMap());
		
		g.setColor(Color.black);
		
		g.drawString(
				"Score",
				gc.getWidth()/2 - g.getFont().getWidth(
					"Score"
				)/2,
				g.getFont().getLineHeight() - 15
			);
		
		g.drawString(
			Integer.toString((int)gameTimer/1000),
			gc.getWidth()/2 - g.getFont().getWidth(
				Integer.toString((int)gameTimer/1000)
			)/2,
			g.getFont().getLineHeight() + 7
		);
			
		g.setColor(new Color(255, 140, 0));
		
		player.display();
		
		for (Drawable drawable: context.objects) {
			drawable.display();
			
			if (drawable instanceof Droppable) {
				((Droppable) drawable).update();
			}
		}
		
		if(paused)
		{
			g.setColor(Color.red);
			g.drawString("Paused", halfWidth - 28, halfHeight - 150);
		}
	}

	public void keyPressed(int key, char c) {
		if (player.getKeyBinds().get("jump") == key) {
			player.jump();
		}
	}
	
	public void keyReleased(int key, char c) {
		keyPressed(key, c);
		
		if (key == Keyboard.KEY_P) {
			context.getGc().setPaused(!context.getGc().isPaused());
			//if(context.getGc().isMusicOn())
			Audio.playSound("testSample.wav",MainMenu.mute);
			
			paused = !paused;
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