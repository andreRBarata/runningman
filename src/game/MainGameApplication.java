package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainGameApplication extends StateBasedGame {

	private static AppGameContainer appgc;

	public MainGameApplication(String title) {
		super(title);
	}

	public static void main(String[] args) {
		
		/*	 GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
		.getDefaultScreenDevice();
 int screenWidth = gd.getDisplayMode().getWidth();
 int screenHeight = gd.getDisplayMode().getHeight();*/
		
//		Audio.playSound("testMusic.wav");
		try {
			appgc = new AppGameContainer(
					new MainGameApplication("Turtle Dash"));
			appgc.setDisplayMode(800, 600, false);
			// appgc.setShowFPS(false);
			appgc.start();
		} catch (SlickException ex) {
			Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		this.addState(new MainMenu());
		this.addState(new MainGame());
	}


}