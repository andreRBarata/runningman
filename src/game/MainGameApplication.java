package game;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainGameApplication extends StateBasedGame {

	private static AppGameContainer appgc;

	public MainGameApplication(String title) {
		super(title);
	}

	public static void main(String[] args) {
		try {
			 appgc = new AppGameContainer(
					new MainGameApplication("RunnerMan"));
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
		addState(new MainMenu());
		addState(new MainGame());
	}


}