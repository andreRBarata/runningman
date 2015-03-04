package game;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {
	Context context;
	int height;
	int width;
	int divideBy = 2;
	int halfWidth;
	int halfHeight;
	float scale = 0.3f;
	ArrayList<Button> mainButtons = new ArrayList<Button>();
	ArrayList<Button> optionsButtons = new ArrayList<Button>();
	ArrayList<Button> currentButtons = new ArrayList<Button>();
	ArrayList<Button> audioButtons = new ArrayList<Button>();
	TrueTypeFont font;

	// void resize(500,500);
	boolean mainMenu = true;
	boolean options = false;
	boolean audioMenu = false;
	boolean instructions = false;
	StateBasedGame game;

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		buttons(gc);
		this.game = game;
	}

	public void buttons(GameContainer gc) throws SlickException {
		halfWidth = gc.getWidth() / divideBy;
		halfHeight = gc.getHeight() / divideBy;
		height = gc.getHeight();
		width = gc.getWidth();

		float heightOffset = 0;
		float heightIncr = 100f;
		context = new Context(gc, gc.getGraphics());

		currentButtons = mainButtons;
		// buttons =

		Button audioBtn = new Button(context,
				new Vector2f(width / 2 - 75, 150f), Context.getImage("audio.png"),
				Context.getImage("highAudio.png"));
		audioBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			audioMenu = true;
			Audio.playSound("testSample.wav");

				currentButtons = audioButtons;
		});

		Button screenBtn = new Button(context, new Vector2f(width / 2 - 75,
				250f), Context.getImage("screenSize.png"),
				Context.getImage("highScreenSize.png"));
		screenBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			Audio.playSound("testSample.wav");
		});

		Button optionsBackBtn = new Button(context,
				new Vector2f(width / 2 - 75, 450f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		optionsBackBtn.onClick(() -> {
			options = false;
			mainMenu = true;
			Audio.playSound("testSample.wav");
			
				currentButtons = mainButtons;
			
			// buttons.add(startBtn);

		});
		
		Button audioBackBtn = new Button(context,
				new Vector2f(width / 2 - 75, 350f), Context.getImage("back.png"),
				Context.getImage("highBack.png"));
		audioBackBtn.onClick(() -> {
			options = false;
			mainMenu = true;
			Audio.playSound("testSample.wav");
			
			currentButtons = optionsButtons;
			
			// buttons.add(startBtn);

		});
		
		Button startBtn = new Button(context,
				new Vector2f(width / 2 - 75, 150f), Context.getImage("start.png"),
				Context.getImage("highStart.png"));
		startBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			Audio.playSound("testSample.wav");
			game.enterState(1, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		});

		Button scoresBtn = new Button(context, new Vector2f(width / 2 - 75,
				250f), Context.getImage("highScores.png"),
				Context.getImage("highHighScores.png"));
		scoresBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			Audio.playSound("testSample.wav");
			System.out.println("test");
		});

		Button optionsBtn = new Button(context, new Vector2f(width / 2 - 75,
				350f), Context.getImage("options.png"), Context.getImage("highOptions.png"));
		optionsBtn.onClick(() -> {
			options = true;
			mainMenu = false;
			if (options) {
				/*
				 * buttons = new ArrayList<Button>(); buttons.add(audioBtn);
				 * buttons.add(screenBtn); buttons.add(backBtn);
				 */
				currentButtons = optionsButtons;
				// currentButtons = new ArrayList<Button>();
			}
			Audio.playSound("testSample.wav");
			System.out.println("test");
		});

		Button instructionsBtn = new Button(context, new Vector2f(
				width / 2 - 75, 450f), Context.getImage("instructions.png"),
				Context.getImage("highInstructions.png"));
		instructionsBtn.onClick(() -> {
			System.out.println("testggg");
			options = true;
			mainMenu = false;
			Audio.playSound("test.wav");
		});
		
		
		Button audioBarBtn = new Button(context,
				new Vector2f(width / 2 - 75, 550f), Context.getImage("audioBar.png"),
				Context.getImage("audioBar.png"));
		audioBarBtn.onClick(() -> {
		
			Audio.playSound("testSample.wav");

		});

		// populate arraylist

		mainButtons.add(startBtn);
		mainButtons.add(scoresBtn);
		mainButtons.add(optionsBtn);
		mainButtons.add(instructionsBtn);

		optionsButtons.add(audioBtn);
		optionsButtons.add(screenBtn);
		optionsButtons.add(optionsBackBtn);

		audioButtons.add(audioBackBtn);
		audioButtons.add(audioBarBtn);

		/*
		 * buttons.add(new Button(context, new Vector2f((width / 2), (height /
		 * 4)), "testing", new CallBack() { public void run() { options = true;
		 * mainMenu = false; MainGame.start(); Audio.playSound("test.wav");
		 * System.out.println("test"); } }, "start.png", "highStart.png"));
		 * 
		 * // buttons = new ArrayList<Button>(); buttons.add(new Button(context,
		 * new Vector2f((width / 2) + 50, height / 2), "test", new CallBack() {
		 * public void run() { options = true; mainMenu = false;
		 * Audio.playSound("test.wav"); System.out.println("test"); } },
		 * "highScores.png", "highHighScores.png"));
		 * 
		 * // buttons = new ArrayList<Button>(); buttons.add(new Button(context,
		 * new Vector2f(width / 2, 50), "test", new CallBack() { public void
		 * run() { options = true; mainMenu = false;
		 * Audio.playSound("test.wav"); System.out.println("test"); } },
		 * "options.png", "highOptions.png"));
		 * 
		 * // buttons = new ArrayList<Button>(); buttons.add(new Button(context,
		 * new Vector2f(width / 2, height / 2 - 10), "test", new CallBack() {
		 * public void run() { options = true; mainMenu = false;
		 * Audio.playSound("test.wav"); System.out.println("test"); } },
		 * "instructions.png", "highInstructions.png"));
		 */
	}

	public void makeButtons(GameContainer gc) throws SlickException {

	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int i)
			throws SlickException {
		// }

		int x = Mouse.getX();
		int y = gc.getHeight() - Mouse.getY();

		for (Button button : currentButtons) {

			if (button.containsPoint(x, y)) {
				button.SetMouseOver(true);

				if (Mouse.isButtonDown(0))
					button.setClicked(true);

				else
					button.setClicked(false);

			} else {
				button.SetMouseOver(false);
				button.setClicked(false);
			}

		}

	}

	public void drawImage(GameContainer gc, org.newdawn.slick.Graphics g,
			float x, final String url) throws SlickException {

		Image img = new Image("/src/Images/" + url);
		// MainMenu.class.getResourceAsStream("/Samples/" + url);
		float imgWidth = img.getWidth();
		float imgHeight = img.getHeight();
		float scaleWidth = imgWidth * scale + 20;
		float scaleHeight = imgHeight * scale;

		img.draw((width / 2) - (scaleWidth / 2), (scaleHeight / 2) * x,
				scaleWidth, scaleHeight);
		// get width and height of the image and multiply it by the scale, scale
		// should be a global variable and should effect everything
		// Image = new Image("image.png", false, Image.FILTER_NEAREST);
	}

	public void background(GameContainer gc, org.newdawn.slick.Graphics g,
			final String url) throws SlickException {

		Image img = new Image("/src/Tiles/" + url);

		// MainMenu.class.getResourceAsStream("/Samples/" + url);
		float imgWidth = img.getWidth();
		float imgHeight = img.getHeight();
		float scaleWidth = imgWidth * context.scale + 20;
		float scaleHeight = imgHeight * context.scale;

		img.draw(0, 0, scaleWidth * 3, scaleHeight * 3);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game,
			org.newdawn.slick.Graphics g) throws SlickException {

		/*
		 * if (mainMenu) { drawImage(gc, g, 1, "start.png"); drawImage(gc, g, 4,
		 * "options.png"); drawImage(gc, g, 7, "instructions.png"); }
		 * 
		 * if (options) { drawImage(gc, g, 1, "highStart.png"); drawImage(gc, g,
		 * 4, "options.png"); drawImage(gc, g, 7, "instructions.png"); }
		 */

		g.scale(1, 1);
		g.setBackground(Color.black);

		background(gc, g, "testBackground.png");
		background(gc, g, "testTitle.png");
		// g.drawImage((new Image("/src/Tiles/testBackground.png")), 0, 0);
		// font.drawString(width / 2 - 125, (height / 2) * scale,
		// "PLACE HOLDER TITLE", Color.yellow);

		// if (mainMenu) {
		// g.setFont(new TrueTypeFont(new Font("TimesRoman", Font.PLAIN,
		// height / 10), false));

		/*
		 * g.setColor(Color.yellow); g.drawString("Start", halfWidth - width /
		 * 10, halfHeight - height / 5);
		 * 
		 * g.setColor(Color.white); g.drawString("Options", halfWidth - width /
		 * 10, halfHeight);
		 * 
		 * g.setColor(Color.red); g.drawString("Instructions", halfWidth - width
		 * / 10, halfHeight + height / 5);
		 */
		// }
		/*
		 * if (options) { g.setColor(Color.yellow); g.drawString("Sound",
		 * halfWidth - width / 10, halfHeight - height / 5);
		 * 
		 * g.setColor(Color.white); g.drawString("Controls", halfWidth - width /
		 * 10, halfHeight);
		 * 
		 * g.setColor(Color.red); g.drawString("Back", halfWidth - width / 10,
		 * halfHeight + height / 5); }
		 */

		for (Button button : currentButtons) {
			button.display();
		}

	}

	// public static void main(String[] args) {
	// try {
	// AppGameContainer appgc = new AppGameContainer(new MainMenu("RunnerMan"));
	// appgc.setDisplayMode(800, 600, false);
	// // appgc.setShowFPS(false);
	//
	// appgc.start();
	// } catch (SlickException ex) {
	// Logger.getLogger(MainGame.class.getName()).log(Level.SEVERE, null,
	// ex);
	// }
	// }

	

	@Override
	public int getID() {
		return 0;
	}

}