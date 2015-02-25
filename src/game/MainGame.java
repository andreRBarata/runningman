//test
package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.MorphShape;

public class MainGame extends BasicGame {
	Polygon map;
	Player player;
	ArrayList<Button> buttons;
	
	public MainGame(String gamename) {
		super(gamename);
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		map = new Polygon(
				new float[] {
						0, gc.getHeight()/2,
						gc.getWidth(), gc.getHeight()/2,
						gc.getWidth(), gc.getHeight(),
						0, gc.getHeight()
				}
		);
		buttons = new ArrayList<Button>();
		buttons.add(
			new Button(
				new Vector2f(150,150),
				"teste",
				35,
				20,
				new CallBack() {
					public void run() {
						System.out.println("teste");
					}				
				}
			)
		);
		player = new Player();
	}

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if (Mouse.isButtonDown(0)) {
			int x = Mouse.getX();
	        int y = gc.getHeight() - Mouse.getY();
	        
	        for (Button button: buttons) {
	    		Shape spriteInSpace = new MorphShape(
	    			button.getSprite()
	    		);
	    		spriteInSpace.setLocation(button.getPosition());
	    		
	    		if (spriteInSpace.contains(x,y)) {
	    			button.setClicked(true);
	    			button.getCallback().run();
	    		}
	    	}
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		g.setBackground(Color.white);
		
		for (Button button: buttons) {
			button.display(g);
		}
		
		g.setColor(Color.green);
		g.fill(map);
		g.setColor(Color.black);
		//player.display(g);
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